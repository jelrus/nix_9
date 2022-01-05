package ua.com.alevel.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ModelAndView defaultErrorHandler(EntityNotFoundException e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("showMessage", true);
        mav.addObject("errorMessage", e.getMessage());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(value = InputException.class)
    public ModelAndView InputErrorHandler(InputException e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("showMessage", true);
        mav.addObject("errorMessage", e.getMessage());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(value = DuplicateEntityException.class)
    public ModelAndView InputErrorHandler(DuplicateEntityException e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("showMessage", true);
        mav.addObject("errorMessage", e.getMessage());
        mav.setViewName("error");
        return mav;
    }
}