package lv.javaguru.courses.ingenico.lecture4.streams.t40_filtering;


import lv.javaguru.courses.ingenico.lecture4.common.User;
import lv.javaguru.courses.ingenico.lecture4.common.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class FilteringTasks {

    public static void main(String[] args) {
        UserRepository userRepository = UserRepository.getInstance();
        List<User> users = userRepository.getAll();

        //todo : filter users to get all users without facebook id
        List<User> withoutFacebookId =
                users.stream()
                .filter(user -> ((user.getFacebookId() == null) || (user.getFacebookId().compareTo("") == 0)))
                .collect(Collectors.toList());
        System.out.println("Users without facebook ids:");
        withoutFacebookId.stream()
                .forEach(user -> System.out.println(user.toString()));

        //todo : filter users to get all inactive users
        List<User> notActiveUsers =
                users.stream()
                .filter(user -> !user.isActive())
                .collect(Collectors.toList());

        System.out.println("Inactive users:");
        notActiveUsers.stream()
                .forEach(user -> System.out.println(user.toString()));

        //todo : filter users to get all users which have facebook and twitter id
        List<User> haveFacebookAndTwitterId =
                users.stream()
                .filter(user -> (user.getFacebookId() != null) && (!user.getFacebookId().isEmpty()) &&
                        (user.getTwitterId() != null) && (!user.getTwitterId().isEmpty()))
                .collect(Collectors.toList());

        System.out.println("Users who has both facebook and twitter accounts:");
        haveFacebookAndTwitterId.stream()
                .forEach(user -> System.out.println(user.toString()));
    }
}
