package org.example.springtask1.service;

import lombok.RequiredArgsConstructor;
import org.example.springtask1.dto.*;
import org.example.springtask1.dto.bookdto.BookDto;
import org.example.springtask1.persistence.entity.Character;
import org.example.springtask1.persistence.entity.*;
import org.example.springtask1.persistence.repository.*;
import org.example.springtask1.service.additional.BookError;
import org.example.springtask1.service.additional.BookErrorState;
import org.example.springtask1.service.additional.BookUploadResult;
import org.example.springtask1.service.additional.LanguagePair;
import org.example.springtask1.service.manager.CSVJacksonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@RequiredArgsConstructor
//@AllArgsConstructor
public class CSVUploadJacksonService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final GenreRepository genreRepository;
    private final LanguageRepository languageRepository;
    private final CharacterRepository characterRepository;
    private final SettingRepository settingRepository;
    private final AwardRepository awardRepository;
    private final FormatRepository formatRepository;
    private final SeriesRepository seriesRepository;

    @Autowired
    public CSVUploadJacksonService(BookRepository bookRepository,
                                   AuthorRepository authorRepository,
                                   PublisherRepository publisherRepository,
                                   GenreRepository genreRepository,
                                   CharacterRepository characterRepository,
                                   SettingRepository settingRepository,
                                   AwardRepository awardRepository,
                                   LanguageRepository languageRepository,
                                   FormatRepository formatRepository,
                                   SeriesRepository seriesRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
        this.genreRepository = genreRepository;
        this.characterRepository = characterRepository;
        this.settingRepository = settingRepository;
        this.awardRepository = awardRepository;
        this.languageRepository = languageRepository;
        this.formatRepository = formatRepository;
        this.seriesRepository = seriesRepository;
    }

    public BookUploadResult upload(MultipartFile file) {

        Map<String, Author> authorByName = getStringAuthorMap();
        Map<String, Genre> genreByName = getStringGenreMap();
        Map<String, Setting> settingByName = getStringSettingMap();
        Map<String, Character> characterByName = getStringCharacterMap();
        Map<String, Award> awardByName = getStringAwardMap();
        Map<LanguagePair, Language> languageByName = getLanguagePairLanguageMap();
        Map<String, Publisher> publisherByName = getStringPublisherMap();
        Map<String, Series> seriesByName = getStringSeriesMap();
        Map<String, Format> formatByName = getStringFormatMap();

        Set<Long> bookList = bookRepository.findAllIsbn();
        List<Book> bookNewList = new ArrayList<>();

//        BookUploadResult result = CSVJacksonReader.getBookList(file);
//        BookUploadResult result = CSVManager.getBookList(file);
        BookUploadResult result = CSVJacksonManager.getBookList(file);


        if(result == null) {
            return null;
        }

        List<BookError> bookErrorList = result.getBookErrorList();
        List<BookDto> bookDtoList = result.getBookDtoList();

        Iterator<BookDto> bookDtoIterator = bookDtoList.iterator();

        while (bookDtoIterator.hasNext()) {
            BookDto dto = bookDtoIterator.next();

            Book book = new Book();

            if(bookList.contains(dto.getIsbn())) {
                BookError bookError = new BookError();
                bookError.setBookDto(dto);
                bookError.setBookErrorState(BookErrorState.DUPLICATE_ISBN);
                bookErrorList.add(bookError);
                bookDtoIterator.remove();
                continue;
            }

            for (AuthorDto authorDto : dto.getAuthorList()) {
                Author author = getAuthor(authorDto.getName(), authorByName);

                book.addBookAuthor(author, authorDto.getRole());
            }

            for (GenreDto genreDto : dto.getGenreList()) {
                Genre genre = getGenre(genreDto.getGenre(), genreByName);

                book.addBookGenre(genre);
            }

            for (CharacterDto characterDto : dto.getCharacterList()) {
                Character character = getCharacter(characterDto.getName(), characterByName);

                book.addBookCharacter(character);
            }

            for (SettingDto settingDto : dto.getSettingList()) {
                Setting setting = getSetting(settingDto.getSetting(), settingByName);

                book.addBookSetting(setting);
            }

            for (AwardDto awardDto : dto.getAwardList()) {
                Award award = getAward(awardDto.getAward(), awardByName);

                book.addBookAward(award, awardDto.getYear());
            }

            for (LanguageDto languageDto : dto.getLanguageList()) {
                Language language = getLanguage(languageDto.getLanguage(), languageDto.getAdditional(), languageByName);

                book.addBookLanguage(language);
            }

            for (SeriesDto seriesDto : dto.getSeriesList()) {
                Series series = getSeries(seriesDto.getSeries(), seriesByName);

                book.addBookSeries(series, seriesDto.getSeriesNumber());
            }

            for (FormatDto formatDto : dto.getFormatList()) {
                Format format = getFormat(formatDto.getFormat(), formatByName);

                book.addBookFormat(format);
            }

            if(dto.getPublisher() != null) {
                Publisher publisher = getPublisher(dto.getPublisher().getName(), publisherByName);

                book.addPublisher(publisher);
            }

            book.setIsbn(dto.getIsbn());
            book.setTitle(dto.getTitle());
            book.setBookId(dto.getBookId());
            book.setDescription(dto.getDescription());
            book.setBbeScore(dto.getBbeScore());
            book.setBbeVotes(dto.getBbeVotes());
            book.setCoverImg(dto.getCoverImg());
            book.setEdition(dto.getEdition());
            book.setFirstPublishDate(dto.getFirstPublishDate());
            book.setPublishDate(dto.getPublishDate());
            book.setNumRatings(dto.getNumRatings());
            book.setRatingsByStars(dto.getRatingsByStars());
            book.setRating(dto.getRating());
            book.setLikedPercent(dto.getLikedPercent());
            book.setPrice(dto.getPrice());
            book.setPages(dto.getPages());
            bookNewList.add(book);
            bookList.add(book.getIsbn());

        }

        bookRepository.saveAll(bookNewList);

        BookUploadResult returnResult = new BookUploadResult();
        returnResult.setBookDtoList(bookDtoList);
        returnResult.setBookErrorList(bookErrorList);

        return returnResult;
    }

