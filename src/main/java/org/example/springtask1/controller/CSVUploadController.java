package org.example.springtask1.controller;

import org.example.springtask1.service.CSVUploadJacksonService;
import org.example.springtask1.service.additional.BookError;
import org.example.springtask1.service.additional.BookUploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/csv")
public class CSVUploadController {

    private final CSVUploadJacksonService serviceJackson;
    
    @Autowired
    public CSVUploadController(CSVUploadJacksonService serviceJackson) {
        this.serviceJackson = serviceJackson;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
        }

        BookUploadResult result = serviceJackson.upload(file);

        if (result == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to perform request");
        }

        StringBuilder response = new StringBuilder();

        for (BookError bookError : result.getBookErrorList()) {
            response.append(bookError.getBookErrorState());
            response.append(": ");
            response.append(bookError.getBookDto());
            response.append("\n");
        }

        return ResponseEntity.ok(response.toString());
    }

//    @PostMapping("/update")
//    public ResponseEntity<String> updateCSV(@RequestParam("file") MultipartFile file) {
//        if (file.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
//        }
//
//        BookUploadResult result = serviceJackson.update(file);
//
//        if (result == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to perform request");
//        }
//
//        StringBuilder response = new StringBuilder();
//
//        for (BookError bookError : result.getBookErrorList()) {
//            response.append(bookError.getBookErrorState());
//            response.append(": ");
//            response.append(bookError.getBookDto());
//            response.append("\n");
//        }
//
//        return ResponseEntity.ok(response.toString());
//    }
}