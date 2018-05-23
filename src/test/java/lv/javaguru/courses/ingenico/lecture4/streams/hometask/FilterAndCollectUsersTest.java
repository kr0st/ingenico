package lv.javaguru.courses.ingenico.lecture4.streams.hometask;

import lv.javaguru.courses.ingenico.lecture4.common.User;
import lv.javaguru.courses.ingenico.lecture4.common.UserRepository;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class FilterAndCollectUsersTest {

    private FilterAndCollectUsers collector = new FilterAndCollectUsers();

    private UserRepository userRepository = new UserRepository(
            new HashSet<User>() {
                {
                    add(new User("ass", LocalDateTime.now().minusDays(1), false));
                    add(new User("axb", LocalDateTime.now().minusDays(1), true));
                    add(new User("aaz", LocalDateTime.now().minusDays(4), true));
                    add(new User("azz", LocalDateTime.now().minusDays(4), true));
                    add(new User("Xaa", LocalDateTime.now().minusDays(4), true));
                    add(new User("Dsa", LocalDateTime.now().minusDays(4), true));
                    add(new User("Dfg", LocalDateTime.now().minusDays(4), true));
                }
            }
    );

    @Test
    public void shouldFilterNotActiveUsers() throws Exception {
        Map<Character, List<User>> users = collector.findAllActiveUsersGroupedByFirstNicknameLetter(userRepository);
        assertTrue(users.size() > 0);
        User ass = users.entrySet().stream()
                        .map(Map.Entry::getValue)
                        .flatMap(Collection::stream)
                        .filter(user -> user.getNickname().equals("ass"))
                        .findFirst()
                        .orElse(null);
        assertNull(ass);
    }

    @Test
    public void shouldCorrectlyCollectToMap() throws Exception {
        Map<Character, List<User>> users = collector.findAllActiveUsersGroupedByFirstNicknameLetter(userRepository);
        assertEquals(3, users.size());
        assertEquals(3, users.get('a').size());
        assertEquals(1, users.get('X').size());
        assertEquals(2, users.get('D').size());
    }

    @Test
    public void usersMustBeSortedWithReversedOrder() throws Exception {
        Map<Character, List<User>> users = collector.findAllActiveUsersGroupedByFirstNicknameLetter(userRepository);
        assertEquals("azz", users.get('a').get(0).getNickname());
        assertEquals("aaz", users.get('a').get(2).getNickname());
    }
}