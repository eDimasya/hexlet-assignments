package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN

    private Task createTask() {
        return Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().sentence(10))
                .create();
    }

    @Test
    public void testShow() throws Exception {
        Task task = createTask();
        taskRepository.save(task);
        MockHttpServletRequestBuilder request = get("/tasks/" + task.getId());
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        String body = result.getResponse().getContentAsString();
        assertThat(body).isEqualTo(om.writeValueAsString(task));
    }

    @Test
    public void testCreate() throws Exception {
        Task task = createTask();
        MockHttpServletRequestBuilder request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(task));
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();
        String body = result.getResponse().getContentAsString();
        Task responseTask = om.readValue(body, Task.class);
        Task actual = taskRepository.findById(responseTask.getId()).orElseThrow();
        assertThatJson(actual).and(
                jsonAssert -> jsonAssert.node("title").isEqualTo(task.getTitle()),
                jsonAssert -> jsonAssert.node("description").isEqualTo(task.getDescription())
        );
    }

    @Test
    public void testUpdate() throws Exception {
        Task task = taskRepository.save(createTask());
        task.setDescription(faker.lorem().sentence(15));
        MockHttpServletRequestBuilder request = put("/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(task));
        mockMvc.perform(request)
                .andExpect(status().isOk());
        Task actual = taskRepository.findById(task.getId()).orElseThrow();
        assertThatJson(actual).and(
                jsonAssert -> jsonAssert.node("description").isEqualTo(task.getDescription())
        );
    }

    @Test
    public void testDelete() throws Exception {
        Task task = createTask();
        taskRepository.save(task);
        MockHttpServletRequestBuilder request = delete("/tasks/" + task.getId());
        mockMvc.perform(request);
        assertThat(taskRepository.findById(task.getId())).isEmpty();
    }
    // END
}
