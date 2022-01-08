package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.exceptions.EntityNotFoundException;
import ua.com.alevel.exceptions.InputException;
import ua.com.alevel.persistence.dao.HouseDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.House;
import ua.com.alevel.persistence.entity.Owner;
import ua.com.alevel.service.HouseService;
import ua.com.alevel.util.input.Checks;

import java.util.List;
import java.util.Set;

@Service
public class HouseServiceImpl implements HouseService {

    private final HouseDao houseDao;

    public HouseServiceImpl(HouseDao houseDao) {
        this.houseDao = houseDao;
    }

    @Override
    public void create(House house) {
        isValid(house);
        houseDao.create(house);
    }

    @Override
    public void update(House house) {
        isValid(house);
        if (!houseDao.existById(house.getId())) {
            throw new EntityNotFoundException("House not found");
        }
        houseDao.update(house);
    }

    @Override
    public void delete(Long id) {
        if (!houseDao.existById(id)) {
            throw new EntityNotFoundException("House not found");
        }
        houseDao.delete(id);
    }

    @Override
    public House findById(Long id) {
        if (!houseDao.existById(id)) {
            throw new EntityNotFoundException("House not found");
        }
        return houseDao.findById(id);
    }

    @Override
    public List<House> findAll() {
        return houseDao.findAll();
    }

    @Override
    public DataTableResponse<House> findAll(DataTableRequest request) {
        return houseDao.findAll(request);
    }

    @Override
    public DataTableResponse<House> findByOwnerId(DataTableRequest request, Long houseId) {
        return houseDao.findByOwnerId(request, houseId);
    }

    @Override
    public Set<Owner> findOwners(Long id) {
        return houseDao.findOwners(id);
    }

    @Override
    public void addOwner(Long houseId, Long ownerId) {
        houseDao.addOwner(houseId, ownerId);
    }

    @Override
    public void removeOwner(Long houseId, Long ownerId) {
        houseDao.removeOwner(houseId, ownerId);
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
