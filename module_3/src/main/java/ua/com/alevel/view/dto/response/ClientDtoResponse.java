package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Client;

import java.util.Objects;
import java.util.Set;

public class ClientDtoResponse extends DtoResponse{

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Set<Account> accounts;
    private Long accountCount;

    public ClientDtoResponse(Client client) {
        super(client.getId(), client.getCreated(), client.getUpdated());
        setFirstName(client.getFirstName());
        setLastName(client.getLastName());
        setEmail(client.getEmail());
        setPhone(client.getPhone());
        setAccounts(client.getAccounts());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public Long getAccountCount() {
        return accountCount;
    }

    public void setAccountCount(Long accountCount) {
        this.accountCount = accountCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDtoResponse that = (ClientDtoResponse) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone) && Objects.equals(accounts, that.accounts) && Objects.equals(accountCount, that.accountCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, phone, accounts, accountCount);
    }

    @Override
    public String toString() {
        return "ClientDtoResponse{" +
                "id" + super.getId() +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", accounts=" + accounts +
                ", accountCount=" + accountCount +
                '}';
    }
}
