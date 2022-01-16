package ua.com.alevel.persistence.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "clients")
public class Client extends BaseEntity{

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "client_accounts",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id"))
    private Set<Account> accounts;

    public Client() {
        super();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(firstName, client.firstName) &&
               Objects.equals(lastName, client.lastName) &&
               Objects.equals(email, client.email) &&
               Objects.equals(phone, client.phone) &&
               Objects.equals(accounts, client.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, phone, accounts);
    }

    @Override
    public String toString() {
        return "[id: " + super.getId() +
                ", created: " + super.getCreated().toString() +
                ", updated: " + super.getUpdated().toString() +
                ", first name: " + firstName +
                ", last name: " + lastName +
                ", email: " + email +
                ", phone: " + phone +
                ", accounts: " + accounts +
                ']';
    }
}
