package courseWork.controllers;

import courseWork.logic.Client;
import courseWork.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.NoSuchElementException;

@Controller
public class MainController {

    private ClientService clientService;
    @Autowired
    public void setService(@NotNull final  ClientService service) {
        this.clientService  = service;
    }

    @GetMapping("/enter")
    public String index1( ) {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Integer> logInUser(@RequestParam String login, @RequestParam String password) {
        if (null == login) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Client client = clientService.findClient1(login, password);
            if (client != null)
                return new ResponseEntity<>(3, HttpStatus.OK);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
