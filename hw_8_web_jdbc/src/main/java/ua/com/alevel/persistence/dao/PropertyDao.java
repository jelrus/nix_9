package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Property;

public interface PropertyDao extends BaseDao<Property>{

    void deleteByHouseId(Long houseId);

    void deleteByOwnerId(Long ownerId);
}
