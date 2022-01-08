package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.exceptions.EntityNotFoundException;
import ua.com.alevel.exceptions.InputException;
import ua.com.alevel.persistence.dao.OwnerDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.House;
import ua.com.alevel.persistence.entity.Owner;
import ua.com.alevel.service.OwnerService;
import ua.com.alevel.util.input.Checks;

import java.util.List;
import java.util.Set;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerDao ownerDao;

    public OwnerServiceImpl(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }

    @Override
    public void create(Owner owner) {
        isValid(owner);
        ownerDao.create(owner);
    }

    @Override
    public void update(Owner owner) {
        isValid(owner);
        if (!ownerDao.existById(owner.getId())) {
            throw new EntityNotFoundException("Owner not found");
        }
        ownerDao.update(owner);
    }

    @Override
    public void delete(Long id) {
        if (!ownerDao.existById(id)) {
            throw new EntityNotFoundException("Owner not found");
        }
        ownerDao.delete(id);
    }

    @Override
    public Owner findById(Long id) {
        if (!ownerDao.existById(id)) {
            throw new EntityNotFoundException("student not found");
        }
        return ownerDao.findById(id);
    }

    @Override
    public List<Owner> findAll() {
        return ownerDao.findAll();
    }

    @Override
    public DataTableResponse<Owner> findAll(DataTableRequest request) {
        return ownerDao.findAll(request);
    }

    @Override
    public DataTableResponse<Owner> findByHouseId(DataTableRequest request, Long houseId) {
        return ownerDao.findByHouseId(request, houseId);
    }

    @Override
    public Set<House> findHouses(Long id) {
        return ownerDao.findHouses(id);
    }

    @Override
    public void addHouse(Long houseId, Long ownerId) {
        ownerDao.addHouse(houseId, ownerId);
    }

    @Override
    public void removeHouse(Long houseId, Long ownerId) {
        ownerDao.removeHouse(houseId, ownerId);
    }

    private void isValid(Owner owner) {
        if (Checks.isBlankNullOrEmpty(owner.getFirstName())){
            throw new InputException("First name is incorrect");
        }
        if (Checks.isBlankNullOrEmpty(owner.getLastName())){
            throw new InputException("Last name is incorrect");
        }
        if (Checks.isBlankNullOrEmpty(owner.getEmail()) || Checks.isValidEmail(owner.getEmail())){
            throw new InputException("Email name is incorrect (Pattern: something@something");
        }
        if (Checks.isBlankNullOrEmpty(owner.getPhone()) || Checks.isValidPhone(owner.getPhone())) {
            throw new InputException("Phone is incorrect (Pattern: +123456789012)");
        }
    }
}
