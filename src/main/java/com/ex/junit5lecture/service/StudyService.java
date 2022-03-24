package com.ex.junit5lecture.service;

import com.ex.junit5lecture.domain.Member;
import com.ex.junit5lecture.domain.Study;
import com.ex.junit5lecture.exception.MemberNotFoundException;
import com.ex.junit5lecture.repository.StudyRepository;

public class StudyService {

    private final MemberService memberService;
    private final StudyRepository studyRepository;

    public StudyService(MemberService memberService, StudyRepository studyRepository) {
        assert memberService != null;
        assert studyRepository != null;

        this.memberService = memberService;
        this.studyRepository = studyRepository;
    }

    public Study createStudy(Long memberId, Study study) {

        Member member = memberService.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException("member does not exists. id=" + memberId)
        );

        study.setOwner(member);
        memberService.notify(study);
        return studyRepository.save(study);
    }
}
