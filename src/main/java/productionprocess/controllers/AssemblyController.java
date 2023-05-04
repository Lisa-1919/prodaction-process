package productionprocess.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import productionprocess.services.OrderOnProductionService;

@Controller
public class AssemblyController {
    @Autowired
    private OrderOnProductionService orderOnProductionService;
}
