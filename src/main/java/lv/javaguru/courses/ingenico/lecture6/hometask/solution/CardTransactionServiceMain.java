package lv.javaguru.courses.ingenico.lecture6.hometask.solution;

import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.courses.ingenico.lecture6.hometask.task2.CardLastTransactions;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CardTransactionServiceMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        ObjectMapper objectMapper = new ObjectMapper();
        CardTransactionService service = new CardTransactionService(httpClient, objectMapper);
        CompletableFuture<List<CardLastTransactions>> lastTransactions = service.getLastTransactions();
        List<CardLastTransactions> cardLastTransactions = lastTransactions.get();
        System.out.println(cardLastTransactions);
    }


}
