package oop;

import java.util.Stack;

/**
 * Solution #1: stack and min stack
 */
/*
public class MinStack {

    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    public void push(int val) {
        stack.push(val);
        int nextMin = minStack.empty() ? val : Math.min(minStack.peek(), val);
        minStack.push(nextMin);
    }
    public void pop() {
        stack.pop();
        minStack.pop();
    }
    public int top() {
        return stack.peek();
    }
    public int getMin() {
        return minStack.peek();
    }

}
*/

/**
 * Solution #2: optimize min stack
 */
public class MinStack {

    private Stack<Integer> stack;
    private Stack<int[]> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<int[]>();
    }
    public void push(int val) {
        stack.push(val);
        if (minStack.empty()) {
            minStack.push(new int[]{val, 1});
        } else {
            if (val >= minStack.peek()[0]) {
                minStack.peek()[1]++;
            } else {
                minStack.push(new int[]{val, 1});
            }
        }
    }
    public void pop() {
        stack.pop();
        if (minStack.peek()[1] == 1) minStack.pop();
        else minStack.peek()[1]--;
    }
    public int top() {
        return stack.peek();
    }
    public int getMin() {
        return minStack.peek()[0];
    }

}
