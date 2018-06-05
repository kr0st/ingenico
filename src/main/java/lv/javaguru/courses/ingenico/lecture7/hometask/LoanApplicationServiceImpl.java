package lv.javaguru.courses.ingenico.lecture7.hometask;

import lv.javaguru.courses.ingenico.lecture7.hometask.dto.Decision;
import lv.javaguru.courses.ingenico.lecture7.hometask.dto.LoanApplicationDto;
import lv.javaguru.courses.ingenico.lecture7.hometask.exceptions.RiskValidationException;
import lv.javaguru.courses.ingenico.lecture7.hometask.model.Customer;
import lv.javaguru.courses.ingenico.lecture7.hometask.model.Loan;
import lv.javaguru.courses.ingenico.lecture7.hometask.model.LoanApplication;
import lv.javaguru.courses.ingenico.lecture7.hometask.repositories.CustomerRepository;
import lv.javaguru.courses.ingenico.lecture7.hometask.repositories.LoanApplicationRepository;
import lv.javaguru.courses.ingenico.lecture7.hometask.repositories.LoanRepository;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.DayOfWeek;
import java.time.Period;

public class LoanApplicationServiceImpl implements LoanApplicationService {

    private static final int MAX_PER_IP_IN_DAY = 3;

    private static final int NIGHT_RISK_UNTIL_HOUR = 6;

    private static final int WEEK = DayOfWeek.values().length;

    private TimeService timeService;

    private CustomerRepository customerRepository;

    private LoanRepository loanRepository;

    private LoanApplicationRepository loanApplicationRepository;

    @Override
    public Decision createLoan(LoanApplicationDto loanApplicationDto) {
        LoanApplication application = saveLoanApplication(loanApplicationDto);
        Customer customer = customerRepository.findById(loanApplicationDto.getCustomerId());
        if (loanRepository.existNotPayied(customer.getId())) {
            return Decision.DECLINED;
        }
        validateByIp(application.getIpAddress());
        validateAmountByTime(customer, application.getAmount());
        saveLoanApplication(loanApplicationDto);
        return Decision.APPROVED;
    }

    private LoanApplication saveLoanApplication(LoanApplicationDto dto) {
        // ID is auto generated key, that's why ID initialized with null
        // repository will return object with generated ID after save
        LoanApplication loanApplication = new LoanApplication(
                null,
                dto.getCustomerId(),
                dto.getAmount(),
                dto.getIpAddress(),
                timeService.now(),
                dto.getPayDate()
        );
        return loanApplicationRepository.save(loanApplication);
    }

    private void validateByIp(String ip) {
        int count = loanApplicationRepository.selectCountByIpAndDate(ip, timeService.now().toLocalDate());
        if (count > MAX_PER_IP_IN_DAY) {
            throw new RiskValidationException(RiskValidationException.Reason.TOO_MANY_APPLICATIONS_BY_IP);
        }
    }

    private void validateAmountByTime(Customer customer, BigDecimal amountToLoan) {
        int moreThanNightAmount = amountToLoan.compareTo(customer.getMaxPossibleLoanNightAmount());
        if (moreThanNightAmount < 1) {
            return;
        }
        int currentHour = timeService.now().getHour();
        if (currentHour < NIGHT_RISK_UNTIL_HOUR) {
            throw new RiskValidationException(RiskValidationException.Reason.ATTEMPT_TO_LOAN_TOO_MUCH_AT_NIGHT);
        }
    }

    private void createLoan(Customer customer, LoanApplication loanApplication) {
        // ID is auto generated key, that's why ID initialized with null
        // repository will return object with generated ID after save
        BigDecimal payAmount = calculatePayAmount(customer, loanApplication);
        Loan loan = new Loan(
                null,
                customer.getId(),
                loanApplication.getId(),
                loanApplication.getAmount(),
                payAmount,
                timeService.now(),
                loanApplication.getPayDate(),
                false
        );
        loanRepository.save(loan);
    }

    /**
     * Total amount to pay calculation.
     * Each week amount increasing on interest factor.
     * Example: loaned 100 for 21 day, interest factor 10% :
     * then:
     * 21 day = 3 weeks
     * 1. 100 + 100 * 10% = 110
     * 2. 110 + 110 * 10% = 121
     * 3. 121 + 121 * 10% = 133.1
     * total amount to pay = 131.1
     */
    private BigDecimal calculatePayAmount(Customer customer, LoanApplication la){
        BigDecimal amountToPay = la.getAmount();
        Period period = Period.between(la.getCreatedAt().toLocalDate(), la.getPayDate());
        int days = period.getDays();
        int weeks = days / WEEK;
        for (int i = 0; i < weeks; i++) {
            amountToPay = amountToPay.add(amountToPay.multiply(customer.getInterestFactor()));
        }
        return amountToPay.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
