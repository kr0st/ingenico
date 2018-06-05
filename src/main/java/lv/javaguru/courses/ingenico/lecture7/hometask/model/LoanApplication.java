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
public class LoanApplication {

    private Long id;
    private Long customerId;
    private BigDecimal amount;
    private String ipAddress;
    private LocalDateTime createdAt;
    private LocalDate payDate;

}
