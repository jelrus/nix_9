package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Owner;

import java.util.Objects;

public class OwnerDtoResponse extends DtoResponse{

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Long houseCount;

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

    public Long getHouseCount() {
        return houseCount;
    }

    public void setHouseCount(Long houseCount) {
        this.houseCount = houseCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerDtoResponse that = (OwnerDtoResponse) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone) && Objects.equals(houseCount, that.houseCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, phone, houseCount);
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
