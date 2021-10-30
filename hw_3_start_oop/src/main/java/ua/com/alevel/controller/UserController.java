package ua.com.alevel.controller;

import ua.com.alevel.console.log.ErrorLog;
import ua.com.alevel.console.log.Log;
import ua.com.alevel.console.log.entries.Entry;
import ua.com.alevel.console.menu.Menu;
import ua.com.alevel.console.messages.ErrorMessages;
import ua.com.alevel.console.messages.Messages;
import ua.com.alevel.console.utilities.InputUtils;
import customcollections.DynamicArray;
import ua.com.alevel.entity.User;
import ua.com.alevel.service.UserService;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;

public class UserController {
    private static final UserService userService = new UserService();
    public static LinkedList<Integer> stackOfTasks = new LinkedList<>();

    public static void create() throws IOException, ParseException{
        stackOfTasks.add(1);
        Messages.getInputNameMessage();
        String name = InputUtils.returnLine();
        Messages.getInputLastNameMessage();
        String lastName = InputUtils.returnLine();
        Messages.getInputAgeMessage();
        String age = InputUtils.returnLine();
        Messages.getInputEmailMessage();
        String email = InputUtils.returnLine();
        String input = name + ", " + lastName + ", " + age + ", " + email;
        if (InputUtils.checkEmpty(name) && InputUtils.checkBlank(name) && InputUtils.checkIfAlphabeticApostrophe(name)
                && InputUtils.checkEmpty(lastName) && InputUtils.checkBlank(lastName)
                && InputUtils.checkIfAlphabeticApostrophe(lastName) && InputUtils.checkEmpty(age)
                && InputUtils.checkBlank(age) && InputUtils.checkIfNumber(age) && InputUtils.checkEmpty(email)
                && InputUtils.checkBlank(email) && InputUtils.checkEmailPattern(email)){
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(Integer.parseInt(age));
            user.setEmail(email);
            userService.create(user);
            Messages.successCreate(user.getId(),name,lastName,age,email);
            Log.log.add(new Entry("Create",input, "Success"));
            Menu.runSubMenu();
        } else if (!InputUtils.checkEmpty(name)){
            ErrorMessages.getEmptyNameInputErrorMessage();
            ErrorLog.errorLog.add(new Entry("Create", input,"Empty name"));
            Menu.runSubMenu();
        } else if (!InputUtils.checkBlank(name)){
            ErrorMessages.getBlankNameInputErrorMessage();
            ErrorLog.errorLog.add(new Entry("Create", input,"Blank name"));
            Menu.runSubMenu();
        } else if (!InputUtils.checkIfAlphabeticApostrophe(name)){
            ErrorMessages.getNameIsNotAlphabeticApostropheErrorMessage();
            ErrorLog.errorLog.add(new Entry("Create", input,"Name isn't alphabetic"));
            Menu.runSubMenu();
        } else if (!InputUtils.checkEmpty(lastName)){
            ErrorMessages.getEmptyLastNameInputErrorMessage();
            ErrorLog.errorLog.add(new Entry("Create", input,"Empty last name"));
            Menu.runSubMenu();
        } else if (!InputUtils.checkBlank(lastName)){
            ErrorMessages.getBlankLastNameInputErrorMessage();
            ErrorLog.errorLog.add(new Entry("Create", input,"Empty last name"));
            Menu.runSubMenu();
        } else if (!InputUtils.checkIfAlphabeticApostrophe(lastName)){
            ErrorMessages.getLastNameIsNotAlphabeticApostropheErrorMessage();
            ErrorLog.errorLog.add(new Entry("Create", input,"Last name isn't alphabetic"));
            Menu.runSubMenu();
        } else if (!InputUtils.checkEmpty(age)){
            ErrorMessages.getEmptyAgeInputErrorMessage();
            ErrorLog.errorLog.add(new Entry("Create", input,"Empty age"));
            Menu.runSubMenu();
        } else if (!InputUtils.checkBlank(age)){
            ErrorMessages.getBlankAgeInputErrorMessage();
            ErrorLog.errorLog.add(new Entry("Create", input,"Blank age"));
            Menu.runSubMenu();
        } else if (!InputUtils.checkIfNumber(age)){
            ErrorMessages.getNotNumberErrorMessage();
            ErrorLog.errorLog.add(new Entry("Create", input,"Age isn't number"));
            Menu.runSubMenu();
        } else if (!InputUtils.checkRange(age)){
            ErrorMessages.getOutOfRangeErrorMessage();
            ErrorLog.errorLog.add(new Entry("Create", input,"Age is out of range"));
            Menu.runSubMenu();
        } else if (!InputUtils.checkEmpty(email)){
            ErrorMessages.getEmptyEmailInputErrorMessage();
            ErrorLog.errorLog.add(new Entry("Create", input,"Empty email"));
            Menu.runSubMenu();
        } else if (!InputUtils.checkBlank(email)){
            ErrorMessages.getBlankEmailInputErrorMessage();
            ErrorLog.errorLog.add(new Entry("Create", input,"Blank email"));
            Menu.runSubMenu();
        } else if (!InputUtils.checkEmailPattern(email)){
            ErrorMessages.getEmailNotValidErrorMessage();
            ErrorLog.errorLog.add(new Entry("Create", input,"Email doesn't match pattern"));
            Menu.runSubMenu();
        }
        Menu.runSubMenu();
    }

