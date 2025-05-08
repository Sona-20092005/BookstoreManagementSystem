package org.example.springtask1.persistence.repository;

import org.example.springtask1.persistence.entity.BookGenre;
import org.example.springtask1.persistence.entity.BookLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookLanguageRepository extends JpaRepository<BookLanguage, Long> {


}

