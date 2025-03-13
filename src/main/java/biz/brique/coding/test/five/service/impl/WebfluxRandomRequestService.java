package biz.brique.coding.test.five.service.impl;

import biz.brique.coding.test.dto.RandomDto;
import biz.brique.coding.test.five.service.RandomRequestService;
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
    /* 요청 URI */
    final String REQUEST_URI = "http://codingtest.brique.kr:8080/random";

    @Override
    public Map<Integer, List<String>> RandomResponse(int requestCount) {
        return Flux.range(1, requestCount)
                .flatMap(i -> webClient
                        .get()
                        .uri(REQUEST_URI)
                        .retrieve()
                        .bodyToMono(RandomDto.class)) /* DTO 형태로 Mapping */
                .collect(Collectors.groupingBy(
                        RandomDto::getId, /* Id 값으로 group by */
                        Collectors.mapping(RandomDto::getQuote, Collectors.toList())
                ))
                .block()
                .entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()))  /* 가장 많이 나온숫자로 소팅 */
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new /* 정렬 저장이 필요하여 LinkedHashMap 사용*/
                ));
    }
}
