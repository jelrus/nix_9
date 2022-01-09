package ua.com.alevel.view.controller;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.HouseFacade;
import ua.com.alevel.facade.OwnerFacade;
import ua.com.alevel.persistence.entity.type.Status;
import ua.com.alevel.view.dto.request.OwnerDtoRequest;
import ua.com.alevel.view.dto.response.HouseDtoResponse;
import ua.com.alevel.view.dto.response.OwnerDtoResponse;
import ua.com.alevel.view.dto.response.PageData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/owners")
public class OwnerController extends AbstractController{

    private final OwnerFacade ownerFacade;
    private final HouseFacade houseFacade;

    public OwnerController(OwnerFacade ownerFacade, HouseFacade houseFacade) {
        this.ownerFacade = ownerFacade;
        this.houseFacade = houseFacade;
    }

    private HeaderName[] getColumnNames() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
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
        PageData<OwnerDtoResponse> response = ownerFacade.findAll(request);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnNames, response);
        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/owners/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Owners");
        model.addAttribute("allowCreate", true);
        model.addAttribute("createNewItemUrl", "/owners/new");
        return "pages/owner/owner_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/owners", model);
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("owner", new OwnerDtoRequest());
        return "pages/owner/owner_new";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        OwnerDtoResponse dto = ownerFacade.findById(id);
        model.addAttribute("owner", dto);
        return "pages/owner/owner_details";
    }

    @PostMapping("/create")
    public String createNewOwner(@ModelAttribute("owner") OwnerDtoRequest dto) {
        ownerFacade.create(dto);
        return "redirect:/owners";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        ownerFacade.delete(id);
        return "redirect:/owners";
    }

    @PostMapping("/update/{id}")
    public String updateOwner(@PathVariable Long id, @ModelAttribute("owner") OwnerDtoRequest ownerDtoRequest) {
        ownerFacade.update(ownerDtoRequest, id);
        return "redirect:/owners";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        OwnerDtoResponse ownerResponseDto = ownerFacade.findById(id);
        List<HouseDtoResponse> attachedHouses = ownerFacade.findHouses(id);
        List<HouseDtoResponse> allHouses = houseFacade.findAll();
        List<HouseDtoResponse> detachedHouses = new ArrayList<>(allHouses);
        detachedHouses.removeAll(attachedHouses);
        model.addAttribute("owner", ownerResponseDto);
        model.addAttribute("status", Status.values());
        model.addAttribute("attachedHouses", attachedHouses);
        model.addAttribute("detachedHouses", detachedHouses);
        return "pages/owner/owner_update";
    }

    @GetMapping("/all/house/{houseId}")
    public String findAllByHouse(@PathVariable Long houseId, Model model, WebRequest request) {
        HeaderName[] columnNames = getColumnNames();
        PageData<OwnerDtoResponse> response = ownerFacade.findByHouseId(request, houseId);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnNames, response);
        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/owners/all/house/" + houseId);
        model.addAttribute("createNewItemUrl", "/owners/new");
        model.addAttribute("pageData", response);
        model.addAttribute("allowCreate", false);
        model.addAttribute("cardHeader", "All Owners");
        return "pages/owner/owner_all";
    }

    @PostMapping("/all/house/{houseId}")
    public ModelAndView findAllByHouseRedirect(@PathVariable Long houseId, WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/owners/all/house/" + houseId, model);
    }

    @GetMapping("/remove/{houseId}+{ownerId}")
    public String removeHouseFromOwner(@PathVariable Long houseId, @PathVariable Long ownerId, Model model) {
        ownerFacade.removeHouse(houseId, ownerId);
        List<HouseDtoResponse> houses = ownerFacade.findHouses(ownerId);
        model.addAttribute("owner", ownerFacade.findById(ownerId));
        model.addAttribute("houses", houses);
        return "redirect:/owners/update/{ownerId}";
    }

    @GetMapping("/add/{houseId}+{ownerId}")
    public String addOwnerToHouse(@PathVariable Long houseId, @PathVariable Long ownerId, Model model){
        ownerFacade.addHouse(houseId, ownerId);
        List<HouseDtoResponse> houses = ownerFacade.findHouses(ownerId);
        model.addAttribute("owner", ownerFacade.findById(ownerId));
        model.addAttribute("houses", houses);
        return "redirect:/owners/update/{ownerId}";
    }
}
