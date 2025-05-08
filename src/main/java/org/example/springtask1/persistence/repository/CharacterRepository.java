package org.example.springtask1.persistence.repository;

import org.example.springtask1.persistence.entity.Character;
import org.example.springtask1.persistence.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    Character findByName(String characterName);

    @Override
    List<Character> findAll();
}
