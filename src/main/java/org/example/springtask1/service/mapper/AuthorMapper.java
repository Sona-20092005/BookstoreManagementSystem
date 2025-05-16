package org.example.springtask1.service.mapper;

import org.example.springtask1.persistence.entity.Author;
import org.example.springtask1.persistence.entity.Book;
import org.example.springtask1.persistence.entity.BookAuthor;
import org.example.springtask1.dto.AuthorDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorMapper {
    public AuthorDto mapToDto(Author author) {
        if (author == null) {
            return null;
        }

        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setName(author.getName());

        return authorDto;
    }

    public List<AuthorDto> mapToDtos(List<BookAuthor> authors) {
        List<AuthorDto> dtos = new ArrayList<>();

        for (BookAuthor author : authors) {
            dtos.add(mapToDto(author.getAuthor()));
        }

        return dtos;
    }

    public Author mapFromDto(AuthorDto dto) {
        if (dto == null) {
            return null;
        }

        Author author = new Author();
        author.setId(dto.getId());
        author.setName(dto.getName());

        return author;
    }

    public List<BookAuthor> mapFromDtosToBookAuthor(List<AuthorDto> authorList, Book book) {
        List<BookAuthor> bookAuthors = new ArrayList<>();

        for (AuthorDto authorDto : authorList) {
            Author author = mapFromDto(authorDto);
            book.addBookAuthor(author, authorDto.getRole());
            BookAuthor bookAuthor = new BookAuthor();
            bookAuthor.setAuthor(mapFromDto(authorDto));
            bookAuthor.setAuthorRole(authorDto.getRole());
            bookAuthors.add(bookAuthor);
        }

        return bookAuthors;
    }

    public List<Author> mapFromDtos(List<AuthorDto> authorList, Book book) {
        List<Author> authors = new ArrayList<>();

        for (AuthorDto authorDto : authorList) {
            Author author = mapFromDto(authorDto);
            book.addBookAuthor(author, authorDto.getRole());
            authors.add(author);

        }

        return authors;
    }
}
