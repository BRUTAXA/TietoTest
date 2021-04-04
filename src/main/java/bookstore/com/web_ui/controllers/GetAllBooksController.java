package bookstore.com.web_ui.controllers;

import bookstore.com.core.request.GetAllBooksRequest;
import bookstore.com.core.response.GetAllBooksResponse;
import bookstore.com.core.service.GetAllBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetAllBooksController {

    @Autowired
    private GetAllBooksService getAllBooksService;

    @GetMapping(value = "/getAllBooks")
    public String getAllBooksList(ModelMap modelMap){
        GetAllBooksResponse response = getAllBooksService.execute(new GetAllBooksRequest());
        modelMap.addAttribute("books", response.getBooks());
        return "/getAllBooks";
    }
}