    public static void update() throws IOException, ParseException{
        stackOfTasks.add(2);
        Messages.getInputIdMessage();
        String id = InputUtils.returnLine();
        Messages.getInputNameShortedMessage();
        String name = InputUtils.returnLine();
        Messages.getInputLastNameMessage();
        String lastName = InputUtils.returnLine();
        Messages.getInputAgeMessage();
        String age = InputUtils.returnLine();
        Messages.getInputEmailMessage();
        String email = InputUtils.returnLine();
        String input = id + " " + name + ", " + lastName + ", " + age + ", " + email;
        if (InputUtils.checkEmpty(id) && InputUtils.checkBlank(id) && InputUtils.checkEmpty(name)
                && InputUtils.checkBlank(name) && InputUtils.checkIfAlphabeticApostrophe(name)
                && InputUtils.checkEmpty(lastName) && InputUtils.checkBlank(lastName)
                && InputUtils.checkIfAlphabeticApostrophe(lastName) && InputUtils.checkEmpty(age)
                && InputUtils.checkBlank(age) && InputUtils.checkIfNumber(age) && InputUtils.checkEmpty(email)
                && InputUtils.checkBlank(email) && InputUtils.checkEmailPattern(email)){
            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(Integer.parseInt(age));
            user.setEmail(email);
            if (InputUtils.checkUserOnNull(userService.findById(id))) {
                userService.update(user);
                Messages.successUpdate(user.getId(), name, lastName, age, email);
                Log.log.add(new Entry("Update", input, "Success"));
            } else {
                ErrorMessages.getUserNotFoundErrorMessage();
                ErrorLog.errorLog.add(new Entry("Update", input,"User not found"));
            }
            Menu.runSubMenu();
        } else if (!InputUtils.checkEmpty(id)){
            ErrorMessages.getEmptyIdInputErrorMessage();
            ErrorLog.errorLog.add(new Entry("Update", input,"Empty id"));
            Menu.runSubMenu();
        } else if (!InputUtils.checkBlank(id)){
            ErrorMessages.getBlankIdInputErrorMessage();
            ErrorLog.errorLog.add(new Entry("Update", input,"Blank id"));
            Menu.runSubMenu();
        } else if (!InputUtils.checkEmpty(name)){
            ErrorMessages.getEmptyNameInputErrorMessage();
            ErrorLog.errorLog.add(new Entry("Update", input,"Empty name"));
            Menu.runSubMenu();
        } else if (!InputUtils.checkBlank(name)){
            ErrorMessages.getBlankNameInputErrorMessage();
            ErrorLog.errorLog.add(new Entry("Update", input,"Blank name"));
            Menu.runSubMenu();
        } else if (!InputUtils.checkIfAlphabeticApostrophe(name)){
            ErrorMessages.getNameIsNotAlphabeticApostropheErrorMessage();
            ErrorLog.errorLog.add(new Entry("Update", input,"Name isn't alphabetic"));
            Menu.runSubMenu();
        } else if (!InputUtils.checkEmpty(lastName)){
            ErrorMessages.getEmptyLastNameInputErrorMessage();
            ErrorLog.errorLog.add(new Entry("Update", input,"Empty last name"));
            Menu.runSubMenu();
        } else if (!InputUtils.checkBlank(lastName)){
            ErrorMessages.getBlankLastNameInputErrorMessage();
            ErrorLog.errorLog.add(new Entry("Update", input,"Empty last name"));
            Menu.runSubMenu();
        } else if (!InputUtils.checkIfAlphabeticApostrophe(lastName)){
            ErrorMessages.getLastNameIsNotAlphabeticApostropheErrorMessage();
            ErrorLog.errorLog.add(new Entry("Update", input,"Last name isn't alphabetic"));
            Menu.runSubMenu();
        } else if (!InputUtils.checkEmpty(age)){
            ErrorMessages.getEmptyAgeInputErrorMessage();
            ErrorLog.errorLog.add(new Entry("Update", input,"Empty age"));
            Menu.runSubMenu();
        } else if (!InputUtils.checkBlank(age)){
            ErrorMessages.getBlankAgeInputErrorMessage();
            ErrorLog.errorLog.add(new Entry("Update", input,"Blank age"));
            Menu.runSubMenu();
        } else if (!InputUtils.checkIfNumber(age)){
            ErrorMessages.getNotNumberErrorMessage();
            ErrorLog.errorLog.add(new Entry("Update", input,"Age isn't number"));
            Menu.runSubMenu();
        } else if (!InputUtils.checkRange(age)){
            ErrorMessages.getOutOfRangeErrorMessage();
            ErrorLog.errorLog.add(new Entry("Update", input,"Age is out of range"));
            Menu.runSubMenu();
        } else if (!InputUtils.checkEmpty(email)){
            ErrorMessages.getEmptyEmailInputErrorMessage();
            ErrorLog.errorLog.add(new Entry("Update", input,"Empty email"));
            Menu.runSubMenu();
        } else if (!InputUtils.checkBlank(email)){
            ErrorMessages.getBlankEmailInputErrorMessage();
            ErrorLog.errorLog.add(new Entry("Update", input,"Blank email"));
            Menu.runSubMenu();
        } else if (!InputUtils.checkEmailPattern(email)){
            ErrorMessages.getEmailNotValidErrorMessage();
            ErrorLog.errorLog.add(new Entry("Update", input,"Email doesn't match pattern"));
            Menu.runSubMenu();
        }
        Menu.runSubMenu();
    }

