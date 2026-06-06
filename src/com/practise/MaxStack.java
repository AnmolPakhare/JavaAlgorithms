package com.practise;

import java.util.Stack;

public class MaxStack {
    private Stack<Integer> stack;     // Main stack
    private Stack<Integer> maxStack;  // Keeps track of max at each level

    public MaxStack() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }

    // Push element x onto stack
    public void push(int x) {
        stack.push(x);
        if (maxStack.isEmpty()) {
            maxStack.push(x);
        } else {
            maxStack.push(Math.max(x, maxStack.peek()));
        }
    }

    // Remove and return the top element
    public int pop() {
        maxStack.pop(); // Remove top from max stack
        return stack.pop();
    }

    // Get the maximum element in the stack
    public int peekMax() {
        return maxStack.peek();
    }

    // Remove and return the max element
    public int popMax() {
        int max = maxStack.peek();
        Stack<Integer> temp = new Stack<>();

        // Pop elements until we find max
        while (stack.peek() != max) {
            temp.push(stack.pop());
            maxStack.pop();
        }

        // Remove max element
        stack.pop();
        maxStack.pop();

        // Restore elements from temp stack
        while (!temp.isEmpty()) {
            push(temp.pop()); // Push ensures maxStack remains correct
        }

        return max;
    }

    public static void main(String[] args) {
        MaxStack maxStack = new MaxStack();
        maxStack.push(5);
        maxStack.push(1);
        maxStack.push(5);
        System.out.println(maxStack.peekMax()); // 5
        System.out.println(maxStack.popMax()); // 5
        System.out.println(maxStack.peekMax()); // 5
        System.out.println(maxStack.pop());    // 1
        System.out.println(maxStack.pop());    // 5
    }
}
