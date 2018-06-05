package lv.javaguru.courses.ingenico.lecture7.hometask.repositories;

import lv.javaguru.courses.ingenico.lecture7.hometask.model.Loan;

import java.util.List;

public interface LoanRepository {

    Loan save(Loan loan);

    boolean existNotPayied(Long customerId);
}
