package ua.com.alevel.controller.impl;

import ua.com.alevel.controller.EmployeeController;
import ua.com.alevel.controller.utils.input.InputUtils;
import ua.com.alevel.controller.utils.messages.ErrorMessages;
import ua.com.alevel.controller.utils.messages.MenuMessages;
import ua.com.alevel.dto.employee.EmployeeRequestDTO;
import ua.com.alevel.dto.employee.EmployeeResponseDTO;
import ua.com.alevel.facade.EmployeeFacade;
import ua.com.alevel.facade.impl.EmployeeFacadeImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Locale;

public class EmployeeControllerImpl implements EmployeeController {

    private final EmployeeFacade employeeFacade = new EmployeeFacadeImpl();

    @Override
    public void run() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
            IllegalAccessException {
        MenuMessages.employeeMenuText();
        String option = InputUtils.INPUT_READER.readLine();
        switch (option) {
            case "1" -> create();
            case "2" -> update();
            case "3" -> delete();
            case "4" -> findById();
            case "5" -> findAll();
            case "e" -> UnifiedController.run();
            default -> {
                ErrorMessages.optionError();
                run();
            }
        }
        returnOption();
    }

    public void returnOption() throws IOException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        MenuMessages.returnBackOption();
        String option = InputUtils.INPUT_READER.readLine().toUpperCase(Locale.ROOT);
        switch (option) {
            case "Y" -> UnifiedController.run();
            case "N" -> run();
            default -> {
                ErrorMessages.optionError();
                returnOption();
            }
        }
    }

    @Override
    public void create() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
            IllegalAccessException {
        String firstName = InputUtils.checkEmployeeFirstName(InputUtils.INPUT_READER);
        String lastName = InputUtils.checkEmployeeLastName(InputUtils.INPUT_READER);
        int age = InputUtils.checkEmployeeAge(InputUtils.INPUT_READER);
        String email = InputUtils.checkEmployeeEmail(InputUtils.INPUT_READER);
        BigDecimal salary = InputUtils.checkEmployeeSalary(InputUtils.INPUT_READER);
        EmployeeRequestDTO employeeRequestDTO = new EmployeeRequestDTO(firstName, lastName, age, email, salary);
        employeeFacade.create(employeeRequestDTO);
    }

    @Override
    public void update() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
            IllegalAccessException {
        MenuMessages.printAllEmployees(employeeFacade);
        String id = InputUtils.checkEmployeeId(InputUtils.INPUT_READER);
        EmployeeResponseDTO employeeResponseDTO = employeeFacade.findById(id);
        String newFirstName = InputUtils.checkEmployeeNewFirstName(InputUtils.INPUT_READER, employeeResponseDTO.getFirstName());
        String newLastName = InputUtils.checkEmployeeNewLastName(InputUtils.INPUT_READER, employeeResponseDTO.getLastName());
        int newAge = InputUtils.checkEmployeeNewAge(InputUtils.INPUT_READER, employeeResponseDTO.getAge());
        String newEmail = InputUtils.checkEmployeeNewEmail(InputUtils.INPUT_READER, employeeResponseDTO.getEmail());
        BigDecimal newSalary = InputUtils.checkEmployeeNewSalary(InputUtils.INPUT_READER, employeeResponseDTO.getSalary());
        EmployeeRequestDTO employeeRequestDTO = new EmployeeRequestDTO(newFirstName, newLastName, newAge, newEmail, newSalary);
        employeeFacade.update(employeeRequestDTO, id);
    }

    @Override
    public void delete() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
            IllegalAccessException {
        MenuMessages.printAllEmployees(employeeFacade);
        String id = InputUtils.checkEmployeeId(InputUtils.INPUT_READER);
        employeeFacade.delete(id);
    }

    @Override
    public void findById() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MenuMessages.printAllEmployees(employeeFacade);
        String id = InputUtils.checkEmployeeId(InputUtils.INPUT_READER);
        EmployeeResponseDTO employeeResponseDTO = employeeFacade.findById(id);
        MenuMessages.printEmployee(employeeResponseDTO);
    }

    @Override
    public void findAll() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
            IllegalAccessException {
        if (!employeeFacade.findAll().isEmpty()) {
            MenuMessages.printAllEmployees(employeeFacade);
        } else {
            ErrorMessages.printDepartmentsListIsEmpty();
        }
    }
}