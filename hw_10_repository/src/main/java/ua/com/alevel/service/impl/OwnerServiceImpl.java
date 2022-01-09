package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exceptions.InputException;
import ua.com.alevel.persistence.crud.BaseCrudRepository;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.House;
import ua.com.alevel.persistence.entity.Owner;
import ua.com.alevel.persistence.repository.HouseRepository;
import ua.com.alevel.persistence.repository.OwnerRepository;
import ua.com.alevel.service.OwnerService;
import ua.com.alevel.util.input.Checks;

import java.util.*;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final BaseCrudRepository<House, HouseRepository> baseHouseRepository;
    private final BaseCrudRepository<Owner, OwnerRepository> baseOwnerRepository;
    private final HouseRepository houseRepository;
    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(BaseCrudRepository<House, HouseRepository> baseHouseRepository,
                            BaseCrudRepository<Owner, OwnerRepository> baseOwnerRepository,
                            HouseRepository houseRepository,
                            OwnerRepository ownerRepository) {
        this.baseHouseRepository = baseHouseRepository;
        this.baseOwnerRepository = baseOwnerRepository;
        this.houseRepository = houseRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void create(Owner owner) {
        isValid(owner);
        baseOwnerRepository.create(ownerRepository, owner);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(Owner owner) {
        isValid(owner);
        baseOwnerRepository.update(ownerRepository, owner);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void delete(Long id) {
        baseOwnerRepository.delete(ownerRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Owner> findById(Long id) {
        return baseOwnerRepository.findById(ownerRepository, id);
    }

    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Owner> findAll(DataTableRequest request) {
        return baseOwnerRepository.findAll(ownerRepository, request);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Owner> findByHouseId(DataTableRequest request, Long houseId) {
        Map<Object, Object> otherParamMap = new HashMap<>();
        Set<Owner> setOwners = baseHouseRepository.findById(houseRepository, houseId).get().getOwners();
        List<Owner> owners = new ArrayList<>(setOwners);
        DataTableResponse<Owner> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(owners);
        dataTableResponse.setOtherParamMap(otherParamMap);
        return dataTableResponse;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Set<House> findHouses(Long id) {
        return baseOwnerRepository.findById(ownerRepository, id).get().getHouses();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void addHouse(Long houseId, Long ownerId) {
        Owner owner = baseOwnerRepository.findById(ownerRepository, ownerId).get();
        House house = baseHouseRepository.findById(houseRepository, houseId).get();
        owner.getHouses().add(house);
        baseOwnerRepository.update(ownerRepository, owner);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void removeHouse(Long houseId, Long ownerId) {
        Owner owner = baseOwnerRepository.findById(ownerRepository, ownerId).get();
        House house = baseHouseRepository.findById(houseRepository, houseId).get();
        owner.getHouses().remove(house);
        baseOwnerRepository.update(ownerRepository, owner);
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
