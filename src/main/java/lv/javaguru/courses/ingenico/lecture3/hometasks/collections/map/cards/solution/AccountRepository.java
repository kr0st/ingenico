package lv.javaguru.courses.ingenico.lecture3.hometasks.collections.map.cards.solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static lv.javaguru.courses.ingenico.lecture3.hometasks.collections.map.cards.Searcher.findOneBy;

public class AccountRepository {

    private final List<Account> accounts = new ArrayList<>();

    public void save(Account account){
        if (findById(account.getId()) != null){
            throw new UniqueConstraintException("id must be unique");
        }
        accounts.add(account);
    }

    public Account findById(int id) {
        return findOneBy(accounts, account -> account.getId() == id);
    }

    public Account findByContractNumber(String contractNumber) {
        return findOneBy(accounts, account -> account.getContractNumber().equals(contractNumber));
    }

    public List<Account> findAll(){
        return Collections.unmodifiableList(accounts);
    }
}
