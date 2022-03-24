package com.ex.junit5lecture.assertion;

import com.ex.junit5lecture.dummy.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class AssertionsTest {

    Member member;

    @BeforeEach
    void createTestData() {
        member = new Member("kim", 27);
    }

    @DisplayName("Assertion error message 1")
    @Test
    void assertionErrorMessageTest1() {
        assertEquals(26, member.getAge(), "age should be " + 27);
    }

    @DisplayName("Assertion error message 2")
    @Test
    void assertionErrorMessageTest2() {
        // 람다식을 이용해서 실패 메시지 지정시 assert 문이 실패할 때까지 생성 및 연산을 지연시킬 수 있음.
        assertEquals(26, member.getAge(), () -> "age should be " + 27);
    }

    @DisplayName("unused assertAll example")
    @Test
    void unusedAssertAllTest() {
        Assertions.assertThat(member.getName()).isEqualTo("lee"); // 테스트 실패.
        Assertions.assertThat(member.getAge()).isLessThan(26); // 위 실패로 실행되지 않음.

        // 실패해도 이후 테스트를 진행한 결과를 보고싶은 경우 assertAll
    }

    @DisplayName("assertAll example")
    @Test
    void assertAllTest() {
        assertAll(
                () -> Assertions.assertThat(member.getName()).isEqualTo("lee"),
                () -> Assertions.assertThat(member.getAge()).isLessThan(26)
        );

        // 두 테스트 모두 실패하는 테스트이지만 일단 모두 실행
    }

    @DisplayName("assertTimeout example")
    @Test
    void assertTimeoutTest() {
        assertTimeout(Duration.ofMillis(100), () -> member.logic()); // 소요시간 확인
        // logic() 3000ms 소요
        // 100ms가 걸린 시점에섯 실패하는 테스트이지만 3000ms가 걸리는 logic()은 끝까지 실행
    }

    @DisplayName("assertTimeoutPreemptively example")
    @Test
    void assertTimeoutPreemptivelyTest() {
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> member.logic()); // 소요시간 확인
        // logic() 3000ms 소요
        // 100ms를 넘어서는 지점에서 logic()의 수행을 멈추고 테스트 실패 처리
        // 주의해서 사용 ...
    }


}
