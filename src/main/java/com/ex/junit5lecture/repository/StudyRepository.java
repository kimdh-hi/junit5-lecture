package com.ex.junit5lecture.repository;

import com.ex.junit5lecture.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {
}
