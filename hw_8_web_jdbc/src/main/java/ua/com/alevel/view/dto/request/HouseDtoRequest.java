package ua.com.alevel.view.dto.request;

import org.springframework.web.multipart.MultipartFile;
import ua.com.alevel.persistence.entity.type.Status;

import java.math.BigDecimal;

public class HouseDtoRequest extends DtoRequest{

    private String image;
    private String country;
    private String city;
    private String street;
    private String buildingNumber;
    private Status status;
    private BigDecimal cost;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
