package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.House;
import ua.com.alevel.persistence.entity.type.Status;

import java.math.BigDecimal;

public class HouseDtoResponse extends DtoResponse{

    private String image;
    private String country;
    private String city;
    private String street;
    private String buildingNumber;
    private Status status;
    private BigDecimal cost;
    private Long ownerCount;

    public HouseDtoResponse(House house) {
        super(house.getId(), house.getCreated(), house.getUpdated());
        setImage(house.getImage());
        setCountry(house.getCountry());
        setCity(house.getCity());
        setStreet(house.getStreet());
        setBuildingNumber(house.getBuildingNumber());
        setStatus(house.getStatus());
        setCost(house.getCost());
    }

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

    public Long getOwnerCount() {
        return ownerCount;
    }

    public void setOwnerCount(Long ownerCount) {
        this.ownerCount = ownerCount;
    }

    @Override
    public String toString() {
        return "HouseDtoResponse{" +
                "image='" + image + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", buildingNumber='" + buildingNumber + '\'' +
                ", status=" + status +
                ", cost=" + cost +
                ", ownerCount=" + ownerCount +
                '}';
    }
}
