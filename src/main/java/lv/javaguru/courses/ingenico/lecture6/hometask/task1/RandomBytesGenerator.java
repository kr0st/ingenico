package lv.javaguru.courses.ingenico.lecture6.hometask.task1;

import javafx.beans.binding.ObjectExpression;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.RecursiveTask;

@Slf4j
public class RandomBytesGenerator extends RecursiveTask<char[]> {

    private static final String url = "http://httpbin.org/bytes/";

    private static final int MAX_BYTES_PER_CALL = 20;

    private final int bytesToReceive;

    public RandomBytesGenerator(int bytesToReceive) {
        this.bytesToReceive = bytesToReceive;
    }

    @Override
    protected char[] compute()
    {
        if (bytesToReceive <= 0)
            return null;

        HttpDOSer ddoser = new HttpDOSer(url.concat((new Integer(MAX_BYTES_PER_CALL)).toString()));
        String res = ddoser.doEvil();

        log.info(res);

        int left_bytes = bytesToReceive - res.length();

        RandomBytesGenerator next = new RandomBytesGenerator(left_bytes);
        next.fork();

        char[] returned = next.join();

        if (returned != null)
        {
            return res.concat(returned.toString()).toCharArray();
        }
        else
        {
            return res.toCharArray();
        }
    }
}
