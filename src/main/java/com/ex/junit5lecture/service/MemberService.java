package com.ex.junit5lecture.service;

import com.ex.junit5lecture.domain.Member;
import com.ex.junit5lecture.exception.InvalidMemberException;
import com.ex.junit5lecture.exception.MemberNotFoundException;

public interface MemberService {

    Member findById(Long memberId) throws MemberNotFoundException;
    void validate(Long memberId) throws InvalidMemberException;
}
