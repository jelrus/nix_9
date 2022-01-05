package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.exceptions.EntityNotFoundException;
import ua.com.alevel.persistence.dao.PropertyDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Property;
import ua.com.alevel.service.PropertyService;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyDao propertyDao;

    public PropertyServiceImpl(PropertyDao propertyDao) {
        this.propertyDao = propertyDao;
    }

    @Override
    public void create(Property entity) {
        propertyDao.create(entity);
    }

    @Override
    public void update(Property entity) {
        if (!propertyDao.existById(entity.getId())) {
            throw new EntityNotFoundException("Property not found");
        }
        propertyDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        if (!propertyDao.existById(id)) {
            throw new EntityNotFoundException("Property not found");
        }
        propertyDao.delete(id);
    }

    @Override
    public Property findById(Long id) {
        if (propertyDao.existById(id)) {
            return propertyDao.findById(id);
        }
        throw new EntityNotFoundException("Property not found");
    }

    @Override
    public DataTableResponse<Property> findAll(DataTableRequest request) {
        DataTableResponse<Property> dataTableResponse = propertyDao.findAll(request);
        dataTableResponse.setItemsSize(propertyDao.count());
        return dataTableResponse;
    }
}