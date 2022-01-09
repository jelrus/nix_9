package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.view.dto.request.HouseDtoRequest;
import ua.com.alevel.view.dto.response.HouseDtoResponse;
import ua.com.alevel.view.dto.response.OwnerDtoResponse;
import ua.com.alevel.view.dto.response.PageData;

import java.util.List;

public interface HouseFacade extends BaseFacade<HouseDtoRequest, HouseDtoResponse>{

    PageData<HouseDtoResponse> findByOwnerId(WebRequest request, Long ownerId);

    List<OwnerDtoResponse> findOwners(Long id);

    void addOwner(Long houseId, Long ownerId);

    void removeOwner(Long houseId, Long ownerId);
}
