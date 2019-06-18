package com.xujh.heap;


import java.util.Arrays;

/**
 * 堆的实现类
 *
 * @author Administrator
 */
public class Heap {
    private Node[] heapArray;
    private int maxSize;
    private int currentSize;

    public Heap(int mx) {
        maxSize = mx;
        heapArray = new Node[maxSize];
        currentSize = 0;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean add(int key) {
        if (currentSize == maxSize) {
            return false;
        }
        Node node = new Node(key);
        heapArray[currentSize] = node;
        trickleUp(currentSize++);
        return true;
    }

    public void trickleUp(int index) {
        int parent = (index - 1) / 2;
        Node node = heapArray[index];
        while (index > 0 && heapArray[parent].getKey() < node.getKey()) {
            heapArray[index] = heapArray[parent];
            index = parent;
            parent = (parent - 1) / 2;
        }
        heapArray[index] = node;
    }

    public Node remove() {
        Node root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }

    public void trickleDown(int index) {
        int largeChild;
        Node top = heapArray[index];
        while (index < currentSize / 2) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            if (rightChild < currentSize && heapArray[leftChild].getKey() < heapArray[rightChild].getKey())
                largeChild = rightChild;
            else
                largeChild = leftChild;
            if (top.getKey() >= heapArray[largeChild].getKey())
                break;
            heapArray[index] = heapArray[largeChild];
            index = largeChild;
        }
        heapArray[index] = top;
    }

    public boolean change(int index, int newvalue) {
        if (index < 0 || index >= currentSize)
            return false;
        int oldvalue = heapArray[index].getKey();
        heapArray[index].setKey(newvalue);
        if (oldvalue < newvalue)
            trickleUp(index);
        else
            trickleDown(index);
        return true;
    }

    public void displayHeap() {
        System.out.print("heapArray:");
        for (int i = 0; i < currentSize; i++) {
            if (heapArray[i] != null)
                System.out.print(heapArray[i].getKey() + "  ");
            else
                System.out.print("--");
        }
        int nBlanks = 32;
        int itemsPerrow = 1;
        int column = 0;
        int j = 0;
        System.out.println();
        String dots = "........................";
        System.out.println(dots + dots);
        while (currentSize > 0) {
            if (column == 0)
                for (int i = 0; i < nBlanks; i++) {
                    System.out.print(" ");
                }
            System.out.print(heapArray[j].getKey());
            if (++j == currentSize)
                break;
            if (++column == itemsPerrow) {
                nBlanks /= 2;
                itemsPerrow *= 2;
                column = 0;
                System.out.println();
            } else
                for (int i = 0; i < nBlanks * 2 - 2; i++)
                    System.out.print(' ');
        }
        System.out.println("\n" + dots + dots);
    }

    @Override
    public String toString() {
        return "Heap{" +
                "heapArray=" + Arrays.toString(heapArray) +
                ", maxSize=" + maxSize +
                ", currentSize=" + currentSize +
                '}';
    }

    public static void main(String[] args) {
        Heap h = new Heap(10);
        h.add(10);
        h.add(20);
        h.add(30);
        h.add(40);
        h.add(50);
        h.change(1, 40);
        h.remove();
        h.displayHeap();
    }
}