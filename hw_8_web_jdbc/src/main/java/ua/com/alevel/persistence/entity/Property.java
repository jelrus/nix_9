package ua.com.alevel.persistence.entity;

public class Property extends BaseEntity {

    private House house;
    private Owner owner;

    public Property() {
        super();
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

    @Override
    public String toString() {
        return "Property{" +
                "house=" + house +
                ", owner=" + owner +
                '}';
    }
}
