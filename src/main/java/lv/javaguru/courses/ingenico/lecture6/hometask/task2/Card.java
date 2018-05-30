package lv.javaguru.courses.ingenico.lecture6.hometask.task2;

import lombok.Data;

@Data
public class Card {

    private String id;
    private String pan;
    private Status status;
    private Integer expirationYear;
    private Integer expirationMonth;

    public enum Status {
        ACTIVE, BLOCKED
    }

}
