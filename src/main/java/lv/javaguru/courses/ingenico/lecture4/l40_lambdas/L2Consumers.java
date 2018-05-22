package lv.javaguru.courses.ingenico.lecture4.l40_lambdas;

import lv.javaguru.courses.ingenico.lecture4.common.User;
import lv.javaguru.courses.ingenico.lecture4.common.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

public class L2Consumers {

    private static UserRepository repository = UserRepository.getInstance();

    public static void main(String[] args) {
        //todo : replace printDate with lambda
        Consumer<LocalDate> datePrinter = (date) ->  System.out.println(DateTimeFormatter.ISO_DATE.format(date));
        datePrinter.accept(LocalDate.now());

        //todo : replace createUser with method reference
        Consumer<String> userCreator = L2Consumers::createUser;
    }

    private static void printDate(LocalDate date) {
        System.out.println(DateTimeFormatter.ISO_DATE.format(date));
    }

    private static void createUser(String nickname) {
        User existing = repository.findByNickname(nickname);
        if (existing != null) {
            throw new IllegalArgumentException("nickname must be unique");
        }
        User newUser = new User(nickname, LocalDateTime.now(), true);
        repository.save(newUser);
    }

}
