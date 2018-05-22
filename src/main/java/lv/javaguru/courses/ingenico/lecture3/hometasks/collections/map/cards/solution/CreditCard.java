package lv.javaguru.courses.ingenico.lecture3.hometasks.collections.map.cards.solution;

import java.math.BigDecimal;

public class CreditCard {

    private int id;
    private String panHash;
    private int accountId;
    private BigDecimal availableBalance;

    public CreditCard(int id,
                      String panHash,
                      int accountId,
                      BigDecimal availableBalance) {
        this.id = id;
        this.panHash = panHash;
        this.accountId = accountId;
        this.availableBalance = availableBalance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPanHash() {
        return panHash;
    }

    public void setPanHash(String panHash) {
        this.panHash = panHash;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }
}
