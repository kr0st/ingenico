package lv.javaguru.courses.ingenico.lecture4.streams.t40_filtering.solution;


import lv.javaguru.courses.ingenico.lecture4.common.User;
import lv.javaguru.courses.ingenico.lecture4.common.UserRepository;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilteringTasks {

    public static void main(String[] args) {
        UserRepository userRepository = UserRepository.getInstance();
        List<User> users = userRepository.getAll();

        //todo : filter users to get all users without facebook id
        List<User> withoutFacebookId = filterUsers(users, user -> user.getFacebookId() == null);

        //todo : filter users to get all inactive users
        List<User> notActiveUsers = filterUsers(users, user -> !user.isActive());

        //todo : filter users to get all users which have facebook and twitter id
        List<User> haveFacebookAndTwitterId = filterUsers(users, user -> user.getFacebookId() != null && user.getTwitterId() != null);
    }

    static List<User> filterUsers(List<User> users, Predicate<User> comparator) {
        return users.stream()
                    .filter(comparator)
                    .collect(Collectors.toList());
    }
}
