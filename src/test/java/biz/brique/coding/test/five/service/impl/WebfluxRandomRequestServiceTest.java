package biz.brique.coding.test.five.service.impl;

import biz.brique.coding.test.five.service.RandomRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

@WebFluxTest(RandomRequestService.class)
class WebfluxRandomRequestServiceTest {
    private int requestCount;
    @Autowired
    RandomRequestService randomRequestService;
    @TestConfiguration
    static class WebClientTestConfig {
        @Bean
        public WebClient webClient() {
            return WebClient.builder()
                    .build();
        }
    }
    @BeforeEach
    void setUp() {
        requestCount = 132; //요청횟수 설정
    }


    @Test
    void 랜덤문구요청() {
        Map<Integer, List<String>> randomResponse = randomRequestService.RandomResponse(requestCount);
        int totalCount = randomResponse.values().stream()
                .mapToInt(List::size)
                .sum();
        randomResponse.entrySet().stream()
                .forEach(entry -> {
                    System.out.println("count: " + entry.getValue().size() + " id: " + entry.getKey() + " quote : " + entry.getValue());
                });
        System.out.println("TotalCount : " + totalCount);

        assertThat(totalCount).isEqualTo(requestCount); //요청 숫자와 토탈 숫자 검증


    }
}