    public static void delete() throws IOException, ParseException {
        stackOfTasks.add(3);
        Messages.getInputIdMessage();
        String id = InputUtils.returnLine();
        if (InputUtils.checkEmpty(id) && InputUtils.checkBlank(id)){
            if (InputUtils.checkUserOnNull(userService.findById(id))) {
                userService.delete(id);
                Messages.successDelete(id);
                Log.log.add(new Entry("Delete", id, "Success"));
            } else {
                ErrorMessages.getUserNotFoundErrorMessage();
                ErrorLog.errorLog.add(new Entry("Delete", id,"User not found"));
            }
            Menu.runSubMenu();
        } else if (!InputUtils.checkEmpty(id)){
            ErrorMessages.getEmptyIdInputErrorMessage();
            ErrorLog.errorLog.add(new Entry("Delete", id,"Empty id"));
            Menu.runSubMenu();
        } else if (!InputUtils.checkBlank(id)) {
            ErrorMessages.getBlankIdInputErrorMessage();
            ErrorLog.errorLog.add(new Entry("Delete", id, "Blank id"));
            Menu.runSubMenu();
        }
        Menu.runSubMenu();
    }

    public static void findById() throws IOException, ParseException {
        stackOfTasks.add(4);
        Messages.getInputIdMessage();
        String id = InputUtils.returnLine();
        if (InputUtils.checkEmpty(id) && InputUtils.checkBlank(id)){
            if (InputUtils.checkUserOnNull(userService.findById(id))) {
                User user = userService.findById(id);
                Messages.successFindById(user.getId(), user.getName(), user.getLastName(),
                        String.valueOf(user.getAge()), user.getEmail());
                Log.log.add(new Entry("Find by id", id, "Success"));
            } else {
                ErrorMessages.getUserNotFoundErrorMessage();
                ErrorLog.errorLog.add(new Entry("Find by id", id,"User not found"));
            }
            Menu.runSubMenu();
        } else if (!InputUtils.checkEmpty(id)){
            ErrorMessages.getEmptyIdInputErrorMessage();
            ErrorLog.errorLog.add(new Entry("Find by id", id,"Empty id"));
            Menu.runSubMenu();
        } else if (!InputUtils.checkBlank(id)) {
            ErrorMessages.getBlankIdInputErrorMessage();
            ErrorLog.errorLog.add(new Entry("Find by id", id, "Blank id"));
            Menu.runSubMenu();
        }
        Menu.runSubMenu();
    }

    public static void findAll() throws IOException, ParseException{
        stackOfTasks.add(5);
        StringBuilder table = new StringBuilder();
        DynamicArray<User> users = userService.findAll();
        if (users != null && users.size() != 0){
            for (int i = 0; i<users.size(); i++){
                table.append(users.get(i)).append("\n");
            }
            Messages.returnUserList(table.toString());
            Log.log.add(new Entry("Find All","", "Success"));
        } else {
            ErrorMessages.getArrayIsEmptyErrorMessage();
            ErrorLog.errorLog.add(new Entry("Find All", "","Array is empty"));
        }
        Menu.runSubMenu();
    }
}