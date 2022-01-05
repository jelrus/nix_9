package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.exceptions.EntityNotFoundException;
import ua.com.alevel.exceptions.InputException;
import ua.com.alevel.persistence.dao.HouseDao;
import ua.com.alevel.persistence.dao.PropertyDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.House;
import ua.com.alevel.service.HouseService;
import ua.com.alevel.util.input.Checks;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {

    private final HouseDao houseDao;
    private final PropertyDao propertyDao;

    public HouseServiceImpl(HouseDao houseDao, PropertyDao propertyDao) {
        this.houseDao = houseDao;
        this.propertyDao = propertyDao;
    }

    @Override
    public void create(House entity) {
        isValid(entity);
        houseDao.create(entity);
    }

    @Override
    public void update(House entity) {
        isValid(entity);
        if (!houseDao.existById(entity.getId())) {
            throw new EntityNotFoundException("House not found");
        }
        houseDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        if (!houseDao.existById(id)) {
            throw new EntityNotFoundException("House not found");
        }
        propertyDao.deleteByHouseId(id);
        houseDao.delete(id);
    }

    @Override
    public House findById(Long id) {
        if (houseDao.existById(id)) {
            return houseDao.findById(id);
        }
        throw new EntityNotFoundException("House not found");
    }

    @Override
    public DataTableResponse<House> findAll(DataTableRequest request) {
        DataTableResponse<House> dataTableResponse = houseDao.findAll(request);
        dataTableResponse.setItemsSize(houseDao.count());
        return dataTableResponse;
    }

    @Override
    public DataTableResponse<House> findByOwnerId(DataTableRequest dataTableRequest, Long ownerId) {
        DataTableResponse<House> dataTableResponse = houseDao.findByOwnerId(dataTableRequest, ownerId);
        dataTableResponse.setItemsSize(houseDao.countByOwnerId(ownerId));
        return dataTableResponse;
    }

    @Override
    public List<House> findAll() {
        return houseDao.findAll();
    }

    private void isValid(House house) {
        if (Checks.isBlankNullOrEmpty(house.getImage())) {
            throw new InputException("Incorrect image");
        }
        if (Checks.isBlankNullOrEmpty(house.getCountry())) {
            throw new InputException("Incorrect country");
        }
        if (Checks.isBlankNullOrEmpty(house.getCity())) {
            throw new InputException("Incorrect city");
        }
        if (Checks.isBlankNullOrEmpty(house.getStreet())) {
            throw new InputException("Incorrect street");
        }
        if (Checks.isBlankNullOrEmpty(house.getBuildingNumber())) {
            throw new InputException("Incorrect building number");
        }
        if (!Checks.isPositiveNumber(house.getCost().toString())) {
            throw new InputException("Incorrect cost");
        }
    }
}
