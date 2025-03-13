package biz.birque.coding.test.two.service;


import java.util.concurrent.CompletableFuture;

public interface AsyncResponseService {

    CompletableFuture<String> textResponse(String text);
}
