package lv.javaguru.courses.ingenico.lecture7.hometask.repositories;

import lv.javaguru.courses.ingenico.lecture7.hometask.model.LoanApplication;

import java.time.LocalDate;
import java.util.List;

public interface LoanApplicationRepository {

    int selectCountByIpAndDate(String ipAddress, LocalDate localDate);

    LoanApplication save(LoanApplication loan);

}
