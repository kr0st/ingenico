package lv.javaguru.courses.ingenico.lecture6.hometask.solution;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import sun.misc.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.RecursiveTask;

@Slf4j
public class RandomBytesGenerator extends RecursiveTask<byte[]> {

    private static final String url = "http://httpbin.org/bytes/";

    private static final int MAX_BYTES_PER_CALL = 20;

    private final int bytesToReceive;

    private HttpClient httpClient;

    public RandomBytesGenerator(int bytesToReceive) {
        this(bytesToReceive, HttpClientBuilder.create().build());
    }

    public RandomBytesGenerator(int bytesToReceive, HttpClient httpClient) {
        this.bytesToReceive = bytesToReceive;
        this.httpClient = httpClient;
    }

    @Override
    protected byte[] compute() {
        if (bytesToReceive <= MAX_BYTES_PER_CALL){
            return doHttpGetRequest();
        }
        int firstHalf = bytesToReceive / 2;
        RandomBytesGenerator subtask1 = new RandomBytesGenerator(firstHalf);
        subtask1.fork();
        RandomBytesGenerator subtask2 = new RandomBytesGenerator(bytesToReceive - firstHalf);
        byte[] right = subtask2.compute();
        byte[] left = subtask1.join();
        return mergeResults(left, right);
    }

    private byte[] doHttpGetRequest() {
        debug("bytes to receive : {}", bytesToReceive);
        String uri = url + bytesToReceive;
        HttpGet get = new HttpGet(uri);
        try {
            return httpClient.execute(get, response -> readContent(response.getEntity()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] readContent(HttpEntity httpEntity) throws IOException {
        InputStream content = httpEntity.getContent();
        return IOUtils.readFully(content, Integer.MAX_VALUE, true);
    }

    private byte[] mergeResults(byte[] left, byte[] right){
        byte[] result = new byte[left.length + right.length];
        System.arraycopy(left, 0, result, 0, left.length);
        System.arraycopy(right, 0, result, left.length, right.length);
        return result;
    }

    private void debug(String message, Object...args){
        if (log.isDebugEnabled()){
            log.debug(message, args);
        }
    }

}
