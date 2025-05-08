package org.example.springtask1.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book_setting")
public class BookSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_setting_id_seq")
    @SequenceGenerator(
            name = "book_setting_id_seq",
            sequenceName = "book_setting_id_seq",
            allocationSize = 50
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "setting_id")
    private Setting setting;

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

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }
}
