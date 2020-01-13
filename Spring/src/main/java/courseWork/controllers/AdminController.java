package courseWork.controllers;

import courseWork.logic.Administrator;
import courseWork.storage.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping(path="/shop")
public class AdminController {
    @Autowired
    private AdminRepo adminRepo;
}