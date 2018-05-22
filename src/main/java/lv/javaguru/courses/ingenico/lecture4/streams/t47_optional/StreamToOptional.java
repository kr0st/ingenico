package lv.javaguru.courses.ingenico.lecture4.streams.t47_optional;

import lv.javaguru.courses.ingenico.lecture4.common.User;
import lv.javaguru.courses.ingenico.lecture4.common.UserRepository;

import java.util.Optional;

public class StreamToOptional {

    public static void main(String[] args) {
        UserRepository userRepository = UserRepository.getInstance();
        User startsWithJo = userRepository.getAll()
                      .stream()
                      .filter(user -> user.getNickname().startsWith("jo"))
                      .findFirst().
                              map(user -> {
                                  user.setActive(false);
                                  return user;
                              }).orElseThrow(() -> new IllegalArgumentException("not found"));
    }

}
