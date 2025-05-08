package org.example.springtask1.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "book_series")
public class BookSeries {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_series_id_seq")
    @SequenceGenerator(
            name = "book_series_id_seq",
            sequenceName = "book_series_id_seq",
            allocationSize = 50
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "series_id")
    private Series series;

    @Column(name = "seriesNumber")
    private String seriesNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public String getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(String seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookSeries that = (BookSeries) o;
        return Objects.equals(book, that.book) && Objects.equals(series, that.series);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, series);
    }
}
