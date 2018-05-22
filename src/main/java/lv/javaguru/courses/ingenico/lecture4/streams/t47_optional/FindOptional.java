package lv.javaguru.courses.ingenico.lecture4.streams.t47_optional;


import lv.javaguru.courses.ingenico.lecture4.common.User;
import lv.javaguru.courses.ingenico.lecture4.common.UserRepository;

import java.util.Optional;

public class FindOptional {

    static UserRepository repository = UserRepository.getInstance();


    static String findFacebookIdByNickname(String nickname) {
        return Optional.ofNullable(nickname)
                       .map(repository::findByNickname)
                       .map(User::getFacebookId)
                       .filter(facebookId -> !facebookId.isEmpty())
                       .orElseThrow(() -> new IllegalArgumentException(nickname));
    }

    static String find(String nickname){
        User user = repository.findByNickname(nickname);
        if (user == null || user.getFacebookId() == null || user.getFacebookId().isEmpty()){
            throw new IllegalArgumentException(nickname);
        }
        return user.getFacebookId();
    }


}
