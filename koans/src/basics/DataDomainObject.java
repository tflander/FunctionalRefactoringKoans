package basics;

public class DataDomainObject {


    final private Long databaseKey;
    final private String accountNumber;
    final private String customerName;

    public DataDomainObject(Long databaseKey, String accountNumber, String customerName) {
        this.databaseKey = databaseKey;
        this.accountNumber = accountNumber;
        this.customerName = customerName;
    }

    public Long getDatabaseKey() {
        return databaseKey;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

}
