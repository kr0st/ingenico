package lv.javaguru.courses.ingenico.lecture4.l40_lambdas;


import lv.javaguru.courses.ingenico.lecture4.common.User;
import lv.javaguru.courses.ingenico.lecture4.common.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class L0Suppliers {

    private static UserRepository userRepository = UserRepository.getInstance();

    public static void main(String[] args) {
        //todo : replace generateNumber with lambda
        Supplier<Integer> numberGenerator = () -> new Random().nextInt();

        L0Suppliers l0Suppliers = new L0Suppliers();
        Supplier<Integer> numberGenerator2 = l0Suppliers::generateNumber;
        Integer integer = numberGenerator.get();

        //todo : replace userRepository.getAll() with method reference
        List<User> allUsers = userRepository.getAll();
        Supplier<List<User>> allUsersFinder = userRepository::getAll;
        List<User> users = allUsersFinder.get();

        //todo : reuse getAllUsersNicknames() as method reference
        Supplier<List<String>> allNicknamesFinder = L0Suppliers::getAllUsersNicknames;
    }

    private int generateNumber() {
        return new Random().nextInt();
    }

    private static List<String> getAllUsersNicknames(){
        List<User> users = userRepository.getAll();
        List<String> nicknames = new ArrayList<>();
        for (User user : users) {
            nicknames.add(user.getNickname());
        }
        return nicknames;
    }

}
