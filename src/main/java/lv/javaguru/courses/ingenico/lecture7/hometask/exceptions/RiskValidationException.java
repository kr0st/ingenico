package lv.javaguru.courses.ingenico.lecture7.hometask.exceptions;

import lombok.Getter;

public class RiskValidationException extends RuntimeException {

    @Getter
    private Reason reason;

    public RiskValidationException(Reason reason) {
        this.reason = reason;
    }

    public enum Reason {
        TOO_MANY_APPLICATIONS_BY_IP,
        ATTEMPT_TO_LOAN_TOO_MUCH_AT_NIGHT
    }
}
