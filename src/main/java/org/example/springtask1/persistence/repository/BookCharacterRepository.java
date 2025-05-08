package org.example.springtask1.persistence.repository;

import org.example.springtask1.persistence.entity.BookCharacter;
import org.example.springtask1.persistence.entity.BookGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCharacterRepository extends JpaRepository<BookCharacter, Long> {


}

