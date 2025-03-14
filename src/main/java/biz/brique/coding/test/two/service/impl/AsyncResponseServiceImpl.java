package biz.brique.coding.test.two.service.impl;

import biz.brique.coding.test.two.service.AsyncResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AsyncResponseServiceImpl implements AsyncResponseService {

    private final String PING = "Ping";
    private final String PONG = "Pong";
    final Long TREAD_SLEEP_TIME = 3000L;

    /* 비동기 처리 */
    @Async
    @Override
    public CompletableFuture<String> asyncResponse(String text) {
        System.out.println("Received : " + text);
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(TREAD_SLEEP_TIME);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return "Error: " + e.getMessage();
            }
            return PING.equals(text) ? PONG : text;
        });
    }

    /* 동기 처리 */
    @Override
    public String syncResponse(String text) {
        try {
            System.out.println("Received : " + text );
            Thread.sleep(TREAD_SLEEP_TIME);
            return PING.equals(text) ? PONG : text;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread was interrupted", e);
        }
    }
}
