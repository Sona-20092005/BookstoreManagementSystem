package org.example.springtask1.persistence.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "format")
public class Format {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "format_id_seq")
    @SequenceGenerator(
            name = "format_id_seq",
            sequenceName = "format_id_seq",
            allocationSize = 50
    )
    private Long id;

    @Column(name = "format", nullable = false, unique = true)
    private String format;

    @OneToMany(mappedBy = "format")
    private List<BookFormat> books = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public List<BookFormat> getBooks() {
        return books;
    }

    public void setBooks(List<BookFormat> books) {
        this.books = books;
    }
}
