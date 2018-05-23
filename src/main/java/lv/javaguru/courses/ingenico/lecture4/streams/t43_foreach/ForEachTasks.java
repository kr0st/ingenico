package lv.javaguru.courses.ingenico.lecture4.streams.t43_foreach;


import lv.javaguru.courses.ingenico.lecture4.common.User;
import lv.javaguru.courses.ingenico.lecture4.common.UserRepository;

import java.util.List;

public class ForEachTasks {

    //todo : rewrite methods with streams
    public static void main(String[] args) {
        List<User> users = UserRepository.getInstance().getAll();

        users.stream().parallel().forEach(user -> user.setActive(false));
        users.forEach(System.out::println);
    }

    static void disableUsers(List<User> users){
        for (User user : users) {
            user.setActive(false);
        }
    }

    static void printUsersNicknames(List<User> users){
        for (User user : users) {
            System.out.println(user.getNickname());
        }
    }
}
