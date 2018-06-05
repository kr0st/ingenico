package lv.javaguru.courses.ingenico.lecture7.hometask;

import lv.javaguru.courses.ingenico.lecture7.hometask.dto.Decision;
import lv.javaguru.courses.ingenico.lecture7.hometask.dto.LoanApplicationDto;

public interface LoanApplicationService {

    Decision createLoan(LoanApplicationDto loanApplicationDto);

}
