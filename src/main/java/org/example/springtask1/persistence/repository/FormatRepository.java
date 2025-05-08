package org.example.springtask1.persistence.repository;

import org.example.springtask1.persistence.entity.Format;
import org.example.springtask1.persistence.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormatRepository extends JpaRepository<Format, Long> {
    Format findByFormat(String formatName);

    @Override
    List<Format> findAll();
}
