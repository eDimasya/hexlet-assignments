package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public List<PostDTO> index() {
        List<PostDTO> postDTOList = new ArrayList<>();
        postRepository.findAll().forEach(post ->
                postDTOList.add(toDTO(post)));
        return postDTOList;
    }

    @GetMapping("/{id}")
    public PostDTO show(@PathVariable Long id) {
        return toDTO(postRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Post with id " + id + " not found")));
    }

    private PostDTO toDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setBody(post.getBody());
        List<Comment> comments = commentRepository.findByPostId(post.getId());
        List<CommentDTO> commentDTOList = new ArrayList<>();
        comments.forEach(comment ->
                commentDTOList.add(toDTO(comment)));
        postDTO.setComments(commentDTOList);
        return postDTO;
    }

    private CommentDTO toDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setBody(comment.getBody());
        return commentDTO;
    }
}
// END
