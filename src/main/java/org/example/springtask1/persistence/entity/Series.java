package org.example.springtask1.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "series")
//@Setter
//@Getter
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "series_id_seq")
    @SequenceGenerator(
            name = "series_id_seq",
            sequenceName = "series_id_seq",
            allocationSize = 50
    )
    private Long id;

    @Column(name = "series", nullable = false, unique = true)
    private String series;

    @OneToMany(mappedBy = "series")
    private List<BookSeries> books = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public List<BookSeries> getBooks() {
        return books;
    }

    public void setBooks(List<BookSeries> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Series series1 = (Series) o;
        return Objects.equals(series, series1.series);
    }

    @Override
    public int hashCode() {
        return Objects.hash(series);
    }
}
