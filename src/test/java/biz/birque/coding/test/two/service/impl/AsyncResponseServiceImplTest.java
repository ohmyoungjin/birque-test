package biz.birque.coding.test.two.service.impl;

import biz.birque.coding.test.two.service.AsyncResponseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AsyncResponseServiceImplTest {

    @Autowired
    private AsyncResponseService asyncResponseService;

    @Test
    void textResponse() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();

        CompletableFuture<String> future1 = asyncResponseService.textResponse("Ping");
        CompletableFuture<String> future2 = asyncResponseService.textResponse("Foobar");
        CompletableFuture<String> future3 = asyncResponseService.textResponse("Ping");

        System.out.println("Send " + future1.get());
        System.out.println("Send " + future2.get());
        System.out.println("Send " + future3.get());

        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + "ms");
    }

}