//    public BookUploadResult update(MultipartFile file) {
//
//        Map<String, Author> authorByName = getStringAuthorMap();
//        Map<String, Genre> genreByName = getStringGenreMap();
//        Map<String, Setting> settingByName = getStringSettingMap();
//        Map<String, Character> characterByName = getStringCharacterMap();
//        Map<String, Award> awardByName = getStringAwardMap();
//        Map<LanguagePair, Language> languageByName = getLanguagePairLanguageMap();
//        Map<String, Publisher> publisherByName = getStringPublisherMap();
//        Map<String, Series> seriesByName = getStringSeriesMap();
//        Map<String, Format> formatByName = getStringFormatMap();
//
//        List<Book> bookAll = bookRepository.findAll();
//        Map<Long, Book> bookList = new HashMap<>();
//        for (Book book : bookAll) {
//            bookList.put(book.getIsbn(), book);
//        }
//        List<Book> bookNewList = new ArrayList<>();
//        List<Book> bookDeleteList = new ArrayList<>();
//
//        BookUploadResult result = CSVJacksonManager.getBookList(file);
//
//
//        if(result == null) {
//            return null;
//        }
//
//        List<BookError> bookErrorList = result.getBookErrorList();
//        List<BookDto> bookDtoList = result.getBookDtoList();
//
//        Iterator<BookDto> bookDtoIterator = bookDtoList.iterator();
//
//        while (bookDtoIterator.hasNext()) {
//            BookDto dto = bookDtoIterator.next();
//
//            Book book = new Book();
//
//            for (AuthorDto authorDto : dto.getAuthorList()) {
//                Author author = getAuthor(authorDto.getName(), authorByName);
//
//                book.addBookAuthor(author, authorDto.getRole());
//            }
//
//            for (GenreDto genreDto : dto.getGenreList()) {
//                Genre genre = getGenre(genreDto.getGenre(), genreByName);
//
//                book.addBookGenre(genre);
//            }
//
//            for (CharacterDto characterDto : dto.getCharacterList()) {
//                Character character = getCharacter(characterDto.getName(), characterByName);
//
//                book.addBookCharacter(character);
//            }
//
//            for (SettingDto settingDto : dto.getSettingList()) {
//                Setting setting = getSetting(settingDto.getSetting(), settingByName);
//
//                book.addBookSetting(setting);
//            }
//
//            for (AwardDto awardDto : dto.getAwardList()) {
//                Award award = getAward(awardDto.getAward(), awardByName);
//
//                book.addBookAward(award, awardDto.getYear());
//            }
//
//            for (LanguageDto languageDto : dto.getLanguageList()) {
//                Language language = getLanguage(languageDto.getLanguage(), languageDto.getAdditional(), languageByName);
//
//                book.addBookLanguage(language);
//            }
//
//            for (SeriesDto seriesDto : dto.getSeriesList()) {
//                Series series = getSeries(seriesDto.getSeries(), seriesByName);
//
//                book.addBookSeries(series, seriesDto.getSeriesNumber());
//            }
//
//            for (FormatDto formatDto : dto.getFormatList()) {
//                Format format = getFormat(formatDto.getFormat(), formatByName);
//
//                book.addBookFormat(format);
//            }
//
//            if(dto.getPublisher() != null) {
//                Publisher publisher = getPublisher(dto.getPublisher().getName(), publisherByName);
//
//                book.addPublisher(publisher);
//            }
//
//            book.setIsbn(dto.getIsbn());
//            book.setTitle(dto.getTitle());
//            book.setBookId(dto.getBookId());
//            book.setDescription(dto.getDescription());
//            book.setBbeScore(dto.getBbeScore());
//            book.setBbeVotes(dto.getBbeVotes());
//            book.setCoverImg(dto.getCoverImg());
//            book.setEdition(dto.getEdition());
//            book.setFirstPublishDate(dto.getFirstPublishDate());
//            book.setPublishDate(dto.getPublishDate());
//            book.setNumRatings(dto.getNumRatings());
//            book.setRatingsByStars(dto.getRatingsByStars());
//            book.setRating(dto.getRating());
//            book.setLikedPercent(dto.getLikedPercent());
//            book.setPrice(dto.getPrice());
//            book.setPages(dto.getPages());
//            bookNewList.add(book);
//            bookList.put(book.getIsbn(), book);
//
//            Book bookFind = bookList.get(dto.getIsbn());
//            if(bookFind != null) {
//                BookError bookError = new BookError();
//                bookError.setBookDto(dto);
//                bookError.setBookErrorState(BookErrorState.BOOK_UPDATED);
//                bookErrorList.add(bookError);
//                bookDeleteList.add(bookFind);
//            }
//
//        }
//
//        System.out.println("Size " + bookDeleteList.size());
//        bookRepository.deleteAll(bookDeleteList);
//        bookRepository.saveAll(bookNewList);
//
//        BookUploadResult returnResult = new BookUploadResult();
//        returnResult.setBookDtoList(bookDtoList);
//        returnResult.setBookErrorList(bookErrorList);
//
//        return returnResult;
//    }

    private Map<String, Format> getStringFormatMap() {
        List<Format> formatList = formatRepository.findAll();
        Map<String, Format> formatByName = new HashMap<>();

        for (Format format : formatList) {
            formatByName.put(format.getFormat(), format);
        }
        return formatByName;
    }

    private Map<String, Series> getStringSeriesMap() {
        List<Series> seriesList = seriesRepository.findAll();
        Map<String, Series> seriesByName = new HashMap<>();

        for (Series series : seriesList) {
            seriesByName.put(series.getSeries(), series);
        }
        return seriesByName;
    }

    private Map<String, Publisher> getStringPublisherMap() {
        List<Publisher> publisherList = publisherRepository.findAll();
        Map<String, Publisher> publisherByName = new HashMap<>();

        for (Publisher publisher : publisherList) {
            publisherByName.put(publisher.getName(), publisher);
        }
        return publisherByName;
    }

    private Map<LanguagePair, Language> getLanguagePairLanguageMap() {
        List<Language> languageList = languageRepository.findAll();
        Map<LanguagePair, Language> languageByName = new HashMap<>();

        for (Language language : languageList) {
            languageByName.put(new LanguagePair(language.getLanguage(), language.getAdditional()), language);
        }
        return languageByName;
    }

    private Map<String, Award> getStringAwardMap() {
        List<Award> awardList = awardRepository.findAll();
        Map<String, Award> awardByName = new HashMap<>();

        for (Award award : awardList) {
            awardByName.put(award.getAward(), award);
        }
        return awardByName;
    }

    private Map<String, Character> getStringCharacterMap() {
        List<Character> characterList = characterRepository.findAll();
        Map<String, Character> characterByName = new HashMap<>();

        for (Character character : characterList) {
            characterByName.put(character.getName(), character);
        }
        return characterByName;
    }

    private Map<String, Setting> getStringSettingMap() {
        List<Setting> settingList = settingRepository.findAll();
        Map<String, Setting> settingByName = new HashMap<>();

        for (Setting setting : settingList) {
            settingByName.put(setting.getSetting(), setting);
        }
        return settingByName;
    }

    private Map<String, Genre> getStringGenreMap() {
        List<Genre> genreList = genreRepository.findAll();
        Map<String, Genre> genreByName = new HashMap<>();

        for (Genre genre : genreList) {
            genreByName.put(genre.getGenre(), genre);
        }
        return genreByName;
    }

    private Map<String, Author> getStringAuthorMap() {
        List<Author> authorList = authorRepository.findAll();
        Map<String, Author> authorByName = new HashMap<>();

        for (Author author : authorList) {
            authorByName.put(author.getName(), author);
        }
        return authorByName;
    }

    private Author getAuthor(String authorName, Map<String, Author> authorByName) {
        if (authorByName.containsKey(authorName)) {
            return authorByName.get(authorName);
        }

        Author author = new Author();
        author.setName(authorName);
        authorByName.put(authorName, author);

        return author;
    }

    private Genre getGenre(String genreName, Map<String, Genre> genreByName) {
        if (genreByName.containsKey(genreName)) {
            return genreByName.get(genreName);
        }

        Genre genre = new Genre();
        genre.setGenre(genreName);
        genreByName.put(genreName, genre);

        return genre;
    }

    private Award getAward(String awardName, Map<String, Award> awardByName) {
        if (awardByName.containsKey(awardName)) {
            return awardByName.get(awardName);
        }

        Award award = new Award();
        award.setAward(awardName);
        awardByName.put(awardName, award);

        return award;
    }

    private Character getCharacter(String characterName, Map<String, Character> characterByName) {
        if (characterByName.containsKey(characterName)) {
            return characterByName.get(characterName);
        }

        Character character = new Character();
        character.setName(characterName);
        characterByName.put(characterName, character);

        return character;
    }

    private Language getLanguage(String languageName, String languageAdditional, Map<LanguagePair, Language> languageByName) {
        LanguagePair key =new LanguagePair(languageName, languageAdditional);
        if (languageByName.containsKey(key)) {
            return languageByName.get(key);
        }

        Language language = new Language();
        language.setLanguage(languageName);
        language.setAdditional(languageAdditional);
        languageByName.put(key, language);

        return language;
    }

    private Publisher getPublisher(String publisherName, Map<String, Publisher> publisherByName) {
        if (publisherByName.containsKey(publisherName)) {
            return publisherByName.get(publisherName);
        }

        Publisher publisher = new Publisher();
        publisher.setName(publisherName);
        publisherByName.put(publisherName, publisher);

        return publisher;
    }

    private Setting getSetting(String settingName, Map<String, Setting> settingByName) {
        if (settingByName.containsKey(settingName)) {
            return settingByName.get(settingName);
        }

        Setting setting = new Setting();
        setting.setSetting(settingName);
        settingByName.put(settingName, setting);

        return setting;
    }

    private Series getSeries(String seriesName, Map<String, Series> seriesByName) {
        if (seriesByName.containsKey(seriesName)) {
            return seriesByName.get(seriesName);
        }

        Series series = new Series();
        series.setSeries(seriesName);
        seriesByName.put(seriesName, series);

        return series;
    }

    private Format getFormat(String formatName, Map<String, Format> formatByName) {
        if (formatByName.containsKey(formatName)) {
            return formatByName.get(formatName);
        }

        Format format = new Format();
        format.setFormat(formatName);
        formatByName.put(formatName, format);

        return format;
    }



}
