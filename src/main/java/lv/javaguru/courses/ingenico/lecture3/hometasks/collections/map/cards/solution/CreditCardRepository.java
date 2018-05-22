package lv.javaguru.courses.ingenico.lecture3.hometasks.collections.map.cards.solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static lv.javaguru.courses.ingenico.lecture3.hometasks.collections.map.cards.Searcher.findManyBy;
import static lv.javaguru.courses.ingenico.lecture3.hometasks.collections.map.cards.Searcher.findOneBy;

public class CreditCardRepository {

    private final List<CreditCard> creditCards = new ArrayList<>();

    public void save(CreditCard creditCard){
        if (findById(creditCard.getId()) != null){
            throw new UniqueConstraintException("id must be unique");
        }
        creditCards.add(creditCard);
    }

    public List<CreditCard> findByAccountId(int accountId) {
        return findManyBy(creditCards, card -> card.getAccountId() == accountId);
    }

    public CreditCard findById(int id){
        return findOneBy(creditCards, card -> card.getId() == id);
    }

    public List<CreditCard> findAll(){
        return Collections.unmodifiableList(creditCards);
    }
}
