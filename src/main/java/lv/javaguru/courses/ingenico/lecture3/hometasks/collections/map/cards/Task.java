package lv.javaguru.courses.ingenico.lecture3.hometasks.collections.map.cards;

import java.math.BigDecimal;
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

        AccountRepository accountRepo = new AccountRepository();
        accountRepo.save(new Account(12, "abc-def"));
        accountRepo.save(new Account(54, "120-ter"));
        accountRepo.save(new Account(78, "29a-six"));

//        List<Identifyable> list = accountRepo.findAll();
//        for (Object o : list)
//            System.out.println(o.toString());

        CreditCardRepository cardRepo = new CreditCardRepository();
        cardRepo.save(new CreditCard(777, "aslkjdhfliuytoiryt7887", 78, new BigDecimal(45.58)));
        cardRepo.save(new CreditCard(898, "87hfwer893457hfdjngejl", 78, new BigDecimal(1.04)));
        cardRepo.save(new CreditCard(371, "mnb12u9984ndmfdsfdg4iu", 54, new BigDecimal(-89)));
        cardRepo.save(new CreditCard(654, "djsfewrftyyy78457457hh", 12, new BigDecimal(0)));

        HashMap<Account, List<CreditCard>> data = new HashMap<Account, List<CreditCard>>();

        for (Object o : accountRepo.findAll())
        {
            Account a = (Account)o;
            Object cl = cardRepo.findByAccountId(a.getId());
            List<CreditCard> cards = (List<CreditCard>)cl;
            data.put(a, cards);
        }

        System.out.println(data.get(new Account(78, "29a-six")).toString());
//        list = cardRepo.findAll();
//        for (Object o : list)
//            System.out.println(o.toString());
    }

}
