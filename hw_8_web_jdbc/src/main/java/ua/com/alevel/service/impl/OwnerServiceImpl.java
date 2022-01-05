package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.exceptions.EntityNotFoundException;
import ua.com.alevel.exceptions.InputException;
import ua.com.alevel.persistence.dao.OwnerDao;
import ua.com.alevel.persistence.dao.PropertyDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Owner;
import ua.com.alevel.service.OwnerService;
import ua.com.alevel.util.input.Checks;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerDao ownerDao;
    private final PropertyDao propertyDao;

    public OwnerServiceImpl(OwnerDao ownerDao, PropertyDao propertyDao) {
        this.ownerDao = ownerDao;
        this.propertyDao = propertyDao;
    }

    @Override
    public void create(Owner entity) {
        isValid(entity);
        ownerDao.create(entity);
    }

    @Override
    public void update(Owner entity) {
        isValid(entity);
        if (!ownerDao.existById(entity.getId())) {
            throw new EntityNotFoundException("Owner not found");
        }
        ownerDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        if (!ownerDao.existById(id)) {
            throw new EntityNotFoundException("Owner not found");
        }
        propertyDao.deleteByOwnerId(id);
        ownerDao.delete(id);
    }

    @Override
    public Owner findById(Long id) {
        if (ownerDao.existById(id)) {
            return ownerDao.findById(id);
        }
        throw new EntityNotFoundException("Owner not found");
    }

    @Override
    public DataTableResponse<Owner> findAll(DataTableRequest request) {
        DataTableResponse<Owner> dataTableResponse = ownerDao.findAll(request);
        dataTableResponse.setItemsSize(ownerDao.count());
        return dataTableResponse;
    }

    @Override
    public DataTableResponse<Owner> findByHouseId(DataTableRequest dataTableRequest, Long houseId) {
        DataTableResponse<Owner> dataTableResponse = ownerDao.findByHouseId(dataTableRequest, houseId);
        dataTableResponse.setItemsSize(ownerDao.countByHouseId(houseId));
        return dataTableResponse;
    }

    @Override
    public List<Owner> findAll() {
        return ownerDao.findAll();
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
