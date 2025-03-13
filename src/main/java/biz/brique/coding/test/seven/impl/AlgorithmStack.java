package biz.brique.coding.test.seven.impl;

import biz.brique.coding.test.seven.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Stack;

@Service
public class AlgorithmStack implements Algorithm {

    @Override
    public int parentheses(String problem) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxLength = 0;
        for (int i = 0; i < problem.length(); i++) {
            if (problem.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }
        return maxLength;
    }
}
