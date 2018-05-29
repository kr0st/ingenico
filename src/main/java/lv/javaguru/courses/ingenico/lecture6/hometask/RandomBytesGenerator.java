package lv.javaguru.courses.ingenico.lecture6.hometask;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.RecursiveTask;

@Slf4j
public class RandomBytesGenerator extends RecursiveTask<byte[]> {

    /*  <pre> {@code
 * class Fibonacci extends RecursiveTask<Integer> {
 *   final int n;
 *   Fibonacci(int n) { this.n = n; }
 *   Integer compute() {
 *     if (n <= 1)
 *       return n;
 *     Fibonacci f1 = new Fibonacci(n - 1);
 *     f1.fork();
 *     Fibonacci f2 = new Fibonacci(n - 2);
 *     return f2.compute() + f1.join();
 *   }
 */

    private static final String url = "http://httpbin.org/bytes/";

    private static final int MAX_BYTES_PER_CALL = 20;

    private final int bytesToReceive;

    public RandomBytesGenerator(int bytesToReceive) {
        this.bytesToReceive = bytesToReceive;
    }

    @Override
    protected byte[] compute() {
        return null;
    }
}
