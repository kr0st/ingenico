package lv.javaguru.courses.ingenico.lecture4.streams.hometask;

import lv.javaguru.courses.ingenico.lecture4.common.User;
import lv.javaguru.courses.ingenico.lecture4.common.UserRepository;

import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FilterAndCollectUsers {


    public static class UserComparator implements Comparator<User>
    {

        @Override
        public int compare(User u1, User u2)
        {
            return -1 * u1.getNickname().compareTo(u2.getNickname());
        }
    }

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
    public Map<Character, List<User>> findAllActiveUsersGroupedByFirstNicknameLetter(UserRepository repository)
    {
        UserComparator cmp = new UserComparator();
        List<User> users = repository.getAll();
        return users.stream()
                .filter(user -> user.isActive())
                .sorted(cmp::compare)
                .collect(Collectors.groupingBy(user -> user.getNickname().toCharArray()[0]));
    }

    public static void main(String[] args)
    {
        UserRepository userRepository = UserRepository.getInstance();
        FilterAndCollectUsers userfilter  = new FilterAndCollectUsers();

        HashMap<Character, List<User>> res = (HashMap<Character, List<User>>) userfilter.findAllActiveUsersGroupedByFirstNicknameLetter(userRepository);
        System.out.println(res.toString());
    }
}
