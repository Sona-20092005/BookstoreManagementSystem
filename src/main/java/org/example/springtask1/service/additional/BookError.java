package org.example.springtask1.service.additional;

import org.example.springtask1.dto.bookdto.BookDto;

public class BookError {
    BookDto bookDto;
    BookErrorState bookErrorState;

    public BookDto getBookDto() {
        return bookDto;
    }

    public void setBookDto(BookDto bookDto) {
        this.bookDto = bookDto;
    }

    public BookErrorState getBookErrorState() {
        return bookErrorState;
    }

    public void setBookErrorState(BookErrorState bookErrorState) {
        this.bookErrorState = bookErrorState;
    }
}
