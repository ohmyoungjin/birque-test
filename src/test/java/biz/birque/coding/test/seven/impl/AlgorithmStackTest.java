package biz.birque.coding.test.seven.impl;

import biz.birque.coding.test.seven.Algorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AlgorithmStackTest {

    @Autowired
    private Algorithm algorithm;

    String parentheses;

    @BeforeEach
    void setup() {
        parentheses = "(()())()())((";
    }

    @Test
    void parentheses() {
        int count = algorithm.parentheses(parentheses);
        System.out.println(count);
    }
}