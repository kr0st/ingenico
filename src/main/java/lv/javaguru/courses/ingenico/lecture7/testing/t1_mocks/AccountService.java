package lv.javaguru.courses.ingenico.lecture7.testing.t1_mocks;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;

public class AccountService {

    public Optional<BigDecimal> findFixedCommission(String accountNumber){
        int randomValue = new Random().nextInt(50);
        BigDecimal commission = new BigDecimal(randomValue);
        return Optional.of(commission);
    }

}
