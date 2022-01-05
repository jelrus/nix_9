package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.PropertyDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.House;
import ua.com.alevel.persistence.entity.Owner;
import ua.com.alevel.persistence.entity.Property;
import ua.com.alevel.persistence.entity.type.Status;
import ua.com.alevel.util.queries.PropertyQueryGenerator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyDaoImpl implements PropertyDao {

    private final JpaConfig jpaConfig;

    public PropertyDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Property entity) {
        try (PreparedStatement ps = jpaConfig.getConnection().prepareStatement(PropertyQueryGenerator.create())) {
            ps.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            ps.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            ps.setLong(3, entity.getHouse().getId());
            ps.setLong(4, entity.getOwner().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    @Override
    public void update(Property entity) {
        try (PreparedStatement ps = jpaConfig.getConnection().prepareStatement(PropertyQueryGenerator.update(entity.getId()))) {
            ps.setTimestamp(1, new Timestamp(entity.getUpdated().getTime()));
            ps.setLong(2, entity.getHouse().getId());
            ps.setLong(3, entity.getOwner().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement ps = jpaConfig.getConnection().prepareStatement(PropertyQueryGenerator.delete(id))) {
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        int count = 0;
        try (ResultSet rs = jpaConfig.getStatement().executeQuery(PropertyQueryGenerator.exist(id))) {
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        return count == 1;
    }

    @Override
    public Property findById(Long id) {
        Property property = null;
        try (ResultSet rs = jpaConfig.getStatement().executeQuery(PropertyQueryGenerator.findById(id))) {
            while (rs.next()) {
                property = getRelationsFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        return property;
    }

    @Override
    public DataTableResponse<Property> findAll(DataTableRequest request) {
        List<Property> propertyList = new ArrayList<>();
        try (ResultSet rs = jpaConfig.getStatement().executeQuery(PropertyQueryGenerator.findAll(request))) {
            while (rs.next()) {
                propertyList.add(getRelationsFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        DataTableResponse<Property> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(propertyList);
        return dataTableResponse;
    }

    @Override
    public long count() {
        int count = 0;
        try (ResultSet rs = jpaConfig.getStatement().executeQuery(PropertyQueryGenerator.count())) {
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        return count;
    }

    @Override
    public void deleteByHouseId(Long houseId) {
        try (PreparedStatement ps = jpaConfig.getConnection().prepareStatement(PropertyQueryGenerator.deleteHouse(houseId))) {
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    @Override
    public void deleteByOwnerId(Long ownerId) {
        try (PreparedStatement ps = jpaConfig.getConnection().prepareStatement(PropertyQueryGenerator.deleteOwner(ownerId))) {
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    private Property getRelationsFromResultSet(ResultSet rs) throws SQLException {
        Property property = new Property();
        property.setId(rs.getLong("property.id"));
        property.setCreated(rs.getTimestamp("property.created"));
        property.setUpdated(rs.getTimestamp("property.updated"));

        House house = new House();
        house.setId(rs.getLong("h.id"));
        house.setCreated(rs.getTimestamp("h.created"));
        house.setUpdated(rs.getTimestamp("h.updated"));
        house.setImage(rs.getString("image"));
        house.setCountry(rs.getString("country"));
        house.setCity(rs.getString("city"));
        house.setStreet(rs.getString("street"));
        house.setBuildingNumber(rs.getString("building_number"));
        house.setStatus(Status.valueOf(rs.getString("status")));
        house.setCost(rs.getBigDecimal("cost"));

        Owner owner = new Owner();
        owner.setId(rs.getLong("o.id"));
        owner.setCreated(rs.getTimestamp("o.created"));
        owner.setUpdated(rs.getTimestamp("o.updated"));
        owner.setFirstName(rs.getString("first_name"));
        owner.setLastName(rs.getString("last_name"));
        owner.setEmail(rs.getString("email"));
        owner.setPhone(rs.getString("phone"));

        property.setHouse(house);
        property.setOwner(owner);

        return property;
    }
}
