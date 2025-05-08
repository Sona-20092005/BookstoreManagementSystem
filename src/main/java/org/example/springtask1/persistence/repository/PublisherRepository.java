package org.example.springtask1.persistence.repository;

import org.example.springtask1.persistence.entity.Author;
import org.example.springtask1.persistence.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    Publisher findByName(String name);

    @Override
    List<Publisher> findAll();
}
