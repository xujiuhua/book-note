package com.github.book.lambda.function.practice;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * <p>
 * 场景描述：读取文件，行数不确定
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class Around {

    public static void main(String[] args) throws Exception{

        Around around = new Around();
        System.out.println(around.processFile());
        System.out.println(around.processTwoFile());

        String oneLine = around.processFile((BufferedReader b) -> b.readLine());
        System.out.println(oneLine);
        String twoLine = around.processFile((BufferedReader b) -> b.readLine() + b.readLine());
        System.out.println(twoLine);

    }

    /**
     * prior java8
     * <p>
     * 此代码局限性，只能读取一行;如果需要读取两行，则需要更改方法
     *
     * @return
     */
    private String processFile() throws Exception {
        final String path = this.getClass().getClassLoader().getResource("").getPath();

        try (BufferedReader br =
                     new BufferedReader(new FileReader(path + "file/data.txt"))) {
            return br.readLine();
        }
    }

    private String processTwoFile() throws Exception {
        final String path = this.getClass().getClassLoader().getResource("").getPath();

        try (BufferedReader br =
                     new BufferedReader(new FileReader(path + "file/data.txt"))) {
            return br.readLine() + br.readLine();
        }
    }

    private String processFile(BufferedReaderProcessor p) throws Exception {
        final String path = this.getClass().getClassLoader().getResource("").getPath();
        try (BufferedReader br =
                     new BufferedReader(new FileReader(path + "file/data.txt"))) {
            return p.process(br);
        }
    }

}
