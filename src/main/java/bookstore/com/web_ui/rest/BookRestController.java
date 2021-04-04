package bookstore.com.web_ui.rest;

import bookstore.com.core.request.AddBookRequest;
import bookstore.com.core.response.AddBookResponse;
import bookstore.com.core.service.AddBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookRestController {

    @Autowired
    private AddBookService addBookService;

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public AddBookResponse addBookResponse(@RequestBody AddBookRequest request){
    return addBookService.execute(request);
    }
}
