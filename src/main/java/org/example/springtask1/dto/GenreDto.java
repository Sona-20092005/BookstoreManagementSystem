package org.example.springtask1.dto;

import java.util.Objects;

public class GenreDto {

    private Long id;

    private String genre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreDto genreDto = (GenreDto) o;
        return Objects.equals(genre, genreDto.genre);
    }

    @Override
    public String toString() {
        return "GenreDto{" +
                "id=" + id +
                ", genre='" + genre + '\'' +
                '}';
    }
}
