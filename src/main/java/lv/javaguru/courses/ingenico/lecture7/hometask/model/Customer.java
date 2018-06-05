package lv.javaguru.courses.ingenico.lecture7.hometask.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Customer {

    private Long id;
    private BigDecimal interestFactor;
    private BigDecimal maxPossibleLoanAmount;
    private BigDecimal maxPossibleLoanNightAmount;
}
