package biz.brique.coding.test.five.service;

import java.util.List;
import java.util.Map;

/* 사용 기술이 달라질 수 있음에 따라 Interface 선언 */
public interface RandomRequestService {

    Map<Integer, List<String>> RandomResponse(int requestCount);
}
