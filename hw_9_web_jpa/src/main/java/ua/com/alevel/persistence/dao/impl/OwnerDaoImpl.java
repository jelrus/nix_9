package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.OwnerDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.House;
import ua.com.alevel.persistence.entity.Owner;
import ua.com.alevel.util.builders.ResponseFormer;
import ua.com.alevel.util.quieries.OwnerQueries;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

@Repository
@Transactional
public class OwnerDaoImpl implements OwnerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Owner owner) {
        entityManager.persist(owner);
    }

    @Override
    public void update(Owner owner) {
        entityManager.merge(owner);
    }

    @Override
    public void delete(Long id) {
        int isSuccessful = entityManager.createQuery(OwnerQueries.deleteEntityById())
                .setParameter("id", id)
                .executeUpdate();
        if (isSuccessful == 0) {
            throw new OptimisticLockException("Table 'owners' modified concurrently");
        }
    }

    @Override
    public boolean existById(Long id) {
        Query query = entityManager.createQuery(OwnerQueries.checkExistenceById())
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Owner findById(Long id) {
        return entityManager.find(Owner.class, id);
    }

    @Override
    public List<Owner> findAll() {
        return entityManager.createQuery(OwnerQueries.findAll(), Owner.class).getResultList();
    }

    @Override
    public DataTableResponse<Owner> findAll(DataTableRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Owner> criteriaQuery = criteriaBuilder.createQuery(Owner.class);
        Root<Owner> from = criteriaQuery.from(Owner.class);
        ResponseFormer.getOrder(request, criteriaBuilder, from, criteriaQuery);
        List<Owner> items = ResponseFormer.getQueryResultList(request, criteriaQuery, entityManager);
        return ResponseFormer.formResponse(request, items);
    }

    @Override
    public long count() {
        Query query = entityManager.createQuery(OwnerQueries.countEntities());
        return (Long) query.getSingleResult();
    }

    @Override
    public DataTableResponse<Owner> findByHouseId(DataTableRequest dataTableRequest, Long houseId) {
        Map<Object, Object> otherParamMap = new HashMap<>();
        Set<Owner> setOwners = entityManager.find(House.class, houseId).getOwners();
        List<Owner> owners = new ArrayList<>(setOwners);
        DataTableResponse<Owner> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(owners);
        dataTableResponse.setOtherParamMap(otherParamMap);
        return dataTableResponse;
    }

    @Override
    public Set<House> findHouses(Long id) {
        return entityManager.find(Owner.class, id).getHouses();
    }

    @Override
    public void addHouse(Long houseId, Long ownerId) {
        House house = entityManager.find(House.class, houseId);
        Owner owner = entityManager.find(Owner.class, ownerId);
        house.getOwners().add(owner);
        owner.getHouses().add(house);
    }

    @Override
    public void removeHouse(Long houseId, Long ownerId) {
        House house = entityManager.find(House.class, houseId);
        Owner owner = entityManager.find(Owner.class, ownerId);
        house.getOwners().remove(owner);
        owner.getHouses().remove(house);
    }
}
