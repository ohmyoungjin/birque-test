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
    void 비동기_처리() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();

        CompletableFuture<String> future1 = asyncResponseService.asyncResponse("Ping");
        CompletableFuture<String> future2 = asyncResponseService.asyncResponse("Foobar");
        CompletableFuture<String> future3 = asyncResponseService.asyncResponse("Ping");

        System.out.println("Send " + future1.get());
        System.out.println("Send " + future2.get());
        System.out.println("Send " + future3.get());

        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + "ms");
    }

    @Test
    void 동기_처리() {
        long startTime = System.currentTimeMillis();

        String one = asyncResponseService.syncResponse("Ping");
        String two = asyncResponseService.syncResponse("Ping");
        String three = asyncResponseService.syncResponse("Ping");

        System.out.println("Send " + one);
        System.out.println("Send " + two);
        System.out.println("Send " + three);

        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + "ms");
    }

}