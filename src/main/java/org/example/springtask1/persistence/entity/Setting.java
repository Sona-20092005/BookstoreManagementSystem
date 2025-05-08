package org.example.springtask1.persistence.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "setting")
//@Setter
//@Getter
public class Setting {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "setting_id_seq")
    @SequenceGenerator(
            name = "setting_id_seq",
            sequenceName = "setting_id_seq",
            allocationSize = 50
    )
    private Long id;

    @Column(name = "setting", nullable = false, unique = true)
    private String setting;

    @OneToMany(mappedBy = "setting")
    private List<BookSetting> books = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public List<BookSetting> getBooks() {
        return books;
    }

    public void setBooks(List<BookSetting> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Setting setting1 = (Setting) o;
        return Objects.equals(setting, setting1.setting);
    }

    @Override
    public int hashCode() {
        return Objects.hash(setting);
    }
}
