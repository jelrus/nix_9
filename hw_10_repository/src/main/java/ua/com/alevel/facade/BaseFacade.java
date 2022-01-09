package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.view.dto.request.DtoRequest;
import ua.com.alevel.view.dto.response.DtoResponse;
import ua.com.alevel.view.dto.response.PageData;

import java.util.List;

public interface BaseFacade<REQ extends DtoRequest, RESP extends DtoResponse> {

    void create(REQ req);

    void update(REQ req, long id);

    void delete(long id);

    RESP findById(long id);

    List<RESP> findAll();

    PageData<RESP> findAll(WebRequest request);
}
