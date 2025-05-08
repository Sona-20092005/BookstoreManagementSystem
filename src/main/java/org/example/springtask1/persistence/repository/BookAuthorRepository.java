package org.example.springtask1.persistence.repository;

import org.example.springtask1.persistence.entity.BookAuthor;
import org.example.springtask1.persistence.entity.BookGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookAuthorRepository extends JpaRepository<BookAuthor, Long> {


}

