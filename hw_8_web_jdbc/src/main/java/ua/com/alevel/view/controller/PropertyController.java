package ua.com.alevel.view.controller;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.HouseFacade;
import ua.com.alevel.facade.PropertyFacade;
import ua.com.alevel.facade.OwnerFacade;
import ua.com.alevel.view.dto.request.PropertyDtoRequest;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.PropertyDtoResponse;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/properties")
public class PropertyController extends AbstractController {

    private final PropertyFacade propertyFacade;
    private final HouseFacade houseFacade;
    private final OwnerFacade ownerFacade;

    public PropertyController(PropertyFacade propertyFacade, HouseFacade houseFacade, OwnerFacade ownerFacade) {
        this.propertyFacade = propertyFacade;
        this.houseFacade = houseFacade;
        this.ownerFacade = ownerFacade;
    }

    private HeaderName[] getColumnNames() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("Image", "image", "image"),
                new HeaderName("Status", "status", "status"),
                new HeaderName("Cost", "cost","cost"),
                new HeaderName("First name", "firstName", "first_name"),
                new HeaderName("Last name", "lastName", "last_name"),
                new HeaderName("Email", "email", "email"),
                new HeaderName("Phone", "phone", "phone"),
                new HeaderName("Details", null, null),
                new HeaderName("Delete", null, null)
        };
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        HeaderName[] columnNames = getColumnNames();
        PageData<PropertyDtoResponse> response = propertyFacade.findAll(request);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnNames, response);
        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/properties/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Properties");
        model.addAttribute("allowCreate", true);
        model.addAttribute("createNewItemUrl", "/properties/new");
        return "pages/property/property_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/properties", model);
    }

    @GetMapping("/new")
    public String redirectToNewPropertyPage(Model model) {
        model.addAttribute("property", new PropertyDtoRequest());
        model.addAttribute("houses", houseFacade.findAll());
        model.addAttribute("owners", ownerFacade.findAll());
        return "pages/property/property_new";
    }

    @PostMapping("/create")
    public String createNewRecord(@ModelAttribute("property") PropertyDtoRequest dto) {
        propertyFacade.create(dto);
        return "redirect:/properties";
    }

    @PostMapping("/update/{id}")
    public String updateRecord(@PathVariable Long id, @ModelAttribute("property") PropertyDtoRequest dto) {
        propertyFacade.update(dto, id);
        return "redirect:/properties";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        PropertyDtoResponse propertyResponseDto = propertyFacade.findById(id);
        model.addAttribute("property", propertyResponseDto);
        model.addAttribute("houses", houseFacade.findAll());
        model.addAttribute("owners", ownerFacade.findAll());
        return "pages/property/property_update";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        PropertyDtoResponse dto = propertyFacade.findById(id);
        model.addAttribute("property", dto);
        return "pages/property/property_details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        propertyFacade.delete(id);
        return "redirect:/properties";
    }
}