package org.example.springtask1.service.additional;

import java.util.Objects;

public class LanguagePair {
    private String language;
    private String additional;

    public LanguagePair(String language, String additional) {
        this.language = language;
        this.additional = additional;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LanguagePair that = (LanguagePair) o;
        return Objects.equals(language, that.language) && Objects.equals(additional, that.additional);
    }

    @Override
    public int hashCode() {
        return Objects.hash(language, additional);
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
}
