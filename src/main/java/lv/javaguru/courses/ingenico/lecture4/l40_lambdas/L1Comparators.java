package lv.javaguru.courses.ingenico.lecture4.l40_lambdas;

import lv.javaguru.courses.ingenico.lecture4.common.User;
import lv.javaguru.courses.ingenico.lecture4.common.UserRepository;

import java.util.Comparator;
import java.util.List;

public class L1Comparators {

    public static void main(String[] args) {
        List<User> users = UserRepository.getInstance().getAll();

        //todo : sort by nickname with lambda
        System.out.println("---- sorted by nickname ---");
        Comparator<User> byNicknameComparator =
                (user1, user2) -> user1.getNickname().compareTo(user2.getNickname());
        users.sort(byNicknameComparator);
        System.out.println(users);

        //todo : sort by createdAt
        System.out.println("---- sorted by creation date ---");
        users.sort((user1, user2) -> user1.getCreatedAt().compareTo(user2.getCreatedAt()));
        System.out.println(users);
    }

}
