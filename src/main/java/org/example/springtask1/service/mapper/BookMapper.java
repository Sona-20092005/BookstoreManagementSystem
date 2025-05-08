package org.example.springtask1.service.mapper;

import org.example.springtask1.persistence.entity.Book;
import org.example.springtask1.dto.bookdto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookMapper {

    private final PublisherMapper publisherMapper;
    private final AuthorMapper authorMapper;
    private final LanguageMapper languageMapper;
    private final GenreMapper genreMapper;
    private final CharacterMapper characterMapper;
    private final SettingMapper settingMapper;
    private final AwardMapper awardMapper;
    private final SeriesMapper seriesMapper;
    private final FormatMapper formatMapper;

    @Autowired
    public BookMapper(PublisherMapper publisherMapper,
                      AuthorMapper authorMapper,
                      LanguageMapper languageMapper,
                      GenreMapper genreMapper,
                      CharacterMapper characterMapper,
                      SettingMapper settingMapper,
                      AwardMapper awardMapper,
                      SeriesMapper seriesMapper,
                      FormatMapper formatMapper) {
        this.publisherMapper = publisherMapper;
        this.authorMapper = authorMapper;
        this.languageMapper = languageMapper;
        this.genreMapper = genreMapper;
        this.characterMapper = characterMapper;
        this.settingMapper = settingMapper;
        this.awardMapper = awardMapper;
        this.seriesMapper = seriesMapper;
        this.formatMapper = formatMapper;
    }

    public BookDto mapToDto(Book book) {
        if (book == null) {
            return null;
        }

        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setTitle(book.getTitle());
        bookDto.setBookId(book.getBookId());
        bookDto.setDescription(book.getDescription());
        bookDto.setBbeScore(book.getBbeScore());
        bookDto.setBbeVotes(book.getBbeVotes());
        bookDto.setCoverImg(book.getCoverImg());
        bookDto.setEdition(book.getEdition());
        bookDto.setFirstPublishDate(book.getFirstPublishDate());
        bookDto.setPublishDate(book.getPublishDate());
        bookDto.setNumRatings(book.getNumRatings());
        bookDto.setRatingsByStars(book.getRatingsByStars());
        bookDto.setRating(book.getRating());
        bookDto.setLikedPercent(book.getLikedPercent());
        bookDto.setPrice(book.getPrice());
        bookDto.setPages(book.getPages());
        bookDto.setPublisher(publisherMapper.mapToDto(book.getPublisher()));
        bookDto.setAuthorList(authorMapper.mapToDtos(book.getAuthors()));
        bookDto.setGenreList(genreMapper.mapToDtos(book.getGenres()));
        bookDto.setCharacterList(characterMapper.mapToDtos(book.getCharacters()));
        bookDto.setSettingList(settingMapper.mapToDtos(book.getSettings()));
        bookDto.setAwardList(awardMapper.mapToDtos(book.getAwards()));
        bookDto.setLanguageList(languageMapper.mapToDtos(book.getLanguages()));
        bookDto.setSeriesList(seriesMapper.mapToDtos(book.getSeries()));
        bookDto.setFormatList(formatMapper.mapToDtos(book.getFormats()));


        return bookDto;
    }

    public List<BookDto> mapToDtos(List<Book> books) {
        List<BookDto> dtos = new ArrayList<>();

        for (Book book : books) {
            dtos.add(mapToDto(book));
        }

        return dtos;
    }

    public Book mapFromDto(BookDto dto) {
        if (dto == null) {
            return null;
        }

        Book book = new Book();
        book.setId(dto.getId());
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
        book.setPublisher(publisherMapper.mapFromDto(dto.getPublisher()));

        authorMapper.mapFromDtos(dto.getAuthorList(), book);
        genreMapper.mapFromDtos(dto.getGenreList(), book);
        characterMapper.mapFromDtos(dto.getCharacterList(), book);
        settingMapper.mapFromDtos(dto.getSettingList(), book);
        awardMapper.mapFromDtos(dto.getAwardList(), book);
        languageMapper.mapFromDtos(dto.getLanguageList(), book);
        seriesMapper.mapFromDtos(dto.getSeriesList(), book);
        formatMapper.mapFromDtos(dto.getFormatList(), book);
//        book.setAuthors(authorMapper.mapFromDtos(dto.getAuthorList()));
//        book.setGenres(genreMapper.mapFromDtos(dto.getGenreList()));
//        book.setCharacters(characterMapper.mapFromDtos(dto.getCharacterList()));
//        book.setSettings(settingMapper.mapFromDtos(dto.getSettingList()));
//        book.setAwards(awardMapper.mapFromDtos(dto.getAwardList()));
//        book.setLanguages(languageMapper.mapFromDtos(dto.getLanguageList()));
//        book.setSeries(seriesMapper.mapFromDtos(dto.getSeriesList()));
//        book.setFormats(formatMapper.mapFromDtos(dto.getFormatList()));

        return book;
    }

}

