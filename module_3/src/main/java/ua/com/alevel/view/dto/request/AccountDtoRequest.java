package ua.com.alevel.view.dto.request;

import java.math.BigDecimal;
import java.util.List;

public class AccountDtoRequest extends DtoRequest{

    private String accountNumber;
    private BigDecimal balance;
    private Long clientId;
    private List<Long> operations;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<Long> getOperations() {
        return operations;
    }

    public void setOperations(List<Long> operations) {
        this.operations = operations;
    }
}
