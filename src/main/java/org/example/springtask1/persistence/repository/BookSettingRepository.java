package org.example.springtask1.persistence.repository;

import org.example.springtask1.persistence.entity.BookSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookSettingRepository extends JpaRepository<BookSetting, Long> {


}

