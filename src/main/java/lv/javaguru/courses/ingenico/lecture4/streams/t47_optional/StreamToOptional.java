package lv.javaguru.courses.ingenico.lecture4.streams.t47_optional;

import lv.javaguru.courses.ingenico.lecture4.common.User;
import lv.javaguru.courses.ingenico.lecture4.common.UserRepository;

import java.util.Optional;

public class StreamToOptional {

    static UserRepository repository = UserRepository.getInstance();

    public static void main(String[] args) {
        UserRepository userRepository = UserRepository.getInstance();
        User startsWithJo =
                userRepository.getAll()
                              .stream()
                              .filter(user -> user.getNickname().startsWith("jo"))
                              .findFirst()
                              .map(user -> {
                                  user.setActive(false);
                                  return user;
                              }).orElseThrow(() -> new IllegalArgumentException("not found"));
    }

    static User getOrCreate(String nickname) {
        User u = repository.findByNickname(nickname);
        if (u == null) {
            return new User();
        }
        return u;
    }

    static User getOrCreate2(String nickname) {
        return Optional.of(nickname)
                       .filter(string -> !string.isEmpty())
                       .map(repository::findByNickname)
                       .orElse(new User());
    }

    static User getOrCreate3(String nickname) {
        return Optional.ofNullable(repository.findByNickname(nickname))
                       .orElseGet(User::new);
    }

    static User get(String nickname) {
        return Optional.ofNullable(repository.findByNickname(nickname))
                       .orElseThrow(() -> new IllegalArgumentException("not exist"));
    }

    static void print(String nickname) {
        Optional.ofNullable(repository.findByNickname(nickname))
                .ifPresent(System.out::println);
    }
}
