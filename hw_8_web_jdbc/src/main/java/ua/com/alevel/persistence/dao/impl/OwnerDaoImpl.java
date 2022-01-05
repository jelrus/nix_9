package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.OwnerDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Owner;
import ua.com.alevel.util.queries.OwnerQueryGenerator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerDaoImpl implements OwnerDao {

    private final JpaConfig jpaConfig;

    public OwnerDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Owner entity) {
        try (PreparedStatement ps = jpaConfig.getConnection().prepareStatement(OwnerQueryGenerator.create())) {
            ps.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            ps.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            ps.setString(3, entity.getFirstName());
            ps.setString(4, entity.getLastName());
            ps.setString(5, entity.getEmail());
            ps.setString(6, entity.getPhone());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    @Override
    public void update(Owner entity) {
        try (PreparedStatement ps = jpaConfig.getConnection().prepareStatement(OwnerQueryGenerator.update(entity.getId()))) {
            ps.setTimestamp(1, new Timestamp(entity.getUpdated().getTime()));
            ps.setString(2, entity.getFirstName());
            ps.setString(3, entity.getLastName());
            ps.setString(4, entity.getEmail());
            ps.setString(5, entity.getPhone());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement ps = jpaConfig.getConnection().prepareStatement(OwnerQueryGenerator.delete(id))) {
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        int count = 0;
        try (ResultSet rs = jpaConfig.getStatement().executeQuery(OwnerQueryGenerator.exist(id))) {
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        return count == 1;
    }

    @Override
    public Owner findById(Long id) {
        Owner owner = null;
        try (ResultSet rs = jpaConfig.getStatement().executeQuery(OwnerQueryGenerator.findById(id))) {
            while (rs.next()) {
                owner = toOwnerFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        return owner;
    }

    @Override
    public DataTableResponse<Owner> findAll(DataTableRequest request) {
        List<Owner> ownersList = new ArrayList<>();
        try (ResultSet rs = jpaConfig.getStatement().executeQuery(OwnerQueryGenerator.findAllOwners(request))) {
            while (rs.next()) {
                ownersList.add(toOwnerFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        DataTableResponse<Owner> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(ownersList);
        return dataTableResponse;
    }

    @Override
    public long count() {
        int count = 0;
        try (ResultSet rs = jpaConfig.getStatement().executeQuery(OwnerQueryGenerator.count())) {
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        return count;
    }

    @Override
    public DataTableResponse<Owner> findByHouseId(DataTableRequest request, Long houseId) {
        List<Owner> owners = new ArrayList<>();
        try (ResultSet rs = jpaConfig.getStatement().executeQuery(OwnerQueryGenerator.findAllOwnersByHouse(request, houseId))) {
            while (rs.next()) {
                owners.add(toOwnerFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        DataTableResponse<Owner> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(owners);
        return dataTableResponse;
    }

    @Override
    public long countByHouseId(Long houseId) {
        int count = 0;
        try (ResultSet rs = jpaConfig.getStatement().executeQuery(OwnerQueryGenerator.countByEntity(houseId))) {
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        return count;
    }

    @Override
    public List<Owner> findAll() {
        List<Owner> owners = new ArrayList<>();
        try (ResultSet rs = jpaConfig.getStatement().executeQuery(OwnerQueryGenerator.findAll())) {
            while (rs.next()) {
                Owner owner = toOwnerFromResultSet(rs);
                owners.add(owner);
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        return owners;
    }

    private Owner toOwnerFromResultSet(ResultSet rs) {
        Owner owner = new Owner();
        try {
            owner.setId(rs.getLong("id"));
            owner.setCreated(rs.getTimestamp("created"));
            owner.setUpdated(rs.getTimestamp("updated"));
            owner.setFirstName(rs.getString("first_name"));
            owner.setLastName(rs.getString("last_name"));
            owner.setEmail(rs.getString("email"));
            owner.setPhone(rs.getString("phone"));
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        return owner;
    }
}
