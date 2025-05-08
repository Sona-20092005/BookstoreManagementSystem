package org.example.springtask1.service.mapper;

import org.example.springtask1.persistence.entity.Book;
import org.example.springtask1.persistence.entity.BookLanguage;
import org.example.springtask1.persistence.entity.Language;
import org.example.springtask1.dto.LanguageDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LanguageMapper {

    public LanguageDto mapToDto(Language language) {
        if (language == null) {
            return null;
        }

        LanguageDto languageDto = new LanguageDto();
        languageDto.setId(language.getId());
        languageDto.setLanguage(language.getLanguage());
        languageDto.setAdditional(language.getAdditional());

        return languageDto;
    }

    public List<LanguageDto> mapToDtos(List<BookLanguage> languages) {
        List<LanguageDto> dtos = new ArrayList<>();

        for (BookLanguage language : languages) {
            dtos.add(mapToDto(language.getLanguage()));
        }

        return dtos;
    }

    public Language mapFromDto(LanguageDto dto) {
        if (dto == null) {
            return null;
        }

        Language language = new Language();
        language.setId(dto.getId());
        language.setLanguage(dto.getLanguage());
        language.setAdditional(dto.getAdditional());

        return language;
    }

    public List<Language> mapFromDtos(List<LanguageDto> languageList, Book book) {
        List<Language> languages = new ArrayList<>();

        for (LanguageDto languageDto : languageList) {
            Language language = mapFromDto(languageDto);
            book.addBookLanguage(language);
            languages.add(language);

        }

        return languages;
    }

}

