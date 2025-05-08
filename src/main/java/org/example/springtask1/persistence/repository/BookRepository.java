package org.example.springtask1.persistence.repository;

import org.example.springtask1.criteria.BookSearchCriteria;
import org.example.springtask1.dto.bookdto.BookResponseDto;
import org.example.springtask1.persistence.entity.Book;
import org.example.springtask1.service.additional.BookCoverView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByIsbn(Long isbn);

    @Query("SELECT b.coverImg FROM Book b")
    List<String> findAllCoverImg();

    List<BookCoverView> findAllBy();

    Optional<Book> findById(Long id);

    @Override
    List<Book> findAll();

    @Query("SELECT b.isbn FROM Book b")
    Set<Long> findAllIsbn();

    @Query("SELECT b\n" +
           "     FROM Book b\n" +
           "     LEFT JOIN b.authors ba\n" +
           "     LEFT JOIN ba.author a\n" +
           "     LEFT JOIN b.genres bg\n" +
           "     LEFT JOIN bg.genre g\n" +
           "     LEFT JOIN b.languages bl\n" +
           "     LEFT JOIN bl.language l\n" +
           "     LEFT JOIN b.characters bc\n" +
           "     LEFT JOIN bc.character ch\n" +
           "     LEFT JOIN b.settings bs\n" +
           "     LEFT JOIN bs.setting s\n" +
           "     LEFT JOIN b.awards baw\n" +
           "     LEFT JOIN baw.award aw\n" +
           "     LEFT JOIN b.series bsr\n" +
           "     LEFT JOIN bsr.series sr\n" +
           "     LEFT JOIN b.formats bf\n" +
           "     LEFT JOIN bf.format f\n" +
           "     LEFT JOIN b.publisher p\n"
//           "     WHERE\n" +
//           "         (:#{#criteria.title} IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :#{#criteria.title}, '%')))\n" +
//           "         AND (:#{#criteria.author} IS NULL OR LOWER(a.name) LIKE LOWER(CONCAT('%', :#{#criteria.author}, '%')))\n" +
//           "         AND (:#{#criteria.isbn} IS NULL OR b.isbn = :#{#criteria.isbn})\n" +
//           "         AND (:#{#criteria.bookId} IS NULL OR b.bookId = :#{#criteria.bookId})\n" +
//           "         AND (:#{#criteria.award} IS NULL OR LOWER(aw.award) LIKE LOWER(CONCAT('%', :#{#criteria.award}, '%')))\n" +
//           "         AND (:#{#criteria.publisher} IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :#{#criteria.publisher}, '%')))\n" +
//           "         AND (:#{#criteria.character} IS NULL OR LOWER(ch.name) LIKE LOWER(CONCAT('%', :#{#criteria.character}, '%')))\n" +
//           "         AND (:#{#criteria.setting} IS NULL OR LOWER(s.setting) LIKE LOWER(CONCAT('%', :#{#criteria.setting}, '%')))\n" +
//           "         AND (:#{#criteria.language} IS NULL OR LOWER(l.language) LIKE LOWER(CONCAT('%', :#{#criteria.language}, '%')))\n" +
//           "         AND (:#{#criteria.genre} IS NULL OR LOWER(g.genre) LIKE LOWER(CONCAT('%', :#{#criteria.genre}, '%')))\n"
    )
    Page<Book> findAll(BookSearchCriteria criteria, Pageable pageable);

}
