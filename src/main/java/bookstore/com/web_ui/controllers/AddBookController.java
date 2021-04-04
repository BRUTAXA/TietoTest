package bookstore.com.web_ui.controllers;

import bookstore.com.core.request.AddBookRequest;
import bookstore.com.core.response.AddBookResponse;
import bookstore.com.core.service.AddBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddBookController {

    @Autowired private AddBookService addBookService;

    @GetMapping(value = "/addBook")
    public String showAddBookPage(ModelMap modelMap){
        modelMap.addAttribute("request", new AddBookRequest());
        return "addBook";
    }

    @PostMapping("/addBook")
    public String processAddBookRequest(@ModelAttribute(value = "request") AddBookRequest request, ModelMap modelMap){
        AddBookResponse response = addBookService.execute(request);
        if (response.hasErrors()){
            modelMap.addAttribute("errors", response.getErrors());
            return "addBook";
        } else {
            return "redirect:/";
        }
    }
}
