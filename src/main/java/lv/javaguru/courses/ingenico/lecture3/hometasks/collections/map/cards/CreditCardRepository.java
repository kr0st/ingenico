package lv.javaguru.courses.ingenico.lecture3.hometasks.collections.map.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static lv.javaguru.courses.ingenico.lecture3.hometasks.collections.map.cards.Searcher.findManyBy;
import static lv.javaguru.courses.ingenico.lecture3.hometasks.collections.map.cards.Searcher.findOneBy;

public class CreditCardRepository extends Repository
{
    public List<CreditCard> findByAccountId(int accountId)
    {
        Object list = findManyBy(data, card -> {CreditCard c = (CreditCard)card; return (c.getAccountId() == accountId);});
        return (List<CreditCard>)list;
    }
}
