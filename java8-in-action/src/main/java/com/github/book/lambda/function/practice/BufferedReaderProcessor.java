package com.github.book.lambda.function.practice;

import java.io.BufferedReader;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
@FunctionalInterface
public interface BufferedReaderProcessor {

    /**
     * process
     *
     * @param bufferedReader
     * @exception
     * @return
     */
    String process(BufferedReader bufferedReader) throws Exception;

}
