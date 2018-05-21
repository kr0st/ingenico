package lv.javaguru.courses.ingenico.lecture3.hometasks.collections.map.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static lv.javaguru.courses.ingenico.lecture3.hometasks.collections.map.cards.Searcher.findOneBy;

public class AccountRepository extends Repository
{
    public Account findByContractNumber(String contractNumber) {
        return (Account)findOneBy(data, account -> { Account a = (Account)account; return a.getContractNumber().equals(contractNumber);});
    }
}
