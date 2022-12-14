package be.sagaeva.financial.manager.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String handleExpenseNotFoundException(HttpServletRequest request,
                                                 RuntimeException ex, Model model) {
        model.addAttribute("notFound", true);
        model.addAttribute("message", ex.getMessage());
        return "response";

    }


    @ExceptionHandler(Exception.class)
    public String handleGlobalException(HttpServletRequest request,
                                                Exception ex, Model model) {
        model.addAttribute("serverError", true);
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("stackTrack", ex.getStackTrace());
        return "response";

    }


}
