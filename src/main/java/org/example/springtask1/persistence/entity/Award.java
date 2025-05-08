package org.example.springtask1.persistence.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "award")
//@Setter
//@Getter
public class Award {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "award_id_seq")
    @SequenceGenerator(
            name = "award_id_seq",
            sequenceName = "award_id_seq",
            allocationSize = 50
    )
    private Long id;

    @Column(name = "award", nullable = false, unique = true)
    private String award;

    @OneToMany(mappedBy = "award")
    private List<BookAward> books = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public List<BookAward> getBooks() {
        return books;
    }

    public void setBooks(List<BookAward> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Award award1 = (Award) o;
        return Objects.equals(award, award1.award);
    }

    @Override
    public int hashCode() {
        return Objects.hash(award);
    }
}
