package lv.javaguru.courses.ingenico.lecture5.commoncode;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Message<T> {

    private UUID correlationId;

    private T payload;

}
