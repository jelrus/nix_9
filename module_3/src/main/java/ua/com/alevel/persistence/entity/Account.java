package ua.com.alevel.persistence.entity;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "accounts")
public class Account extends BaseEntity{

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "balance", precision = 20, scale = 2)
    private BigDecimal balance;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "account_operations",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "operation_id"))
    private List<Operation> operations;

    public Account() {
        super();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountNumber, account.accountNumber) &&
               Objects.equals(balance, account.balance) &&
               Objects.equals(operations, account.operations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, balance, operations);
    }

    @Override
    public String toString() {
        return "[id: " + super.getId() +
                ", created: " + super.getCreated().toString() +
                ", updated: " + super.getUpdated().toString() +
                ", account number: " + accountNumber +
                ", balance: " + balance + "]";
    }
}
