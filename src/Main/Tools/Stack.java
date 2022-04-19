package Tools;

public class Stack<T> {
    private T[] stack;
    //is the position of the last element
    private int top;
    private int size;

    public Stack() {
        this.size = 10;
        this.stack = (T[])new Object[this.size];
        this.top = -1;
    }

    public void push(T o){
        if (size() == this.size)
            expandArray();
        this.stack[++this.top] = o;
    }

    public void pop(){
        //algoritmo de orden 1, siempre combiene ahorrar tiempo
        //en casos de datos que ocupen mas memoria sera visto mas adelante
        this.top--;
    }

    public T peek() {
        if (isEmpty())
            return null;
        return this.stack[this.top];}

    public boolean isEmpty() {return top == -1;}

    public int size() {return this.top;}

    private void expandArray(){
        T[] auxArray = (T[])new Object[size + 10];
        System.arraycopy(stack, 0, auxArray, 0, this.stack.length);
        this.size = auxArray.length;
        this.stack = auxArray;
    }
}
