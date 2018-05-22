package lv.javaguru.courses.ingenico.lecture3.hometasks.collections.map.cards.solution;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task {

    public static void main(String[] args) {
        /* todo :
        * 0. move common logic from AccountRepository and CreditCardRepository
        * into abstract generic superclass Repository
        *
        * 1. fix Account class to have possibility use it as Key in HashMap
        *
        * 2. create instance of AccountRepository and add few accounts
        *
        * 3. create instance of CreditCardRepository and add few credit cards
        * for each accounts
        *
        * 4. - retrieve all accounts from repository
        *    - retrieve all credit cards from repository
        *    - group all cards by account into Map<Account, List<CreditCard>>
        *      key = Account
        *      value = List of CreditCard (only which has the same account id as key-Account)
        *
        *      e.g. key = Account(id = 1)
        *           value = List [ CreditCard(id=10, accountId=1),  CreditCard(id=5, accountId=1)]
        *
        * 5. use Map#get(account) to retrieve List of credit cards
        */
        AccountRepository accountRepository = new AccountRepository();
        accountRepository.save(new Account(1, "cn1"));
        accountRepository.save(new Account(2, "cn2"));

        CreditCardRepository cardRepository = new CreditCardRepository();
        cardRepository.save(new CreditCard(1, "1h", 1, BigDecimal.ZERO));
        cardRepository.save(new CreditCard(2, "2h", 1, BigDecimal.TEN));
        cardRepository.save(new CreditCard(3, "3h", 2, BigDecimal.ZERO));
        cardRepository.save(new CreditCard(4, "4h", 2, BigDecimal.TEN));

        Map<Account, List<CreditCard>> accountCards = new HashMap<>();
        List<Account> allAccounts = accountRepository.findAll();
        for (CreditCard card : cardRepository.findAll()) {
            for (Account account : allAccounts) {
                if (account.getId() == card.getAccountId()) {
                    if (accountCards.containsKey(account)) {
                        accountCards.get(account).add(card);
                    } else {
                        List<CreditCard> cards = new ArrayList<>();
                        cards.add(card);
                        accountCards.put(account, cards);
                    }
                }
            }
        }
        //to enable asserts run program with args -ea
        boolean hasExpectedSize = accountCards.get(accountRepository.findById(1)).size() == 2;
        assert hasExpectedSize : "account id 1 must have 2 cards";
        hasExpectedSize = accountCards.get(accountRepository.findById(2)).size() == 2;
        assert hasExpectedSize : "account id 2 must have 2 cards";
    }

}
