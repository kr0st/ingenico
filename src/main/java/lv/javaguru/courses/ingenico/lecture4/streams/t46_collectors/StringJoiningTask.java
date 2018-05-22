package lv.javaguru.courses.ingenico.lecture4.streams.t46_collectors;

import lv.javaguru.courses.ingenico.lecture4.common.User;
import lv.javaguru.courses.ingenico.lecture4.common.UserRepository;

import java.util.List;

public class StringJoiningTask {

    public static void main(String[] args) {
        List<User> users = UserRepository.getInstance().getAll();

        //todo: join all nicknames separated by ", " (john, peter, jose ...)
        String nicknames = null;
        System.out.println(nicknames);
    }

}
