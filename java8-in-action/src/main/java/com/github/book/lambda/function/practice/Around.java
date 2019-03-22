package com.github.book.lambda.function.practice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;

/**
 * <p>
 * 自定义function
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

        String oneLine = around.processFile(BufferedReader::readLine);
        System.out.println(oneLine);
        String twoLine = around.processFile((BufferedReader b) -> b.readLine() + b.readLine());
        System.out.println(twoLine);

        Function<BufferedReader, String> function = (BufferedReader b) -> {
            try {
                return b.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
        System.out.println(around.processFileByFun(function));

    }

    /**
     * prior java8
     *
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

    /**
     * java8
     *
     * @param p
     * @return
     * @throws Exception
     */
    private String processFile(BufferedReaderProcessor p) throws Exception {
        final String path = this.getClass().getClassLoader().getResource("").getPath();
        try (BufferedReader br =
                     new BufferedReader(new FileReader(path + "file/data.txt"))) {
            return p.process(br);
        }
    }

    /**
     * use function
     *
     * @param function
     * @return
     * @throws Exception
     */
    private String processFileByFun(Function<BufferedReader, String> function) throws Exception {
        final String path = this.getClass().getClassLoader().getResource("").getPath();
        try (BufferedReader br =
                     new BufferedReader(new FileReader(path + "file/data.txt"))) {
            return function.apply(br);
        }
    }

}
