package productionprocess.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import productionprocess.data.entities.Employee;
import productionprocess.services.EmployeeService;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Collections;

@Controller
public class AdminController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees/add")
    public String addEmployee(Model model){
        model.addAttribute("employee", new Employee());
        return "add_user";
    }

    @PostMapping("/employees/add")
    public String addManager(@ModelAttribute("employee") Employee employee, @RequestParam Integer roleId, Model model) {
        String password = employeeService.generatePassword();
        employee.setPassword(password);
        if(employeeService.saveEmployee(employee, roleId)) {
            try {
                Writer writer = new FileWriter("D:\\productionProcess\\src\\main\\resources\\employees.txt", true);
                writer.write(employee.getUsername() + " " + password + "\n");
                writer.flush();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return "redirect:/employees";
        }else {
            model.addAttribute("error", "Пользователь с таким email уже существует");
            return "add_user";
        }
    }

//    @PostMapping("/employees/add")
//    public String addManager(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String phoneNumber,
//                             @RequestParam String email, @RequestParam Integer roleId, Model model) {
//        String password = employeeService.generatePassword();
//
//        Employee employee = new Employee(firstName, lastName, phoneNumber, email, password);
//        employeeService.saveEmployee(employee, roleId);
//        try {
//            Writer writer = new FileWriter("D:\\productionProcess\\src\\main\\resources\\employees.txt", true);
//            writer.write(email + " " + password + "\n");
//            writer.flush();
//            writer.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return "redirect:/home";
//    }

    @GetMapping("/employees")
    public String getAllEmployees(Model model){
        model.addAttribute("employees", employeeService.findAll());
        return "admin";
    }

    @GetMapping("/employees/{id}")
    public String getEmployee(@PathVariable("id") Integer id, Model model){
        model.addAttribute("employee", employeeService.findById(id));
        return "edit_user";
    }

    @PostMapping("/employees/{id}")
    public String editEmployee(@ModelAttribute("employee") Employee employee, @RequestParam("roleId") Integer roleId, Model model){
        employeeService.editEmployee(employee, roleId);
        return "redirect:/employees";
    }

    @GetMapping("/employees/{id}/delete")
    public String deleteEmployee(@PathVariable("id") Integer id, Model model){
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }

    @GetMapping("/employees/search")
    public String searchEmployee(@RequestParam("email") String email, Model model){
        model.addAttribute("employees", employeeService.searchEmployeeByEmail(email));
        return "admin";
    }



}
