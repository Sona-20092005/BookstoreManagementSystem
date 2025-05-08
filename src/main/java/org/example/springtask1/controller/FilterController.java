//package org.example.springtask1.controller;
//
//import org.example.springtask1.dto.*;
//import org.example.springtask1.dto.bookdto.BookDto;
//import org.example.springtask1.service.BookService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/books")
//public class FilterController {
//
//    private final BookService service;
//
//    @Autowired
//    public FilterController(BookService service) {
//        this.service = service;
//    }
////
////    @GetMapping
////    public List<BookDto> getBooks(@RequestParam(value = "isbn", required = true) Long isbn,
////                                  @RequestParam(value = "genres", required = true) List<String> genres) {
////        return this.service.getBooks();
////    }
//
//    @GetMapping("/book/isbn")
//    public BookDto getBooksByIsbn(@RequestParam(value = "isbn", required = true) Long isbn) {
//        return service.getBookByIsbn(isbn);
//    }
//
//    @GetMapping("/book/title")
//    public List<BookDto> getBooksByTitle(@RequestParam(value = "title", required = true) String title) {
//        return service.getBookByTitle(title);
//    }
//
//    @GetMapping("/book/{id}")
//    public BookDto getBook(@PathVariable Long id) {
//        return service.getBook(id);
//    }
//
//    @GetMapping("/genre")
//    public List<BookDto> getBooksByGenre(@RequestParam(value = "genre", required = true) String genre) {
//        return service.getBooksByGenre(genre);
//    }
//
//    @GetMapping("/genre/{id}")
//    public GenreDto getGenre(@PathVariable Long id) {
//        return service.getGenre(id);
//    }
//
//    @GetMapping("/character")
//    public List<BookDto> getBooksByCharacter(@RequestParam(value = "name", required = true) String character) {
//        return service.getBooksByCharacter(character);
//    }
//
//    @GetMapping("/character/{id}")
//    public CharacterDto getCharacter(@PathVariable Long id) {
//        return service.getCharacter(id);
//    }
//
//    @GetMapping("/setting")
//    public List<BookDto> getBooksBySetting(@RequestParam(value = "setting", required = true) String setting) {
//        return service.getBooksBySetting(setting);
//    }
//
//    @GetMapping("/setting/{id}")
//    public SettingDto getSetting(@PathVariable Long id) {
//        return service.getSetting(id);
//    }
//
//    @GetMapping("/language")
//    public List<BookDto> getBooksByLanguage(@RequestParam(value = "language", required = true) String language,
//                                            @RequestParam(value = "additional", required = false) String additional) {
//        return service.getBooksByLanguage(language, additional);
//    }
//
//    @GetMapping("/language/{id}")
//    public LanguageDto getLanguage(@PathVariable Long id) {
//        return service.getLanguage(id);
//    }
//
//    @GetMapping("/author")
//    public List<BookDto> getBooksByAuthor(@RequestParam(value = "author", required = true) String author) {
//        return service.getBooksByAuthor(author);
//    }
//
//    @GetMapping("/author/{id}")
//    public AuthorDto getAuthor(@PathVariable Long id) {
//        return service.getAuthor(id);
//    }
//
//    @GetMapping("/publisher")
//    public List<BookDto> getBooksByPublisher(@RequestParam(value = "publisher", required = true) String publisher) {
//        return service.getBooksByPublisher(publisher);
//    }
//
//    @GetMapping("/publisher/{id}")
//    public PublisherDto getPublisher(@PathVariable Long id) {
//        return service.getPublisher(id);
//    }
//
//    @GetMapping("/award")
//    public List<BookDto> getBooksByAward(@RequestParam(value = "award", required = true) String awardName,
//                                          @RequestParam(value = "year", required = false) Integer year) {
//        return service.getBooksByAward(awardName, year);
//    }
//
//    @GetMapping("/award/{id}")
//    public AwardDto getAward(@PathVariable Long id) {
//        return service.getAward(id);
//    }
//}