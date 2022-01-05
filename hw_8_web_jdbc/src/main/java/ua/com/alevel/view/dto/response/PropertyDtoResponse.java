package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.House;
import ua.com.alevel.persistence.entity.Owner;
import ua.com.alevel.persistence.entity.Property;

public class PropertyDtoResponse extends DtoResponse{

    private House house;
    private Owner owner;
    private Long houseId;
    private Long ownerId;


    public PropertyDtoResponse(Property property) {
        super(property.getId(), property.getCreated(), property.getUpdated());
        setHouse(property.getHouse());
        setOwner(property.getOwner());
        setHouseId(property.getHouse().getId());
        setOwnerId(property.getOwner().getId());
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "PropertyResponseDto{" +
                "house=" + house +
                ", owner=" + owner +
                ", houseId=" + houseId +
                ", ownerId=" + ownerId +
                '}';
    }
}
