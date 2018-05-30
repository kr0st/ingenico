package lv.javaguru.courses.ingenico.lecture6.hometask.task2;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Transaction {

    private String id;
    private String cardId;
    private String beneficiaryAccount;
    private BigDecimal amount;

}
