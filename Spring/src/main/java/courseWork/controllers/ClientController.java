package courseWork.controllers;

import courseWork.logic.Product;
import courseWork.service.ClientService;
import courseWork.service.productService;
import courseWork.storage.OrderRepo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import courseWork.storage.ClientRepo;
import courseWork.logic.Client;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Controller
@RequestMapping(path="/shop")
public class ClientController {
    private ClientService clientService;
    courseWork.service.productService productService;
    @Autowired
    public void setService(@NotNull final  ClientService service, @NotNull final productService productService) {
        this.clientService  = service;
        this.productService = productService;
    }


    @GetMapping(path="/allClients")
    public @ResponseBody Iterable<Client> getAllUsers() {
        return clientService.getAllClients();
    }

    @RequestMapping(value = "/client",  params = { "login", "password" }, method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Client getClient(@RequestParam("login") String login, @RequestParam("password") String password) {
        Client client = clientService.findClient1(login, password);;
//        if (client != null)
//            this.currentClient = client;
        return client;
    }

    @GetMapping(path="/currentclient")
    public @ResponseBody Client getCurrentClient()
    {
        return this.clientService.getCurrentClient();
    }
}