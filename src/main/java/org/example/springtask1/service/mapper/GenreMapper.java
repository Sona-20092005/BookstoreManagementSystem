package org.example.springtask1.service.mapper;

import org.example.springtask1.persistence.entity.Book;
import org.example.springtask1.persistence.entity.BookGenre;
import org.example.springtask1.persistence.entity.Genre;
import org.example.springtask1.dto.GenreDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper {

    public GenreDto mapToDto(Genre genre) {
        if (genre == null) {
            return null;
        }

        GenreDto genreDto = new GenreDto();
        genreDto.setId(genre.getId());
        genreDto.setGenre(genre.getGenre());

        return genreDto;
    }

    public List<GenreDto> mapToDtos(List<BookGenre> genres) {
        List<GenreDto> dtos = new ArrayList<>();

        for (BookGenre genre : genres) {
            dtos.add(mapToDto(genre.getGenre()));
        }

        return dtos;
    }

    public Genre mapFromDto(GenreDto dto) {
        if (dto == null) {
            return null;
        }

        Genre genre = new Genre();
        genre.setId(dto.getId());
        genre.setGenre(dto.getGenre());

        return genre;
    }

    public List<Genre> mapFromDtos(List<GenreDto> genreList, Book book) {
        List<Genre> genres = new ArrayList<>();

        for (GenreDto genreDto : genreList) {
            Genre genre = mapFromDto(genreDto);
            book.addBookGenre(genre);
            genres.add(genre);

        }

        return genres;
    }

}

