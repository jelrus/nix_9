package ua.com.alevel.view.controller;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.ClientFacade;
import ua.com.alevel.view.dto.request.ClientDtoRequest;
import ua.com.alevel.view.dto.response.AccountDtoResponse;
import ua.com.alevel.view.dto.response.ClientDtoResponse;
import ua.com.alevel.view.dto.response.PageData;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/clients")
public class ClientController extends AbstractController{

    private final ClientFacade clientFacade;

    public ClientController(ClientFacade clientFacade) {
        this.clientFacade = clientFacade;
    }

    private AbstractController.HeaderName[] getColumnNames() {
        return new AbstractController.HeaderName[]{
                new AbstractController.HeaderName("#", null, null),
                new AbstractController.HeaderName("First name", "first_name", "firstName"),
                new AbstractController.HeaderName("Last Name", "last_name", "lastName"),
                new AbstractController.HeaderName("Email", "email", "email"),
                new AbstractController.HeaderName("Phone", "phone", "phone"),
                new AbstractController.HeaderName("Details", null, null),
                new AbstractController.HeaderName("Delete", null, null)
        };
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        AbstractController.HeaderName[] columnNames = getColumnNames();
        PageData<ClientDtoResponse> response = clientFacade.findAll(webRequest);
        response.initPaginationState(response.getCurrentPage());
        model.addAttribute("headerDataList", getHeaderDataList(columnNames, response));
        model.addAttribute("createUrl", "/clients/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Clients");
        model.addAttribute("allowCreate", true);
        model.addAttribute("createNewItemUrl", "/clients/new");
        return "pages/client/client_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/clients", model);
    }

    @GetMapping("/new")
    public String redirectToNewClientPage(Model model) {
        model.addAttribute("client", new ClientDtoRequest());
        return "pages/client/client_new";
    }

    @PostMapping("/create")
    public String createNewClient(@ModelAttribute("client") ClientDtoRequest dto) {
        clientFacade.create(dto);
        return "redirect:/clients";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        clientFacade.delete(id);
        return "redirect:/clients";
    }

    @GetMapping("/client/{id}/details/")
    public String details(@PathVariable Long id, Model model) {
        List<AccountDtoResponse> attachedAccounts = clientFacade.findAccounts(id);
        ClientDtoResponse clientDto = clientFacade.findById(id);
        model.addAttribute("client", clientDto);
        model.addAttribute("attachedAccounts", attachedAccounts);
        return "pages/client/client_details";
    }

    @PostMapping("/update/{id}")
    public String updateClient(@PathVariable Long id, @ModelAttribute("client") ClientDtoRequest clientDtoRequest) {
        clientFacade.update(clientDtoRequest, id);
        return "redirect:/clients/client/{id}/details/";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        ClientDtoResponse clientDtoResponse = clientFacade.findById(id);
        model.addAttribute("client", clientDtoResponse);
        return "pages/client/client_update";
    }

    @GetMapping("/client/{clientId}/accounts")
    public String findAccountsByClient(@PathVariable Long clientId, Model model) {
        List<AccountDtoResponse> attachedAccounts = clientFacade.findAccounts(clientId);
        ClientDtoResponse clientDto = clientFacade.findById(clientId);
        model.addAttribute("attachedAccounts", attachedAccounts);
        model.addAttribute("client", clientDto);
        return "pages/client/client_accounts";
    }
}
