package lv.javaguru.courses.ingenico.lecture7.hometask.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loan {

    private Long id;
    private Long customerId;
    private Long loanApplicationId;
    private BigDecimal loanedAmount;
    private BigDecimal amountToPay;
    private LocalDateTime createdAt;
    private LocalDate expireAt;
    private boolean payed;

}
