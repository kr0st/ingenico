package lv.javaguru.courses.ingenico.lecture7.testing.t1_mocks;

import lv.javaguru.courses.ingenico.lecture7.testing.t0_simpletest.Calculator;

import java.math.BigDecimal;

public class CommissionService {

    private Calculator calculator;

    private AccountService accountService;

    public CommissionService(Calculator calculator, AccountService accountService) {
        this.calculator = calculator;
        this.accountService = accountService;
    }

    public BigDecimal applyFixedCommission(String accountNumber, BigDecimal amount) {
        return accountService.findFixedCommission(accountNumber)
                             .map(commission -> calculator.add(commission, amount))
                             .orElseThrow(() -> new IllegalArgumentException("account not exist " + accountNumber));
    }

}
