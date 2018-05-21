package lv.javaguru.courses.ingenico.lecture3.hometasks.collections.map.cards;

public class Account implements Identifyable
{
    private int id;
    private String contractNumber;

    public Account(int id, String contractNumber) {
        this.id = id;
        this.contractNumber = contractNumber;
    }

    @Override
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
    public int hashCode()
    {
        return contractNumber.hashCode();
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Account)
        {
            Account that = (Account)o;
            return this.contractNumber.equals(that.getContractNumber());
        }
        else
            return false;
    }

    @Override
    public String toString()
    {
        String result = new String("id: ");
        return result.concat(String.valueOf(id)).concat(", contract#: ").concat(contractNumber);
    }
}
