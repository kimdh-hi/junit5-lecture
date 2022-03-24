package com.ex.junit5lecture.service;

import com.ex.junit5lecture.domain.Member;
import com.ex.junit5lecture.exception.InvalidMemberException;
import com.ex.junit5lecture.exception.MemberNotFoundException;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId);
    void validate(Long memberId) throws InvalidMemberException;
}
