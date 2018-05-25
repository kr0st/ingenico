package lv.javaguru.courses.ingenico.lecture4.streams.t46_collectors.solution;

import lv.javaguru.courses.ingenico.lecture4.common.User;
import lv.javaguru.courses.ingenico.lecture4.common.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class StringJoiningTask {

    public static void main(String[] args) {
        List<User> users = UserRepository.getInstance().getAll();

        //todo: join all nicknames separated by ", " (john, peter, jose ...)
        String nicknames = users.stream()
                                .map(User::getNickname)
                                .collect(Collectors.joining(", "));
        System.out.println(nicknames);
    }

}
