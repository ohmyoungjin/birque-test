package biz.birque.coding.test.two.controller;

import biz.birque.coding.test.two.service.AsyncResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.CompletableFuture;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AsyncResponseController {

    private final AsyncResponseService asyncResponseService;

    @GetMapping("/test-two")
    public String testTwo() {
        return "TestTwo";
    }

    @ResponseBody
    @GetMapping("/test-two/{text}")
    public ResponseEntity<CompletableFuture<String>> request(@PathVariable("text") String text) {
        log.info("text={}", text);
        return new ResponseEntity<>(asyncResponseService.textResponse(text), HttpStatus.OK);
    }


}
