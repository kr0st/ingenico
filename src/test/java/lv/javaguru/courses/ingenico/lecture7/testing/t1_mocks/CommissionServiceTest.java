package lv.javaguru.courses.ingenico.lecture7.testing.t1_mocks;

import lv.javaguru.courses.ingenico.lecture7.testing.t0_simpletest.Calculator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.in;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyVararg;
import static org.mockito.Matchers.eq;

public class CommissionServiceTest {

    private AccountService accountService = Mockito.mock(AccountService.class);
    private Calculator calculator = Mockito.mock(Calculator.class);
    private CommissionService commissionService = new CommissionService(calculator, accountService);

    private ArgumentCaptor<BigDecimal> captor = ArgumentCaptor.forClass(BigDecimal.class);

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldReturnSumOfAmountAndCommission() throws Exception {
        BigDecimal commission = new BigDecimal("20");
        BigDecimal amount = new BigDecimal("1");
        String accountNumber = "no123";

        Mockito.when(accountService.findFixedCommission(accountNumber)).thenReturn(Optional.of(commission));
        Mockito.when(calculator.add(commission, amount)).thenReturn(new BigDecimal("21"));
        BigDecimal result = commissionService.applyFixedCommission("no123", amount);
        assertThat(result, closeTo(new BigDecimal("21"), new BigDecimal("0.00000001")));
    }

    @Test
    public void shouldThrowExceptionWhenCommissionNotFound() throws Exception {
        Mockito.when(accountService.findFixedCommission(anyString())).thenReturn(Optional.empty());
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("account not exist 666");
        commissionService.applyFixedCommission("666", new BigDecimal(1));

        Mockito.verify(calculator, Mockito.times(0)).add(any());
    }

    @Test
    public void shouldNotAddAdditionalValuesToCommisionBeforeCalculatorInvoked() throws Exception {
        BigDecimal commission = new BigDecimal(23);
        BigDecimal amount = new BigDecimal(1);
        Mockito.when(accountService.findFixedCommission(anyString())).thenReturn(Optional.of(commission));
        Mockito.when(calculator.add(any())).thenReturn(new BigDecimal("21"));

        commissionService.applyFixedCommission("acc",amount);
        Mockito.verify(calculator).add(captor.capture());
        List<BigDecimal> allValues = captor.getAllValues();
        assertThat(commission, in(allValues));
    }
}