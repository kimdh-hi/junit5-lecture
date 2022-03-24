package com.ex.junit5lecture.repository;

import com.ex.junit5lecture.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
}
