package org.example.springtask1.controller;

import org.example.springtask1.criteria.BookSearchCriteria;
import org.example.springtask1.dto.*;
import org.example.springtask1.dto.bookdto.BookDto;
import org.example.springtask1.dto.bookdto.BookResponseDto;
import org.example.springtask1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public BookResponseDto getById(@PathVariable Long id) {
        return service.getBook(id);
    }

    @GetMapping
    public PageResponseDto<BookResponseDto> getAll(BookSearchCriteria criteria) {
        return service.getAll(criteria);
    }

   

}