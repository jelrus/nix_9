package ua.com.alevel.view.dto.request;

import java.util.List;

public class OwnerDtoRequest extends DtoRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<Long> houses;

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

    public List<Long> getHouses() {
        return houses;
    }

    public void setHouses(List<Long> houses) {
        this.houses = houses;
    }
}
