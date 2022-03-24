package com.ex.junit5lecture.service;

import com.ex.junit5lecture.domain.Member;
import com.ex.junit5lecture.domain.Study;
import com.ex.junit5lecture.domain.StudyStatus;
import com.ex.junit5lecture.exception.InvalidMemberException;
import com.ex.junit5lecture.repository.StudyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    @Mock StudyRepository studyRepository; // 에노테이션을 처리할 extension 필요
    @Mock MemberService memberService;

    Member member;
    Study study;

    @BeforeEach
    void initData() {
        member = new Member();
        member.setEmail("kim@gmail.com");
        member.setId(1L);
        study = new Study("study", 10);
    }

    @Test
    void createStudyServiceObjectTest() {
//        StudyRepository studyRepository = mock(StudyRepository.class);
//        MemberService memberService = mock(MemberService.class);

        StudyService studyService = new StudyService(memberService, studyRepository);

        assertNotNull(studyService);
    }

    @Test
    void stubbingTest() {
//        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        when(memberService.findById(anyLong())).thenReturn(Optional.of(member)); // stubbing

        StudyService studyService = new StudyService(memberService, studyRepository);

        studyService.createStudy(1L, study);
    }

    @Test
    void createStudyTest() {
        when(memberService.findById(anyLong())).thenReturn(Optional.of(member)); // stubbing
        when(studyRepository.save(study)).thenReturn(study); // stubbing

        StudyService studyService = new StudyService(memberService, studyRepository);
        studyService.createStudy(1L, study);

        assertNotNull(study.getOwner());
        assertEquals(member, study.getOwner());
    }

    @Test
    void notifyCallTest() throws InvalidMemberException {
        when(memberService.findById(anyLong())).thenReturn(Optional.of(member)); // stubbing
        when(studyRepository.save(study)).thenReturn(study); // stubbing

        StudyService studyService = new StudyService(memberService, studyRepository);
        studyService.createStudy(1L, study);


        // mocking 된 memberService 의 notify 메서드가 한 번 호출되는지 확인 times(1)
        verify(memberService, times(1)).notify(any());
        // validate 메서드가 호출되지 않았는지 확인 never()
        verify(memberService, never()).validate(anyLong());

        assertNotNull(study.getOwner());
        assertEquals(member, study.getOwner());
    }

    @Test
    void createStudyTestBddStyle() {
        // given
        given(memberService.findById(anyLong())).willReturn(Optional.of(member));
        given(studyRepository.save(any(Study.class))).willReturn(study);
        // when
        StudyService studyService = new StudyService(memberService, studyRepository);
        studyService.createStudy(member.getId(), study);
        // then
        then(memberService).should(times(1)).notify(any(Study.class));
        assertNotNull(study.getOwner());
        assertEquals(member, study.getOwner());
    }

    @Test
    void publishStudyTest() {
        // given
        given(studyRepository.save(any(Study.class))).willReturn(study);
        // when
        StudyService studyService = new StudyService(memberService, studyRepository);
        Study publishedStudy = studyService.publishStudy(this.study);
        //then
        then(memberService).should().notify(publishedStudy);
        assertNotNull(publishedStudy);
        assertEquals(StudyStatus.OPENED, publishedStudy.getStatus());
    }
}