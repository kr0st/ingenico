package lv.javaguru.courses.ingenico.lecture4.streams.t40_filtering;


import lv.javaguru.courses.ingenico.lecture4.common.User;
import lv.javaguru.courses.ingenico.lecture4.common.UserRepository;

import java.util.List;

public class FilteringTasks {

    public static void main(String[] args) {
        UserRepository userRepository = UserRepository.getInstance();
        List<User> users = userRepository.getAll();

        //todo : filter users to get all users without facebook id
        List<User> withoutFacebookId = null;

        //todo : filter users to get all inactive users
        List<User> notActiveUsers = null;

        //todo : filter users to get all users which have facebook and twitter id
        List<User> haveFacebookAndTwitterId = null;
    }
}
