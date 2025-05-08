package org.example.springtask1.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "language")
//@Setter
//@Getter
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "language_id_seq")
    @SequenceGenerator(
            name = "language_id_seq",
            sequenceName = "language_id_seq",
            allocationSize = 50
    )
    private Long id;

    @Column(name = "language", nullable = false)
    private String language;
    @Column(name = "addtional")
    private String additional;

    @OneToMany(mappedBy = "language")
    private List<BookLanguage> books = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public List<BookLanguage> getBooks() {
        return books;
    }

    public void setBooks(List<BookLanguage> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language1 = (Language) o;
        return Objects.equals(language, language1.language) && Objects.equals(additional, language1.additional);
    }
}
