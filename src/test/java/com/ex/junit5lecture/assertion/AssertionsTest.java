package com.ex.junit5lecture.assertion;

import com.ex.junit5lecture.dummy.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertionsTest {

    @DisplayName("Assertion error message 1")
    @Test
    void assertionErrorMessageTest1() {
        Member member = new Member("kim", 27);

        assertEquals(26, member.getAge(), "age should be " + 27);
    }

    @DisplayName("Assertion error message 2")
    @Test
    void assertionErrorMessageTest2() {
        Member member = new Member("kim", 27);

        // 람다식을 이용해서 실패 메시지 지정시 assert 문이 실패할 때까지 생성 및 연산을 지연시킬 수 있음.
        assertEquals(26, member.getAge(), () -> "age should be " + 27);
    }

    @DisplayName("unused assertAll example")
    @Test
    void unusedAssertAllTest() {
        Member member = new Member("kim", 27);

        Assertions.assertThat(member.getName()).isEqualTo("lee"); // 테스트 실패.
        Assertions.assertThat(member.getAge()).isLessThan(26); // 위 실패로 실행되지 않음.

        // 실패해도 이후 테스트를 진행한 결과를 보고싶은 경우 assertAll
    }

    @DisplayName("assertAll example")
    @Test
    void assertAllTest() {
        Member member = new Member("kim", 27);

        assertAll(
                () -> Assertions.assertThat(member.getName()).isEqualTo("lee"),
                () -> Assertions.assertThat(member.getAge()).isLessThan(26)
        );

        // 두 테스트 모두 실패하는 테스트이지만 일단 모두 실행
    }
}
