package productionprocess.services;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import productionprocess.data.entities.Employee;
import productionprocess.data.entities.Product;
import productionprocess.data.entities.Role;
import productionprocess.data.repo.EmployeeRepo;
import productionprocess.data.repo.RoleRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.passay.DigestDictionaryRule.ERROR_CODE;

@Service
public class EmployeeService implements UserDetailsService {
    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepo.findByEmail(email);
        if (employee == null) {
            throw new UsernameNotFoundException("Employee not found");
        }
        return employee;
    }

    public Employee findById(int id) {
        return employeeRepo.findById(id).orElseThrow();
    }

    public boolean existsById(int id) {
        return employeeRepo.existsById(id);
    }

    public List<Employee> findAll() {
        return (List<Employee>) employeeRepo.findAll();
    }

    public boolean saveEmployee(Employee employee, int roleId) {
        Employee employeeFromDB = employeeRepo.findByEmail(employee.getEmail());
        if (employeeFromDB != null) {
            return false;
        }
        //генерация пароля
        employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        Role role = roleRepo.findById(roleId).orElseThrow();
        employee.setRoles(Collections.singleton(role));
        employeeRepo.save(employee);
        return true;
    }

    public boolean deleteEmployee(int id) {
        if (employeeRepo.findById(id).isPresent()) {
            Employee employee = findById(id);
            for (Role role : employee.getRoles()) {
                if (role.getRoleName().equals("ADMIN")) {
                    return false;
                } else {
                    employeeRepo.deleteById(id);
                    return true;
                }
            }
        }
        return false;
    }

    public Employee getAuthorized() {
        return (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public void editEmployee(Employee employee, Integer roleId){
        Employee employeeDB = findById(employee.getId());
        Role role = roleRepo.findById(roleId).orElseThrow();
        employee.setRoles(Collections.singleton(role));
        employeeDB.setFirstName(employee.getFirstName());
        employeeDB.setLastName(employee.getLastName());
        employeeDB.setPhone(employee.getPhone());
        employeeDB.setEmail(employee.getEmail());
        employeeRepo.save(employeeDB);
    }

    public String generatePassword() {
        PasswordGenerator gen = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);
        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR_CODE;
            }

            public String getCharacters() {
                return "!@#$%^&*()_+";
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2);

        String password = gen.generatePassword(10, splCharRule, lowerCaseRule,
                upperCaseRule, digitRule);
        return password;
    }

    public List<Employee> searchEmployeeByEmail(String value) {
        Iterable<Employee> employees = employeeRepo.findAll();
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getEmail().toLowerCase().contains(value.toLowerCase())) {
                result.add(employee);
            }
        }
        return result;
    }
}
