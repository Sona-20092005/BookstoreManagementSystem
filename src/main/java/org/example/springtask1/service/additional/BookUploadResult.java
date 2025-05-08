package org.example.springtask1.service.additional;

import org.example.springtask1.dto.bookdto.BookDto;

import java.util.ArrayList;
import java.util.List;

public class BookUploadResult {
    List<BookDto> bookDtoList = new ArrayList<>();
    List<BookError> bookErrorList = new ArrayList<>();

    public List<BookDto> getBookDtoList() {
        return bookDtoList;
    }

    public void setBookDtoList(List<BookDto> bookDtoList) {
        this.bookDtoList = bookDtoList;
    }

    public List<BookError> getBookErrorList() {
        return bookErrorList;
    }

    public void setBookErrorList(List<BookError> bookErrorList) {
        this.bookErrorList = bookErrorList;
    }


}
