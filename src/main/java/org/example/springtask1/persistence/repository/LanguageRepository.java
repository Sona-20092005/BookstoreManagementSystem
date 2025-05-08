package org.example.springtask1.persistence.repository;

import org.example.springtask1.persistence.entity.Author;
import org.example.springtask1.persistence.entity.Award;
import org.example.springtask1.persistence.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    @Query("SELECT lang " +
            "FROM Language lang WHERE " +
            "lang.language = :language")
    List<Language> findByLanguage(String language);

    @Query("SELECT lang " +
            "FROM Language lang WHERE " +
            "lang.language = :language AND lang.additional = :additional")
    List<Language> findByLanguageAndAdditional(String language, String additional);

    @Override
    List<Language> findAll();
}

