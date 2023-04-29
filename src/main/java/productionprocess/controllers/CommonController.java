package productionprocess.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import productionprocess.services.EmployeeService;

@Controller
public class CommonController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/home")
    public String homePage(Model model){
        return "home";
    }
    
}
