package biz.birque.coding.test.five.controller;

import biz.birque.coding.test.five.service.RandomRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class RanDomRequestController {
    private final RandomRequestService randomRequestService;

    @GetMapping("/five/{requestCount}")
    public ResponseEntity<Map<Integer, List<String>>> randomRequest(@PathVariable("requestCount") Integer requestCount) {
        return new ResponseEntity<>(randomRequestService.RandomResponse(requestCount), HttpStatus.OK);
    }
}
