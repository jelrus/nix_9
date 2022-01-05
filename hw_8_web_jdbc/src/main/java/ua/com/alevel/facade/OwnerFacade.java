package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.view.dto.request.OwnerDtoRequest;
import ua.com.alevel.view.dto.response.HouseDtoResponse;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.OwnerDtoResponse;

import java.util.List;

public interface OwnerFacade extends BaseFacade<OwnerDtoRequest, OwnerDtoResponse>{

    PageData<OwnerDtoResponse> findByHouseId(WebRequest request, Long houseId);

    List<OwnerDtoResponse> findAll();
}
