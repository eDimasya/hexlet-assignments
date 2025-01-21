package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.model.Book;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private AuthorRepository authorRepository;

    public List<BookDTO> getAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::map)
                .toList();
    }

    public BookDTO findById(Long id) {
        return bookMapper.map(
                bookRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Book with id " + id + " not found")));
    }

    public BookDTO create(BookCreateDTO bookData) {
        Book book = bookMapper.map(bookData);
        book.setAuthor(authorRepository.findById(bookData.getAuthorId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Author with id " + bookData.getAuthorId() + " not found")));
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public BookDTO update(BookUpdateDTO bookData, Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book with id " + id + " not found"));
        bookMapper.update(bookData, book);
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public void delete(Long id) {

    }
    // END
}
