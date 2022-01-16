package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.facade.AccountFacade;
import ua.com.alevel.facade.ClientFacade;
import ua.com.alevel.facade.OperationFacade;
import ua.com.alevel.persistence.entity.type.OperationType;
import ua.com.alevel.view.dto.request.OperationDtoRequest;
import ua.com.alevel.view.dto.response.AccountDtoResponse;
import ua.com.alevel.view.dto.response.ClientDtoResponse;
import ua.com.alevel.view.dto.response.OperationDtoResponse;

@Controller
@RequestMapping("/clients")
public class OperationController extends AbstractController{

    private final ClientFacade clientFacade;
    private final AccountFacade accountFacade;
    private final OperationFacade operationFacade;


    public OperationController(ClientFacade clientFacade,
                               AccountFacade accountFacade,
                               OperationFacade operationFacade) {
        this.clientFacade = clientFacade;
        this.accountFacade = accountFacade;
        this.operationFacade = operationFacade;
    }

    @GetMapping("/client/{clientId}/account/{accountId}/new/operation")
    public String redirectToNewOperationPage(Model model, @PathVariable Long accountId, @PathVariable Long clientId) {
        model.addAttribute("operation", new OperationDtoRequest());
        model.addAttribute("account", accountFacade.findById(accountId));
        model.addAttribute("client", clientFacade.findById(clientId));
        model.addAttribute("operationType", OperationType.values());
        return "pages/operation/operation_new";
    }

    @PostMapping("/client/{clientId}/account/{accountId}/create/operation")
    public String createNewOperation(@ModelAttribute("operation") OperationDtoRequest dto,
                                     @PathVariable Long clientId,
                                     @PathVariable Long accountId) {
        operationFacade.create(dto);
        OperationDtoResponse operation = operationFacade.findAll().get(operationFacade.findAll().size()-1);
        accountFacade.addOperation(dto.getAccountId(), operation.getId());
        return "redirect:/clients/client/{clientId}/account/{accountId}/operations";
    }

    @GetMapping("/client/{clientId}/account/{accountId}/delete/{operationId}")
    public String deleteOperation(@PathVariable Long clientId, @PathVariable Long accountId, @PathVariable Long operationId) {
        operationFacade.delete(operationId);
        return "redirect:/clients/client/{clientId}/account/{accountId}/operations";
    }

    @GetMapping("/client/{clientId}/account/{accountId}/operation/{operationId}/details")
    public String operationDetails(Model model, @PathVariable Long clientId, @PathVariable Long accountId,
                                   @PathVariable Long operationId) {
        OperationDtoResponse operation = operationFacade.findById(operationId);
        model.addAttribute("operation", operation);
        return "pages/operation/operation_details";
    }

    @PostMapping("/client/{clientId}/account/{accountId}/update/operation/{operationId}")
    public String redirectToUpdateOperationPage(@ModelAttribute("operation") OperationDtoRequest operationDtoRequest,
                                                @PathVariable Long operationId, @PathVariable Long clientId) {
        operationFacade.update(operationDtoRequest, operationId);
        return "redirect:/clients/client/{clientId}/account/{accountId}/operation/{operationId}/details";
    }

    @GetMapping("/client/{clientId}/account/{accountId}/update/operation/{operationId}")
    public String updateOperation(Model model, @PathVariable Long clientId,
                                  @PathVariable Long accountId, @PathVariable Long operationId) {
        AccountDtoResponse accountDtoResponse = accountFacade.findById(accountId);
        ClientDtoResponse clientDtoResponse = clientFacade.findById(clientId);
        OperationDtoResponse operationDtoResponse = operationFacade.findById(operationId);
        model.addAttribute("account", accountDtoResponse);
        model.addAttribute("client", clientDtoResponse);
        model.addAttribute("operation", operationDtoResponse);
        model.addAttribute("operationType", OperationType.values());
        return "pages/operation/operation_update";
    }
}
