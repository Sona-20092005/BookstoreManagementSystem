package org.example.springtask1.persistence.repository;

import org.example.springtask1.persistence.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookGenreRepository extends JpaRepository<BookGenre, Long> {
    @Query("SELECT bg.book " +
            "FROM BookGenre bg WHERE " +
            "bg.genre.genre = :genreName")
    List<Book> findBooksByGenreName(@Param("genreName") String genreName);

}

