package lv.javaguru.courses.ingenico.lecture7.hometask.repositories;

import lv.javaguru.courses.ingenico.lecture7.hometask.model.Customer;

public interface CustomerRepository {

    Customer findById(Long id);

}
