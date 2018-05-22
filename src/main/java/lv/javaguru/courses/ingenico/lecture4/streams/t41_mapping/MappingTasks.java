package lv.javaguru.courses.ingenico.lecture4.streams.t41_mapping;

import lv.javaguru.courses.ingenico.lecture4.common.User;
import lv.javaguru.courses.ingenico.lecture4.common.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class MappingTasks {

    public static void main(String[] args) {
        UserRepository userRepository = UserRepository.getInstance();
        List<User> users = userRepository.getAll();

        //todo : select all unique users creation dates
        //todo : select all unique users creation dates
        Set<LocalDate> allCreationDates = null;

        //todo : select all users facebook ids, filter null values
        List<String> allFacebookIds = null;

    }


}
