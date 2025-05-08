package org.example.springtask1.dto;

import java.util.Objects;

public class LanguageDto {

    private Long id;
    private String language;
    private String additional;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LanguageDto that = (LanguageDto) o;
        return Objects.equals(language, that.language) && Objects.equals(additional, that.additional);
    }

    @Override
    public String toString() {
        return "LanguageDto{" +
                "id=" + id +
                ", language='" + language + '\'' +
                ", additional='" + additional + '\'' +
                '}';
    }
}
