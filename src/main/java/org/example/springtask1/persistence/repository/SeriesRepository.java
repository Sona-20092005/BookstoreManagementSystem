package org.example.springtask1.persistence.repository;

import org.example.springtask1.persistence.entity.Genre;
import org.example.springtask1.persistence.entity.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
    Series findBySeries(String seriesName);

    @Override
    List<Series> findAll();
}
