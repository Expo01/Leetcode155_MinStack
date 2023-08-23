import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}


// thoughts. can store min where initial value of max and then any new min found, update min
// problem if min node removed, how do we know what new min is? would need a duplicate linked list
// wich is ordered and would be O(n) to insert new Node into stored stored linked list  which is not likely the solution

// option 2 could make a record of min values with nested hashmap where min is the only outer key 'min' and inner map
// is key of sorted lowest to highest and the value is the number . so 'min, <placeNumber, value>' but this doesn't
// work either since any time a lower number is found, the inner key must change for all inner map items

// the problem is really needing a sorting mechanism that isn't slow to retieve the next min to assign it as new
// min when prior min popped off stack






// solution 1 involves storing a 2-item array as each stack item where index 0 is the new number and index 1 will be the
// min in stack as of that number
class MinStack {

    private Stack<int[]> stack = new Stack<>();

    public MinStack() { }

    public void push(int x) {

        if (stack.isEmpty()) {
            stack.push(new int[]{x, x}); // if empty, first item must be min
            return;
        }

        int currentMin = stack.peek()[1]; // peeks index 1 for current min
        stack.push(new int[]{x, Math.min(x, currentMin)}); // store new number and min as either current value or maintain prior min
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek()[0]; // peeks index 0 for last pushed value
    }

    public int getMin() {
        return stack.peek()[1];
    }
}

///////////////////////////////////////////////

// 2 stack variant, reduces repeated storing of min where duplicate min only stored when duplicate is pushed onto main stack
// technically has better memory efficiency even though a second stack is created
class MinStack {

    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public MinStack() { }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || x <= minStack.peek()) { // pushed only if less than or duplicate of min
            minStack.push(x);
        }
    }

    public void pop() {
        if (stack.peek().equals(minStack.peek())) { // if duplicate is popped from stack, then dupliate also popped frmo min stack
            minStack.pop();
        }
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

