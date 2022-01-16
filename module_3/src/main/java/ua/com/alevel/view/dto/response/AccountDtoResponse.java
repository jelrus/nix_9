package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Operation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class AccountDtoResponse extends DtoResponse{

    private String accountNumber;
    private BigDecimal balance;
    private List<Operation> operations;
    private Long operationCount;

    public AccountDtoResponse(Account account) {
        super(account.getId(), account.getCreated(), account.getUpdated());
        setAccountNumber(account.getAccountNumber());
        setBalance(account.getBalance());
        setOperations(account.getOperations());
    }

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

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public Long getOperationCount() {
        return operationCount;
    }

    public void setOperationCount(Long operationCount) {
        this.operationCount = operationCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDtoResponse that = (AccountDtoResponse) o;
        return Objects.equals(accountNumber, that.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, balance, operations, operationCount);
    }
}
