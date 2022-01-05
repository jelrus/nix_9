package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.HouseDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.House;
import ua.com.alevel.persistence.entity.type.Status;
import ua.com.alevel.util.queries.HouseQueryGenerator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HouseDaoImpl implements HouseDao {

    private final JpaConfig jpaConfig;

    public HouseDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(House entity) {
        try (PreparedStatement ps = jpaConfig.getConnection().prepareStatement(HouseQueryGenerator.create())) {
            ps.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            ps.setTimestamp(2,new Timestamp(entity.getCreated().getTime()));
            ps.setString(3, entity.getImage());
            ps.setString(4, entity.getCountry());
            ps.setString(5, entity.getCity());
            ps.setString(6, entity.getStreet());
            ps.setString(7, entity.getBuildingNumber());
            ps.setString(8, entity.getStatus().name());
            ps.setBigDecimal(9, entity.getCost());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    @Override
    public void update(House entity) {
        try (PreparedStatement ps = jpaConfig.getConnection().prepareStatement(HouseQueryGenerator.update(entity.getId()))){
            ps.setTimestamp(1,new Timestamp(entity.getCreated().getTime()));
            ps.setString(2, entity.getImage());
            ps.setString(3, entity.getCountry());
            ps.setString(4, entity.getCity());
            ps.setString(5, entity.getStreet());
            ps.setString(6, entity.getBuildingNumber());
            ps.setString(7, entity.getStatus().name());
            ps.setBigDecimal(8, entity.getCost());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement ps = jpaConfig.getConnection().prepareStatement(HouseQueryGenerator.delete(id))){
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        int count = 0;
        try (ResultSet rs = jpaConfig.getStatement().executeQuery(HouseQueryGenerator.exist(id))) {
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        return count == 1;
    }

    @Override
    public House findById(Long id) {
        House house = null;
        try (ResultSet rs = jpaConfig.getStatement().executeQuery(HouseQueryGenerator.findById(id))) {
            while (rs.next()) {
                house = toHouseFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        return house;
    }

    @Override
    public DataTableResponse<House> findAll(DataTableRequest request) {
        List<House> groupList = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();
        try (ResultSet rs = jpaConfig.getStatement().executeQuery(HouseQueryGenerator.findAllHouses(request))) {
            while (rs.next()) {
                HouseResultSet groupResultSet = toHouseResultSetFromResultSet(rs);
                groupList.add(groupResultSet.getHouse());
                otherParamMap.put(groupResultSet.getHouse().getId(), groupResultSet.getOwnerCount());
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        DataTableResponse<House> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(groupList);
        tableResponse.setOtherParamMap(otherParamMap);
        return tableResponse;
    }

    @Override
    public long count() {
        int count = 0;
        try (ResultSet rs = jpaConfig.getStatement().executeQuery(HouseQueryGenerator.count())) {
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        return count;
    }

    @Override
    public DataTableResponse<House> findByOwnerId(DataTableRequest request, Long ownerId) {
        List<House> housesList = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();
        try (ResultSet rs = jpaConfig.getStatement().executeQuery(HouseQueryGenerator.findAllHousesByOwner(request, ownerId))) {
            while (rs.next()) {
                HouseResultSet housesResultSet = toHouseResultSetFromResultSet(rs);
                housesList.add(housesResultSet.getHouse());
                otherParamMap.put(housesResultSet.getHouse().getId(), housesResultSet.getOwnerCount());
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        DataTableResponse<House> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(housesList);
        dataTableResponse.setOtherParamMap(otherParamMap);
        return dataTableResponse;
    }

    @Override
    public long countByOwnerId(Long ownerId) {
        int count = 0;
        try (ResultSet rs = jpaConfig.getStatement().executeQuery(HouseQueryGenerator.countByEntity(ownerId))) {
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        return count;
    }

    @Override
    public List<House> findAll() {
        List<House> houses = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(HouseQueryGenerator.findAll())) {
            while (resultSet.next()) {
                houses.add(toHouseFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        return houses;
    }

    private House toHouseFromResultSet(ResultSet rs) throws SQLException {
        House house = new House();
        house.setId(rs.getLong("id"));
        house.setCreated(rs.getTimestamp("created"));
        house.setUpdated(rs.getTimestamp("updated"));
        house.setImage(rs.getString("image"));
        house.setCountry(rs.getString("country"));
        house.setCity(rs.getString("city"));
        house.setStreet(rs.getString("street"));
        house.setBuildingNumber(rs.getString("building_number"));
        house.setStatus(Status.valueOf(rs.getString("status")));
        house.setCost(rs.getBigDecimal("cost"));
        return house;
    }

    private HouseResultSet toHouseResultSetFromResultSet(ResultSet rs) throws SQLException {
        House house = toHouseFromResultSet(rs);
        long ownerCount = rs.getInt("owner_count");
        return new HouseResultSet(house, ownerCount);
    }

    private record HouseResultSet(House house, long ownerCount) {

        public House getHouse() {
            return house;
        }

        public long getOwnerCount() {
            return ownerCount;
        }
    }
}
