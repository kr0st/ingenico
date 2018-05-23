package lv.javaguru.courses.ingenico.lecture4.streams.hometask;

import lv.javaguru.courses.ingenico.lecture4.common.User;
import lv.javaguru.courses.ingenico.lecture4.common.UserRepository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FilterAndCollectUsers {

    /*
    * TODO :
    * return all active users grouped by first character
    * return value = Map<Character, List<User>>
    *               where
    *                   Character = first nickname letter
    *                   List<User> = users SORTED BY NICKNAME IN REVERSED ORDER
    * use toMap or groupingBy -> https://www.mkyong.com/java8/java-8-collectors-groupingby-and-mapping-example/
    *
    * */
    public Map<Character, List<User>> findAllActiveUsersGroupedByFirstNicknameLetter(UserRepository repository) {
        return new HashMap<>();
    }


}
