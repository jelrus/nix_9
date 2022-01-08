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
import ua.com.alevel.view.dto.request.HouseDtoRequest;
import ua.com.alevel.view.dto.response.HouseDtoResponse;
import ua.com.alevel.view.dto.response.OwnerDtoResponse;
import ua.com.alevel.view.dto.response.PageData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/houses")
public class HouseController extends AbstractController {

    private final HouseFacade houseFacade;
    private final OwnerFacade ownerFacade;

    public HouseController(HouseFacade houseFacade, OwnerFacade ownerFacade) {
        this.houseFacade = houseFacade;
        this.ownerFacade = ownerFacade;
    }

    private HeaderName[] getColumnNames() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("Image", "image", "image"),
                new HeaderName("Country", "country", "country"),
                new HeaderName("City", "city", "city"),
                new HeaderName("Street", "street", "street"),
                new HeaderName("Building number", "buildingNumber", "building_number"),
                new HeaderName("Status", "status", "status"),
                new HeaderName("Cost, $", "cost", "cost"),
                new HeaderName("Details", null, null),
                new HeaderName("Delete", null, null)
        };
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        HeaderName[] columnNames = getColumnNames();
        PageData<HouseDtoResponse> response = houseFacade.findAll(webRequest);
        response.initPaginationState(response.getCurrentPage());
        model.addAttribute("headerDataList", getHeaderDataList(columnNames, response));
        model.addAttribute("createUrl", "/houses/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Houses");
        model.addAttribute("allowCreate", true);
        model.addAttribute("createNewItemUrl", "/houses/new");
        return "pages/house/house_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/houses", model);
    }


    @GetMapping("/new")
    public String redirectToNewHousePage(Model model) {
        model.addAttribute("house", new HouseDtoRequest());
        model.addAttribute("status", Status.values());
        return "pages/house/house_new";
    }

    @PostMapping("/create")
    public String createNewHouse(@ModelAttribute("house") HouseDtoRequest dto) {
        houseFacade.create(dto);
        return "redirect:/houses";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        houseFacade.delete(id);
        return "redirect:/houses";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        HouseDtoResponse dto = houseFacade.findById(id);
        model.addAttribute("house", dto);
        return "pages/house/house_details";
    }

    @PostMapping("/update/{id}")
    public String updateHouse(@PathVariable Long id, @ModelAttribute("house") HouseDtoRequest houseRequestDto) {
        houseFacade.update(houseRequestDto, id);
        return "redirect:/houses";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        HouseDtoResponse houseResponseDto = houseFacade.findById(id);
        List<OwnerDtoResponse> attachedOwners = houseFacade.findOwners(id);
        List<OwnerDtoResponse> allOwners = ownerFacade.findAll();
        List<OwnerDtoResponse> detachedOwners = new ArrayList<>(allOwners);
        detachedOwners.removeAll(attachedOwners);
        model.addAttribute("house", houseResponseDto);
        model.addAttribute("status", Status.values());
        model.addAttribute("attachedOwners", attachedOwners);
        model.addAttribute("detachedOwners", detachedOwners);
        return "pages/house/house_update";
    }

    @GetMapping("/all/owner/{ownerId}")
    public String findAllByHouse(@PathVariable Long ownerId, Model model, WebRequest request) {
        HeaderName[] columnNames = getColumnNames();
        PageData<HouseDtoResponse> response = houseFacade.findByOwnerId(request, ownerId);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnNames, response);
        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/houses/all/owner/" + ownerId);
        model.addAttribute("createNewItemUrl", "/houses/new");
        model.addAttribute("pageData", response);
        model.addAttribute("allowCreate", false);
        model.addAttribute("cardHeader", "All Houses");
        return "pages/house/house_all";
    }

    @PostMapping("/all/owner/{ownerId}")
    public ModelAndView findAllByOwnersRedirect(@PathVariable Long ownerId, WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/houses/all/owner/" + ownerId, model);
    }

    @GetMapping("/remove/{houseId}+{ownerId}")
    public String removeOwnerFromHouse(@PathVariable Long houseId, @PathVariable Long ownerId, Model model) {
        houseFacade.removeOwner(houseId, ownerId);
        List<OwnerDtoResponse> owners = houseFacade.findOwners(houseId);
        model.addAttribute("house", houseFacade.findById(houseId));
        model.addAttribute("owners", owners);
        return "redirect:/houses/update/{houseId}";
    }

    @GetMapping("/add/{houseId}+{ownerId}")
    public String addOwnerToHouse(@PathVariable Long houseId, @PathVariable Long ownerId, Model model){
        houseFacade.addOwner(houseId, ownerId);
        List<OwnerDtoResponse> owners = houseFacade.findOwners(houseId);
        model.addAttribute("house", houseFacade.findById(houseId));
        model.addAttribute("owners", owners);
        return "redirect:/houses/update/{houseId}";
    }
}