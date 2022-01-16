package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import ua.com.alevel.facade.AccountFacade;
import ua.com.alevel.facade.ClientFacade;
import ua.com.alevel.facade.OperationFacade;
import ua.com.alevel.persistence.entity.Operation;
import ua.com.alevel.persistence.entity.type.OperationType;
import ua.com.alevel.view.dto.request.AccountDtoRequest;
import ua.com.alevel.view.dto.request.OperationDtoRequest;
import ua.com.alevel.view.dto.response.AccountDtoResponse;
import ua.com.alevel.view.dto.response.ClientDtoResponse;
import ua.com.alevel.view.dto.response.OperationDtoResponse;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/clients")
public class AccountController {

    private final ClientFacade clientFacade;
    private final AccountFacade accountFacade;
    private final OperationFacade operationFacade;

    public AccountController(ClientFacade clientFacade,
                             AccountFacade accountFacade,
                             OperationFacade operationFacade) {
        this.clientFacade = clientFacade;
        this.accountFacade = accountFacade;
        this.operationFacade = operationFacade;
    }

    @GetMapping("/client/{clientId}/new/account")
    public String redirectToNewAccountPage(Model model, @PathVariable Long clientId) {
        model.addAttribute("account", new AccountDtoRequest());
        model.addAttribute("client", clientFacade.findById(clientId));
        return "pages/account/account_new";
    }

    @PostMapping("/client/{clientId}/create/account")
    public String createNewAccount(@ModelAttribute("account") AccountDtoRequest dto, @PathVariable Long clientId) {
        accountFacade.create(dto);
        AccountDtoResponse account = accountFacade.findAll().get(accountFacade.findAll().size()-1);
        clientFacade.addAccount(dto.getClientId(), account.getId());
        return "redirect:/clients/client/{clientId}/accounts";
    }

    @GetMapping("/client/{clientId}/delete/account/{accountId}")
    public String deleteAccount(@PathVariable Long clientId, @PathVariable Long accountId) {
        accountFacade.delete(accountId);
        return "redirect:/clients/client/{clientId}/accounts";
    }

    @GetMapping("/client/{clientId}/account/{accountId}/details")
    public String accountDetails(Model model, @PathVariable Long clientId, @PathVariable Long accountId) {
        List<OperationDtoResponse> attachedOperations = accountFacade.findOperations(accountId);
        AccountDtoResponse accountDto = accountFacade.findById(accountId);
        ClientDtoResponse clientDtoResponse = clientFacade.findById(clientId);
        model.addAttribute("account", accountDto);
        model.addAttribute("attachedOperations", attachedOperations);
        model.addAttribute("client", clientDtoResponse);
        return "pages/account/account_details";
    }

    @PostMapping("/client/{clientId}/update/account/{accountId}")
    public String redirectToUpdateAccountPage(@ModelAttribute("account") AccountDtoRequest accountDtoRequest,
                                              @PathVariable Long accountId) {
        accountFacade.update(accountDtoRequest, accountId);
        return "redirect:/clients/client/{clientId}/account/{accountId}/details";
    }

    @GetMapping("/client/{clientId}/update/account/{accountId}")
    public String updateAccount(Model model, @PathVariable Long clientId, @PathVariable Long accountId) {
        AccountDtoResponse accountDtoResponse = accountFacade.findById(accountId);
        ClientDtoResponse clientDtoResponse = clientFacade.findById(clientId);
        model.addAttribute("account", accountDtoResponse);
        model.addAttribute("client", clientDtoResponse);
        return "pages/account/account_update";
    }

    @GetMapping("/client/{clientId}/account/{accountId}/operations")
    public String findOperationsByAccount(@PathVariable Long clientId, @PathVariable Long accountId,  Model model) {
        List<OperationDtoResponse> attachedOperations = accountFacade.findOperations(accountId);
        AccountDtoResponse accountDtoResponse = accountFacade.findById(accountId);
        model.addAttribute("attachedOperations", attachedOperations);
        model.addAttribute("account", accountDtoResponse);
        return "pages/account/account_operations";
    }

    @GetMapping("/client/{clientId}/account/{senderId}/transaction/new")
    public String selectRecipient(Model model, @PathVariable Long clientId,
                                  @PathVariable Long senderId,
                                  @ModelAttribute ("recipient") AccountDtoRequest recipient) {
        List<AccountDtoResponse> accounts = accountFacade.findAll();
        List<AccountDtoResponse> accountsExclusive = new ArrayList<>(accounts);
        accountsExclusive.remove(accountFacade.findById(senderId));
        model.addAttribute("accounts", accountsExclusive);
        model.addAttribute("recipient", new AccountDtoRequest());
        return "pages/account/recipient_selection_page";
    }

    @PostMapping("/client/{clientId}/account/{senderId}/transaction/new/")
    public String postRecipient(@PathVariable Long clientId,
                                @PathVariable Long senderId,
                                @ModelAttribute("recipient") AccountDtoRequest recipient) {
        List<AccountDtoResponse> accounts = accountFacade.findAll();
        AccountDtoResponse response = accounts.stream()
                .filter(it -> it.getAccountNumber().equals(recipient.getAccountNumber()))
                .findFirst().get();
        Long recipientId = response.getId();
        return "redirect:/clients/client/{clientId}/account/{senderId}/transaction/new/" + recipientId;
    }

    @GetMapping("/client/{clientId}/account/{senderId}/transaction/new/{recipientId}")
    public String selectTransactionInfo(Model model,
                                        @PathVariable String clientId,
                                        @PathVariable String recipientId,
                                        @PathVariable String senderId,
                                        @ModelAttribute("outcome") OperationDtoRequest outcome,
                                        @ModelAttribute("income") OperationDtoRequest income) {
        model.addAttribute("outcome", new OperationDtoRequest());
        model.addAttribute("income", new OperationDtoRequest());
        return "pages/account/transaction_page";
    }

    @PostMapping("/client/{clientId}/account/{senderId}/transaction/new/{recipientId}")
    public String postTransactionInfo(@PathVariable Long clientId,
                                      @PathVariable Long recipientId,
                                      @PathVariable Long senderId,
                                      @ModelAttribute("outcome") OperationDtoRequest outcome,
                                      @ModelAttribute("income") OperationDtoRequest income) {
        outcome.setOperationType(OperationType.OUTCOME);
        operationFacade.create(outcome);
        OperationDtoResponse operationOutcome = operationFacade.findAll().get(operationFacade.findAll().size()-1);

        income.setOperationType(OperationType.INCOME);
        income.setName(outcome.getName());
        income.setSum(outcome.getSum());
        income.setDescription(outcome.getDescription());
        operationFacade.create(income);

        OperationDtoResponse operationIncome = operationFacade.findAll().get(operationFacade.findAll().size()-1);
        accountFacade.makeTransaction(senderId, operationOutcome.getId(), recipientId, operationIncome.getId());
        return "redirect:/clients/client/{clientId}/account/{senderId}/details";
    }

    @GetMapping("/export/csv/{accountId}")
    public void exportCSV(HttpServletResponse response, @PathVariable Long accountId) throws Exception {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=account_" + accountId + "_operations_on_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<Operation> operations = accountFacade.findById(accountId).getOperations();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Created", "Name", "Operation Type", "Sum", "Description"};
        String[] nameMapping = {"created", "name", "operationType", "sum", "description"};

        csvWriter.writeHeader(csvHeader);

        for (Operation operation : operations) {
            csvWriter.write(operation, nameMapping);
        }

        csvWriter.close();
    }}
