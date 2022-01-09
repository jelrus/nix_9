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
import ua.com.alevel.service.HouseService;
import ua.com.alevel.util.input.Checks;

import java.util.*;

@Service
public class HouseServiceImpl implements HouseService {

    private final BaseCrudRepository<House, HouseRepository>  baseHouseRepository;
    private final BaseCrudRepository<Owner, OwnerRepository> baseOwnerRepository;
    private final HouseRepository houseRepository;
    private final OwnerRepository ownerRepository;

    public HouseServiceImpl(BaseCrudRepository<House, HouseRepository> baseHouseRepository,
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
    public void create(House house) {
        isValid(house);
        baseHouseRepository.create(houseRepository, house);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(House house) {
        isValid(house);
        baseHouseRepository.update(houseRepository, house);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void delete(Long id) {
        baseHouseRepository.delete(houseRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<House> findById(Long id) {
        return baseHouseRepository.findById(houseRepository, id);
    }

    @Override
    public List<House> findAll() {
        return houseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<House> findAll(DataTableRequest request) {
        return baseHouseRepository.findAll(houseRepository, request);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<House> findByOwnerId(DataTableRequest dataTableRequest, Long ownerId) {
        Map<Object, Object> otherParamMap = new HashMap<>();
        Set<House> setHouses = baseOwnerRepository.findById(ownerRepository, ownerId).get().getHouses();
        List<House> houses = new ArrayList<>(setHouses);
        DataTableResponse<House> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(houses);
        dataTableResponse.setOtherParamMap(otherParamMap);
        return dataTableResponse;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Set<Owner> findOwners(Long id) {
        return baseHouseRepository.findById(houseRepository, id).get().getOwners();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void addOwner(Long houseId, Long ownerId) {
        House house = baseHouseRepository.findById(houseRepository, houseId).get();
        Owner owner = baseOwnerRepository.findById(ownerRepository, ownerId).get();
        house.getOwners().add(owner);
        owner.getHouses().add(house);
        baseHouseRepository.update(houseRepository, house);
        baseOwnerRepository.update(ownerRepository, owner);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void removeOwner(Long houseId, Long ownerId) {
        House house = baseHouseRepository.findById(houseRepository, houseId).get();
        Owner owner = baseOwnerRepository.findById(ownerRepository, ownerId).get();
        house.getOwners().remove(owner);
        owner.getHouses().remove(house);
        baseHouseRepository.update(houseRepository, house);
        baseOwnerRepository.update(ownerRepository, owner);
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
