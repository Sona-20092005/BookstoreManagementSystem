package org.example.springtask1.persistence.repository;

import org.example.springtask1.persistence.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findByGenre(String genreName);

    @Override
    List<Genre> findAll();
}
