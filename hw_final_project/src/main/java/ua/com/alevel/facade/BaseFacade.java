package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.view.dto.request.DtoRequest;
import ua.com.alevel.view.dto.response.DtoResponse;
import ua.com.alevel.view.dto.response.PageData;

public interface BaseFacade <REQ extends DtoRequest, RESP extends DtoResponse> {

    void create(REQ req);

    void update(REQ req, Long id);

    void delete(Long id);

    RESP findById(Long id);

    PageData<RESP> findAll(WebRequest request);
}
