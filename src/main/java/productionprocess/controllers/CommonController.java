package productionprocess.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import productionprocess.services.AccountService;

@Controller
public class CommonController {

    @Autowired
    private AccountService accountService;

    
}
