package org.example.springtask1.persistence.repository;

import org.example.springtask1.persistence.entity.Book;
import org.example.springtask1.persistence.entity.BookFormat;
import org.example.springtask1.persistence.entity.BookGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookFormatRepository extends JpaRepository<BookFormat, Long> {
//    @Query("SELECT bg.book " +
//            "FROM BookGenre bg WHERE " +
//            "bg.genre.genre = :genreName")
//    List<Book> findBooksByGenreName(@Param("genreName") String genreName);

}

