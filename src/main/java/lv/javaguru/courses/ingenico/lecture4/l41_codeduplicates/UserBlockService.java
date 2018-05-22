package lv.javaguru.courses.ingenico.lecture4.l41_codeduplicates;


import lv.javaguru.courses.ingenico.lecture4.common.User;
import lv.javaguru.courses.ingenico.lecture4.common.UserRepository;

import java.util.function.Function;

public class UserBlockService {

    private final UserRepository userRepository;

    public UserBlockService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private boolean blockByNickname(String nickname){
        return blockUser(nickname,  userRepository::findByNickname);
    }

    private boolean blockByFacebookId(String facebookId){
        return blockUser(facebookId, userRepository::findByFacebookId);
    }

    private <T> boolean blockUser(T userProperty, Function<T, User> userFinder){
        User user = userFinder.apply(userProperty);
        if (user == null){
            return false;
        }
        user.setActive(false);
        return true;
    }

    public static void main(String[] args) {
        UserBlockService service = new UserBlockService(UserRepository.getInstance());

        boolean isJoseBlocked = service.blockByFacebookId("jose-facebook");
        System.out.println("Jose blocked = " + isJoseBlocked);

        boolean isJohn89Blocked = service.blockByNickname("john89");
        System.out.println("John89 blocked = " + isJohn89Blocked);

        boolean isUnknownBlocked = service.blockByNickname("unknown");
        System.out.println("Unknown blocked = " + isUnknownBlocked);

        boolean isBlockedByWrongCode = service.blockByFacebookId("jose-facebook");
        System.out.println("Block jose with wrong code = " + isBlockedByWrongCode);
    }
}
