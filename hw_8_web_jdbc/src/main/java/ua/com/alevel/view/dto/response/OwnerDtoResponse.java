package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Owner;

import java.util.Date;

public class OwnerDtoResponse extends DtoResponse{

    private String firstName;
    private String lastName;
    private String email;
    private String phone;


    public OwnerDtoResponse(Owner owner) {
        super(owner.getId(), owner.getCreated(), owner.getUpdated());
        setFirstName(owner.getFirstName());
        setLastName(owner.getLastName());
        setEmail(owner.getEmail());
        setPhone(owner.getPhone());
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

    @Override
    public String toString() {
        return "OwnerDtoResponse{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
