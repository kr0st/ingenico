package lv.javaguru.courses.ingenico.lecture6.hometask.task1;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Slf4j
public class HttpDOSer
{
    private String uri;

    public HttpDOSer(String uri)
    {
        this.uri = uri;
    }

    public String doEvil()
    {
        return doHttpGetRequest(uri);
    }

    private String doHttpGetRequest(String uri) {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(uri);
        try {
            return client.execute(get, response -> readContent(response.getEntity()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String readContent(HttpEntity httpEntity) throws IOException {
        InputStream content = httpEntity.getContent();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(content, StandardCharsets.US_ASCII))) {
            StringBuilder result = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                result.append((char) c);
            }
            return result.toString();
        }
    }

}
