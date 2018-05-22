package lv.javaguru.courses.ingenico.lecture3.hometasks.collections.map.cards.solution;

public class Account {

    private int id;
    private String contractNumber;

    public Account(int id, String contractNumber) {
        this.id = id;
        this.contractNumber = contractNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account
                account = (Account) o;

        if (id != account.id) return false;
        return contractNumber != null ? contractNumber.equals(account.contractNumber) : account.contractNumber == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (contractNumber != null ? contractNumber.hashCode() : 0);
        return result;
    }
}
