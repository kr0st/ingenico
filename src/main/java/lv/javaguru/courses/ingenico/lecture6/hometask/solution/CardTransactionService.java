package lv.javaguru.courses.ingenico.lecture6.hometask.solution;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;
import lv.javaguru.courses.ingenico.lecture6.hometask.task2.Card;
import lv.javaguru.courses.ingenico.lecture6.hometask.task2.CardLastTransactions;
import lv.javaguru.courses.ingenico.lecture6.hometask.task2.Links;
import lv.javaguru.courses.ingenico.lecture6.hometask.task2.Transaction;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class CardTransactionService {

    private static final String URL = "http://www.mocky.io/v2/";

    private static final String LINKS_ID = "5b0e42cd3200005300c19948";

    private static final CollectionType LIST_OF_CARD_TYPE;

    private static final CollectionType LIST_OF_TRANSACTIONS_TYPE;

    private static final JavaType LINKS_TYPE;

    static {
        TypeFactory typeFactory = TypeFactory.defaultInstance();
        LIST_OF_CARD_TYPE = typeFactory.constructCollectionType(ArrayList.class, Card.class);
        LIST_OF_TRANSACTIONS_TYPE = typeFactory.constructCollectionType(ArrayList.class, Transaction.class);
        LINKS_TYPE = typeFactory.constructType(Links.class);
    }

    private HttpClient httpClient;

    private ObjectMapper objectMapper;

    public CardTransactionService(HttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    public CompletableFuture<List<CardLastTransactions>> getLastTransactions() {
        CompletableFuture<Links> linksFuture = CompletableFuture.supplyAsync(this::getLinks);
        CompletableFuture<List<Card>> cardsFuture = linksFuture.thenApply(links -> getCards(links.getCards()));
        CompletableFuture<List<Transaction>> transactionsFuture = linksFuture.thenApply(links -> getTransactions(links.getTransactions()));
        return cardsFuture.thenCombine(transactionsFuture, this::mapCardsWithTransactions)
                          .thenApply(this::reduceCardsWithTransactions)
                          .exceptionally(this::onException);
    }

    private Links getLinks() {
        return doHttpRequest(HttpGet::new, LINKS_ID, LINKS_TYPE);
    }

    private List<Card> getCards(String id) {
        return doHttpRequest(HttpPost::new, id, LIST_OF_CARD_TYPE);
    }

    private List<Transaction> getTransactions(String id) {
        return doHttpRequest(HttpPost::new, id, LIST_OF_TRANSACTIONS_TYPE);
    }

    private <T> T doHttpRequest(Function<String, HttpUriRequest> httpUriRequest, String linkId, JavaType javaType) {
        HttpUriRequest request = httpUriRequest.apply(URL + linkId);
        try {
            return httpClient.execute(request, response -> objectMapper.readValue(response.getEntity().getContent(), javaType));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<Card, List<Transaction>> mapCardsWithTransactions(List<Card> cards, List<Transaction> transactions) {
        return transactions.stream()
                           .collect(Collectors.groupingBy(
                                   transaction -> cards.stream()
                                                       .filter(card -> card.getId().equals(transaction.getCard()))
                                                       .findFirst()
                                                       .get()
                           ));
    }

    private List<CardLastTransactions> reduceCardsWithTransactions(Map<Card, List<Transaction>> cardsWithTransactions) {
        return cardsWithTransactions.entrySet()
                                    .stream()
                                    .map(entry -> convertOneCardWithTransactions(entry.getKey(), entry.getValue()))
                                    .collect(Collectors.toList());
    }

    private CardLastTransactions convertOneCardWithTransactions(Card card, List<Transaction> transactions) {
        BigDecimal totalAmount = sumTotalTransactionAmount(transactions);
        CardLastTransactions cardLastTransactions = new CardLastTransactions();
        cardLastTransactions.setCard(card);
        cardLastTransactions.setTransactions(transactions);
        cardLastTransactions.setTotalAmount(totalAmount);
        return cardLastTransactions;
    }

    private BigDecimal sumTotalTransactionAmount(List<Transaction> transactions) {
        return transactions.stream()
                           .map(Transaction::getAmount)
                           .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<CardLastTransactions> onException(Throwable t) {
        log.error("receive last transactions failed with", t);
        return Collections.emptyList();
    }
}
