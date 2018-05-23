package lv.javaguru.courses.ingenico.lecture4.l40_lambdas;

import lv.javaguru.courses.ingenico.lecture4.common.User;
import lv.javaguru.courses.ingenico.lecture4.common.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class L3Functions {

    private static UserRepository userRepository = UserRepository.getInstance();

    public static void main(String[] args) {
        //todo : replace formatDate with method reference
        Function<LocalDate, String> dateFormatter = DateTimeFormatter.ISO_DATE::format;

        //todo : replace getUsersCreatedAfter with lambda
        Function<LocalDateTime, List<User>> usersFinder = (createdAfter) -> {
            List<User> allUsers = userRepository.getAll();
            List<User> found = new ArrayList<>();
            for (User user : allUsers) {
                if (user.getCreatedAt().isAfter(createdAfter)) {
                    found.add(user);
                }
            }
            return found;
        };

        Function<String, User> byFacebookIdFinder = userRepository::findByFacebookId;
    }
}
