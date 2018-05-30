package lv.javaguru.courses.ingenico.lecture6.hometask.task2;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CardLastTransactions {

    private Card card;
    private List<Transaction> transactions;
    private BigDecimal totalAmount;

}
