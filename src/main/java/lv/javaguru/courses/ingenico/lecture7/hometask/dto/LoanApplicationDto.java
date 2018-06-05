package lv.javaguru.courses.ingenico.lecture7.hometask.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LoanApplicationDto {

    private Long customerId;
    private BigDecimal amount;
    private String ipAddress;
    private LocalDate payDate;
}
