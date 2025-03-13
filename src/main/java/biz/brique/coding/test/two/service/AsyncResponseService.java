package biz.brique.coding.test.two.service;


import java.util.concurrent.CompletableFuture;

/* 사용 기술이 달라질 수 있음에 따라 Interface 선언 */
public interface AsyncResponseService {

    CompletableFuture<String> asyncResponse(String text);

    String syncResponse(String text);
}
