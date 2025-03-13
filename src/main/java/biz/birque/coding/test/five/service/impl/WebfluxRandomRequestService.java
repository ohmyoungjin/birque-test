package biz.birque.coding.test.five.service.impl;

import biz.birque.coding.test.dto.RandomDto;
import biz.birque.coding.test.five.service.RandomRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WebfluxRandomRequestService implements RandomRequestService {
    private final WebClient webClient;

    @Override
    public Map<Integer, List<String>> RandomResponse(int requestCount) {
        return Flux.range(1, requestCount)  // 1부터 100까지의 숫자 생성
                .flatMap(i -> webClient
                        .get()
                        .uri("http://codingtest.brique.kr:8080/random")  // 요청할 URI
                        .retrieve()
                        .bodyToMono(RandomDto.class))  // 응답을 RandomDto로 변환
                .collect(Collectors.groupingBy(
                        RandomDto::getId,  // id 기준으로 그룹화
                        Collectors.mapping(RandomDto::getQuote, Collectors.toList())
                ))
                .block()
                .entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size())) // count 기준 내림차순 정렬
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}
