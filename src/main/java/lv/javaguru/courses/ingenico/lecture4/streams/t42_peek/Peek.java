package lv.javaguru.courses.ingenico.lecture4.streams.t42_peek;

import lv.javaguru.courses.ingenico.lecture4.common.User;
import lv.javaguru.courses.ingenico.lecture4.common.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Peek {

    // use peek for debugging only!
    public static void main(String[] args) {
        List<String> strings = UserRepository.getInstance()
                                             .getAll()
                                             .stream()
                                             .peek(user -> System.out.println("convert user : " + user))
                                             .map(User::getNickname)
                                             .peek(nickname -> System.out.println("nickname : " + nickname))
                                             .collect(Collectors.toList());

        System.out.println(strings);
    }

    //todo : rewrite method with stream
    static List<String> getAllNicknames() {
        List<User> users = UserRepository.getInstance().getAll();
        List<String> nicknames = new ArrayList<>();
        for (User user : users) {
            System.out.println("convert user : " + user);
            String nickname = user.getNickname();
            System.out.println("nickname : " + nickname);
            nicknames.add(nickname);
        }
        return nicknames;
    }

}
