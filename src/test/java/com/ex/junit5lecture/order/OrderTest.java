package com.ex.junit5lecture.order;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 순서를 지키면서 유지해야 할 상태(필드)가 있을 때 사용
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // @Order 에너테이션 기반 순서지정
public class OrderTest {

    // 테스트 메서드의 실행순서는 선언순으로 동작하지만, 이 순서에 의존하면 안 된다. (완벽한 단위 테스트라면)
    // but, 어떤 시나리오 대로 흘러가야 하는 테스트가 있을 수 있다. 이때는 테스트의 순서를 명시적으로 설정해주자.

    @Order(2)
    @Test
    void test1() {
        System.out.println("test1");
    }

    @Order(1)
    @Test
    void test2() {
        System.out.println("test2");
    }
}
