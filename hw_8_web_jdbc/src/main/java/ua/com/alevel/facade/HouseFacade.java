package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.view.dto.request.HouseDtoRequest;
import ua.com.alevel.view.dto.response.HouseDtoResponse;
import ua.com.alevel.view.dto.response.PageData;

import java.util.List;

public interface HouseFacade extends BaseFacade<HouseDtoRequest, HouseDtoResponse>{

    PageData<HouseDtoResponse> findByOwnerId(WebRequest request, Long ownerId);

    List<HouseDtoResponse> findAll();
}
