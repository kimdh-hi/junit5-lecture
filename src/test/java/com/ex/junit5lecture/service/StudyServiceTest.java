package com.ex.junit5lecture.service;

import com.ex.junit5lecture.domain.Member;
import com.ex.junit5lecture.domain.Study;
import com.ex.junit5lecture.repository.StudyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    @Mock StudyRepository studyRepository; // 에노테이션을 처리할 extension 필요

    @Mock MemberService memberService;

    @Test
    void createStudyServiceObjectTest() {
//        StudyRepository studyRepository = mock(StudyRepository.class);
//        MemberService memberService = mock(MemberService.class);

        StudyService studyService = new StudyService(memberService, studyRepository);

        assertNotNull(studyService);
    }

    @Test
    void stubbingTest() {
        Member member = new Member();
        member.setEmail("kim@gmail.com");
        member.setId(1L);
        Study study = new Study("study", 10);

//        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        when(memberService.findById(anyLong())).thenReturn(Optional.of(member)); // stubbing

        StudyService studyService = new StudyService(memberService, studyRepository);

        studyService.createStudy(1L, study);
    }

    @Test
    void createStudyTest() {
        Member member = new Member();
        member.setId(1L);
        member.setEmail("kim@gmail.com");
        Study study = new Study("test", 10);

        when(memberService.findById(anyLong())).thenReturn(Optional.of(member)); // stubbing
        when(studyRepository.save(study)).thenReturn(study); // stubbing

        StudyService studyService = new StudyService(memberService, studyRepository);
        studyService.createStudy(1L, study);

        assertNotNull(study.getOwner());
        assertEquals(member, study.getOwner());
    }
}