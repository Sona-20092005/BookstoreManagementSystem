package org.example.springtask1.service.manager;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.example.springtask1.dto.bookdto.BookCsvModel;
import org.example.springtask1.dto.bookdto.BookDto;
import org.example.springtask1.service.additional.BookError;
import org.example.springtask1.service.additional.BookErrorState;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

public class CSVJacksonReader {

//    public static BookUploadResult getBookList(MultipartFile file) {
//
//        CsvMapper mapper = new CsvMapper();
//        CsvSchema schema = CsvSchema.emptySchema().withHeader();
//
//        MappingIterator<BookDto> it = null;
//        try (InputStream inputStream = file.getInputStream()) {
//            it = mapper.readerFor(BookDto.class)
//                    .with(schema)
//                    .readValues(inputStream);
//        }
//        catch (IOException e) {
//            return null;
//        }
//
//        List<BookError> bookErrorsList = new ArrayList<>();
//
//        List<BookDto> bookDtoList = new ArrayList<>();
//        Set<Long> existingISBN = new HashSet<>();
//
//        while (it.hasNext()) {
//            BookDto bookDto = it.next();
//
//            if (checkFields(bookDto, bookErrorsList, existingISBN)) continue;
//
//            bookDtoList.add(bookDto);
//        }
//
//        BookUploadResult result = new BookUploadResult();
//        result.setBookDtoList(bookDtoList);
//        result.setBookErrorList(bookErrorsList);
//        return result;
//    }


    private static boolean checkFields(BookDto bookDto, List<BookError> bookErrorsList, Set<Long> existingISBN) {
        BookError currentBookError = new BookError();

        if(bookDto.getIsbn() == null) {
            currentBookError.setBookDto(bookDto);
            currentBookError.setBookErrorState(BookErrorState.NO_OR_INCORRECT_ISBN);
            bookErrorsList.add(currentBookError);
            return true;
        }
        if(bookDto.getTitle() == null) {
            currentBookError.setBookDto(bookDto);
            currentBookError.setBookErrorState(BookErrorState.NO_TITLE);
            bookErrorsList.add(currentBookError);
            return true;
        }
        if(bookDto.getBookId() == null) {
            currentBookError.setBookDto(bookDto);
            currentBookError.setBookErrorState(BookErrorState.NO_BOOKID);
            bookErrorsList.add(currentBookError);
            return true;
        }
        if(bookDto.getAuthorList() == null) {
            currentBookError.setBookDto(bookDto);
            currentBookError.setBookErrorState(BookErrorState.NO_OR_INCORRECT_AUTHORS);
            bookErrorsList.add(currentBookError);
            return true;
        }

        if (!existingISBN.add(bookDto.getIsbn())) {
            currentBookError.setBookDto(bookDto);
            currentBookError.setBookErrorState(BookErrorState.DUPLICATE_ISBN);
            bookErrorsList.add(currentBookError);
            return true;
        }
        return false;
    }

    private List<BookCsvModel> parseBooksToDataList(MultipartFile file) {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();

        MappingIterator<BookCsvModel> mappingIterator = null;
        try {
            mappingIterator = mapper
                    .readerFor(BookCsvModel.class)
                    .with(schema)
                    .readValues(file.getInputStream());
            return mappingIterator.readAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
