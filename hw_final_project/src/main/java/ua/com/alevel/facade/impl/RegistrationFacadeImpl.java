package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.facade.RegistrationFacade;
import ua.com.alevel.persistence.entity.user.StandardUser;
import ua.com.alevel.service.StandardUserService;
import ua.com.alevel.view.dto.request.register.AuthDto;

@Service
public class RegistrationFacadeImpl implements RegistrationFacade {

    private final StandardUserService standardUserService;

    public RegistrationFacadeImpl(StandardUserService standardUserService) {
        this.standardUserService = standardUserService;
    }

    @Override
    public void registration(AuthDto dto) {
        StandardUser standardUser = new StandardUser();
        standardUser.setUsername(dto.getUsername());
        standardUser.setPassword(dto.getPassword());
        standardUser.setProfilePic("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__340.png");
        standardUser.setFirstName("");
        standardUser.setLastName("");
        standardUser.setDescription("");
        standardUserService.create(standardUser);
    }
}