package biz.birque.coding.test.two.service;


import java.util.concurrent.CompletableFuture;

public interface AsyncResponseService {

    CompletableFuture<String> asyncResponse(String text);

    String syncResponse(String text);
}
