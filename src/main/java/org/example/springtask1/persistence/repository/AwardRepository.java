package org.example.springtask1.persistence.repository;

import org.example.springtask1.persistence.entity.Award;
import org.example.springtask1.persistence.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AwardRepository extends JpaRepository<Award, Long> {

    @Query("SELECT award " +
            "FROM Award award WHERE " +
            "award.award = :awardName")
    List<Award> findByAward(String awardName);

//    @Query("SELECT award " +
//            "FROM Award award WHERE " +
//            "award.award = :awardName AND award.year = :year")
//    List<Award> findByAwardAndYear(String awardName, Integer year);

    @Override
    List<Award> findAll();
}
