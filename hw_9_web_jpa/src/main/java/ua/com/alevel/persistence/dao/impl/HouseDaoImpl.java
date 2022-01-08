package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.HouseDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.House;
import ua.com.alevel.persistence.entity.Owner;
import ua.com.alevel.util.builders.ResponseFormer;
import ua.com.alevel.util.quieries.HouseQueries;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

@Repository
@Transactional
public class HouseDaoImpl implements HouseDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(House house) {
        entityManager.persist(house);
    }

    @Override
    public void update(House house) {
        entityManager.merge(house);
    }

    @Override
    public void delete(Long id) {
        int isSuccessful = entityManager.createQuery(HouseQueries.deleteEntityById())
                                        .setParameter("id", id)
                                        .executeUpdate();
        if (isSuccessful == 0) {
            throw new OptimisticLockException("Table 'houses' modified concurrently");
        }
    }

    @Override
    public boolean existById(Long id) {
        Query query = entityManager.createQuery(HouseQueries.checkExistenceById())
                                   .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public House findById(Long id) {
        return entityManager.find(House.class, id);
    }

    @Override
    public List<House> findAll() {
        return entityManager.createQuery(HouseQueries.findAll(), House.class).getResultList();
    }

    @Override
    public DataTableResponse<House> findAll(DataTableRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<House> criteriaQuery = criteriaBuilder.createQuery(House.class);
        Root<House> from = criteriaQuery.from(House.class);
        ResponseFormer.getOrder(request, criteriaBuilder, from, criteriaQuery);
        List<House> items = ResponseFormer.getQueryResultList(request, criteriaQuery, entityManager);
        return ResponseFormer.formResponse(request, items);
    }

    @Override
    public long count() {
        Query query = entityManager.createQuery(HouseQueries.countEntities());
        return (Long) query.getSingleResult();
    }

    @Override
    public DataTableResponse<House> findByOwnerId(DataTableRequest dataTableRequest, Long ownerId) {
        Map<Object, Object> otherParamMap = new HashMap<>();
        Set<House> setHouses = entityManager.find(Owner.class, ownerId).getHouses();
        List<House> houses = new ArrayList<>(setHouses);
        DataTableResponse<House> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(houses);
        dataTableResponse.setOtherParamMap(otherParamMap);
        return dataTableResponse;
    }

    @Override
    public Set<Owner> findOwners(Long id) {
        return entityManager.find(House.class, id).getOwners();
    }

    @Override
    public void addOwner(Long houseId, Long ownerId) {
        House house = entityManager.find(House.class, houseId);
        Owner owner = entityManager.find(Owner.class, ownerId);
        house.getOwners().add(owner);
        owner.getHouses().add(house);
    }

    @Override
    public void removeOwner(Long houseId, Long ownerId) {
        House house = entityManager.find(House.class, houseId);
        Owner owner = entityManager.find(Owner.class, ownerId);
        house.getOwners().remove(owner);
        owner.getHouses().remove(house);
    }
}
