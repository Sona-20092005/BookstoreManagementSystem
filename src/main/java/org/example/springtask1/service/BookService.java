package org.example.springtask1.service;

import lombok.RequiredArgsConstructor;
import org.example.springtask1.criteria.BookSearchCriteria;
import org.example.springtask1.dto.*;
import org.example.springtask1.dto.bookdto.BookResponseDto;
import org.example.springtask1.persistence.entity.Author;
import org.example.springtask1.persistence.entity.*;
import org.example.springtask1.persistence.repository.*;
import org.example.springtask1.service.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final GenreRepository genreRepository;
    private final LanguageRepository languageRepository;
    private final CharacterRepository characterRepository;
    private final SettingRepository settingRepository;
    private final AwardRepository awardRepository;
    private final BookAuthorRepository bookAuthorRepository;
    private final BookGenreRepository bookGenreRepository;
    private final BookLanguageRepository bookLanguageRepository;
    private final BookCharacterRepository bookCharacterRepository;
    private final BookSettingRepository bookSettingRepository;
    private final BookAwardRepository bookAwardRepository;

    private final BookMapper bookMapper;
    private final GenreMapper genreMapper;
    private final AuthorMapper authorMapper;
    private final CharacterMapper characterMapper;
    private final SettingMapper settingMapper;
    private final AwardMapper awardMapper;
    private final LanguageMapper languageMapper;
    private final PublisherMapper publisherMapper;
    private final SeriesMapper seriesMapper;
    private final FormatMapper formatMapper;



    @Autowired
    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository,
                       PublisherRepository publisherRepository,
                       GenreRepository genreRepository,
                       CharacterRepository characterRepository,
                       SettingRepository settingRepository,
                       AwardRepository awardRepository,
                       LanguageRepository languageRepository,
                       BookLanguageRepository bookLanguageRepository,
                       BookAuthorRepository bookAuthorRepository,
                       BookGenreRepository bookGenreRepository,
                       BookCharacterRepository bookCharacterRepository,
                       BookSettingRepository bookSettingRepository,
                       BookAwardRepository bookAwardRepository,
                       GenreMapper genreMapper,
                       AuthorMapper authorMapper,
                       LanguageMapper languageMapper,
                       CharacterMapper characterMapper,
                       SettingMapper settingMapper,
                       PublisherMapper publisherMapper,
                       AwardMapper awardMapper,
                       BookMapper bookMapper,
                       SeriesMapper seriesMapper,
                       FormatMapper formatMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
        this.genreRepository = genreRepository;
        this.characterRepository = characterRepository;
        this.settingRepository = settingRepository;
        this.awardRepository = awardRepository;
        this.languageRepository = languageRepository;
        this.bookLanguageRepository = bookLanguageRepository;
        this.bookGenreRepository = bookGenreRepository;
        this.bookAuthorRepository = bookAuthorRepository;
        this.bookCharacterRepository = bookCharacterRepository;
        this.bookSettingRepository = bookSettingRepository;
        this.bookAwardRepository = bookAwardRepository;
        this.bookMapper = bookMapper;
        this.genreMapper = genreMapper;
        this.authorMapper = authorMapper;
        this.languageMapper = languageMapper;
        this.characterMapper = characterMapper;
        this.settingMapper = settingMapper;
        this.awardMapper = awardMapper;
        this.publisherMapper = publisherMapper;
        this.seriesMapper = seriesMapper;
        this.formatMapper = formatMapper;
    }


    public PageResponseDto<BookResponseDto> getAll(BookSearchCriteria criteria) {
        Page<Book> bookPage = bookRepository.findAll(criteria, criteria.buildPageRequest());

        Page<BookResponseDto> bookResponseDtoPage = bookPage.map(book -> convertToDto(book));

        return PageResponseDto.from(bookResponseDtoPage);
    }

    public BookResponseDto getBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        return convertToDto(book);
    }


    private BookResponseDto convertToDto(Book book) {
        BookResponseDto dto = new BookResponseDto();
        dto.setId(book.getId());
        dto.setIsbn(book.getIsbn());
        dto.setTitle(book.getTitle());
        dto.setBookId(book.getBookId());
        dto.setDescription(book.getDescription());
        dto.setRating(book.getRating());
        dto.setEdition(book.getEdition());
        dto.setPages(book.getPages());
        dto.setPrice(book.getPrice());
        dto.setPublishDate(book.getPublishDate());
        dto.setFirstPublishDate(book.getFirstPublishDate());
        dto.setNumRatings(book.getNumRatings());
        dto.setRatingsByStars(book.getRatingsByStars());
        dto.setLikedPercent(book.getLikedPercent());
        dto.setCoverImg(book.getCoverImg());
        dto.setBbeScore(book.getBbeScore());
        dto.setBbeVotes(book.getBbeVotes());


        dto.setAuthorList(getAuthorDtos(book));
        dto.setGenreList(getGenreDtos(book));
        dto.setLanguageList(getLanguageDtos(book));
        dto.setCharacterList(getCharacterDtos(book));
        dto.setSettingList(getSettingDtos(book));
        dto.setAwardList(getAwardDtos(book));
        dto.setSeriesList(getSeriesDtos(book));
        dto.setFormatList(getFormatDtos(book));
        dto.setPublisher(getPublisherDto(book));

        return dto;
    }

    private List<AuthorDto> getAuthorDtos(Book book) {
        List<AuthorDto> authorDtos = new ArrayList<>();
        for (BookAuthor bookAuthor : book.getAuthors()) {
            authorDtos.add(authorMapper.mapToDto(bookAuthor.getAuthor()));
        }
        return authorDtos;
    }

    private List<GenreDto> getGenreDtos(Book book) {
        List<GenreDto> genreDtos = new ArrayList<>();
        for (BookGenre bookGenre : book.getGenres()) {
            genreDtos.add(genreMapper.mapToDto(bookGenre.getGenre()));
        }
        return genreDtos;
    }

    private List<LanguageDto> getLanguageDtos(Book book) {
        List<LanguageDto> languageDtos = new ArrayList<>();
        for (BookLanguage bookLanguage : book.getLanguages()) {
            languageDtos.add(languageMapper.mapToDto(bookLanguage.getLanguage()));
        }
        return languageDtos;
    }

    private List<CharacterDto> getCharacterDtos(Book book) {
        List<CharacterDto> characterDtos = new ArrayList<>();
        for (BookCharacter bookCharacter : book.getCharacters()) {
            characterDtos.add(characterMapper.mapToDto(bookCharacter.getCharacter()));
        }
        return characterDtos;
    }

    private List<SettingDto> getSettingDtos(Book book) {
        List<SettingDto> settingDtos = new ArrayList<>();
        for (BookSetting bookSetting : book.getSettings()) {
            settingDtos.add(settingMapper.mapToDto(bookSetting.getSetting()));
        }
        return settingDtos;
    }

    private List<AwardDto> getAwardDtos(Book book) {
        List<AwardDto> awardDtos = new ArrayList<>();
        for (BookAward bookAward : book.getAwards()) {
            awardDtos.add(awardMapper.mapToDto(bookAward.getAward()));
        }
        return awardDtos;
    }

    private List<SeriesDto> getSeriesDtos(Book book) {
        List<SeriesDto> seriesDtos = new ArrayList<>();
        for (BookSeries bookSeries : book.getSeries()) {
            seriesDtos.add(seriesMapper.mapToDto(bookSeries.getSeries()));
        }
        return seriesDtos;
    }

    private List<FormatDto> getFormatDtos(Book book) {
        List<FormatDto> formatDtos = new ArrayList<>();
        for (BookFormat bookFormat : book.getFormats()) {
            formatDtos.add(formatMapper.mapToDto(bookFormat.getFormat()));
        }
        return formatDtos;
    }

    private PublisherDto getPublisherDto(Book book) {
        return publisherMapper.mapToDto(book.getPublisher());
    }
}
