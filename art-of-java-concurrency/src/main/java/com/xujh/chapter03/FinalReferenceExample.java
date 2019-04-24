package com.xujh.chapter03;

/**
 * 不能保证writerTwo 的值能被reader看到
 */
public class FinalReferenceExample {
    final int[] intArray;
    static FinalReferenceExample obj;

    public FinalReferenceExample() {
        intArray = new int[1]; //1
        intArray[0] = 1; //2
    }

    public static void writerOne() { //
        obj = new FinalReferenceExample(); //3
    }

    public static void writerTwo() { //
        obj.intArray[0] = 2; //4
    }

    public static void reader() {
        if (obj != null) { //5
            int temp1 = obj.intArray[0]; //6
            System.out.println(temp1);
        }
    }

    public static void main(String[] args) {
        new Thread(FinalReferenceExample::writerOne).start();
        new Thread(FinalReferenceExample::writerTwo).start();
        new Thread(FinalReferenceExample::reader).start();

    }


}
