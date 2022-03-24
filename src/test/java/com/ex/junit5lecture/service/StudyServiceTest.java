package com.ex.junit5lecture.service;

import com.ex.junit5lecture.repository.StudyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    @Mock // 에노테이션을 처리할 extension 필요
    StudyRepository studyRepository;

    @Mock
    MemberService memberService;

    @Test
    void createStudyService() {
//        StudyRepository studyRepository = mock(StudyRepository.class);
//        MemberService memberService = mock(MemberService.class);

        StudyService studyService = new StudyService(memberService, studyRepository);

        assertNotNull(studyService);
    }
}