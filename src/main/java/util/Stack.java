package util;

import java.util.Arrays;

public class Stack<T> {

    private Object[] data;
    private int size = 16;
    private int tail = 0;

    public Stack() {
        this.data = new Object[this.size];
    }

    public Stack(int size) {
        this.size = size;
        this.data = new Object[this.size];
    }

    public T pop() {
        if (this.tail == 0) return null;
        return (T)this.data[--tail];
    }

    public void push(T data) {
        if (tail == size-1) {
            size *= 2;
            this.data = Arrays.copyOf(this.data, size);
        }
        this.data[tail++] = data;
    }

}
