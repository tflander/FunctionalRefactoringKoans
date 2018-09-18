package basics;

public class DataFromDatabase {


    final private long dataIdentifierSeq;
    final private String accountIdentifier;
    final private String customerDescription;

    public DataFromDatabase(long dataIdentifierSeq, String accountIdentifier, String customerDescription) {
        this.dataIdentifierSeq = dataIdentifierSeq;
        this.accountIdentifier = accountIdentifier;
        this.customerDescription = customerDescription;
    }

    public long getDataIdentifierSeq() {
        return dataIdentifierSeq;
    }


    public String getAccountIdentifier() {
        return accountIdentifier;
    }

    public String getCustomerDescription() {
        return customerDescription;
    }

}
