package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import exercise.model.Contact;
import exercise.repository.ContactRepository;
import exercise.dto.ContactDTO;
import exercise.dto.ContactCreateDTO;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    // BEGIN
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO create(@RequestBody ContactCreateDTO contactData) {
        Contact contact = contactRepository.save(toEntity(contactData));
        return toDTO(contact);
    }

    private ContactDTO toDTO(Contact contact) {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setId(contact.getId());
        contactDTO.setPhone(contact.getPhone());
        contactDTO.setFirstName(contact.getFirstName());
        contactDTO.setLastName(contact.getLastName());
        contactDTO.setUpdatedAt(contact.getUpdatedAt());
        contactDTO.setCreatedAt(contact.getCreatedAt());
        return contactDTO;
    }

    private Contact toEntity(ContactCreateDTO contactCreateDTO) {
        Contact contact = new Contact();
        contact.setPhone(contactCreateDTO.getPhone());
        contact.setFirstName(contact.getFirstName());
        contact.setLastName(contact.getLastName());
        return contact;
    }
    // END
}
