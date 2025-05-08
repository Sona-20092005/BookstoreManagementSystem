package org.example.springtask1.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book_character")
//@Setter
//@Getter
public class BookCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_character_id_seq")
    @SequenceGenerator(
            name = "book_character_id_seq",
            sequenceName = "book_character_id_seq",
            allocationSize = 50
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "character_id")
    private Character character;

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

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
