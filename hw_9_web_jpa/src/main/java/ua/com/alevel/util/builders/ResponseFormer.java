package ua.com.alevel.util.builders;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public final class ResponseFormer {

    public static <E extends BaseEntity> void  getOrder(DataTableRequest request, CriteriaBuilder builder,
                                                        Root<E> from, CriteriaQuery<E> query) {
        if (request.getOrder().equals("desc")) {
            query.orderBy(builder.desc(from.get(request.getSort())));
        } else {
            query.orderBy(builder.asc(from.get(request.getSort())));
        }
    }

    public static int getPage(DataTableRequest request) {
        return (request.getPage() - 1) * request.getSize();
    }

    public static int getSize(DataTableRequest request) {
        return getPage(request) + request.getSize();
    }

    public static <E extends BaseEntity> List<E> getQueryResultList(DataTableRequest request, CriteriaQuery<E> query,
                                                                    EntityManager entityManager) {
        System.out.println("page: " + getPage(request));
        System.out.println("size: " + getSize(request));
        return entityManager.createQuery(query)
                            .setFirstResult(getPage(request))
                            .setMaxResults(getSize(request))
                            .getResultList();
    }

    public static <E extends BaseEntity> DataTableResponse<E> formResponse(DataTableRequest request, List<E> items) {
        DataTableResponse<E> response = new DataTableResponse<>();
        response.setSort(request.getSort());
        response.setOrder(request.getOrder());
        response.setCurrentPage(request.getPage());
        response.setPageSize(request.getSize());
        response.setItems(items);
        return response;
    }
}
