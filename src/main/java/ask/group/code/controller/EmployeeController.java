package ask.group.code.controller;

import ask.group.code.model.Employee;
import ask.group.code.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository empRepository;

    @RequestMapping("/employees")
    public List<Employee> getEmployees() {
        List<Employee> eList = new ArrayList<>();
        Iterable<Employee> employees = this.empRepository.findAll();
        if(employees != null) {
            employees.forEach(e -> {
                if (Employee.Active.equals(e.getStatus())) {
                    eList.add(e);
                }
            });
        }
        return eList;
    }

    @RequestMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Optional<Employee> emp = null;
        try {
            emp = this.empRepository.findById(employeeId);
        } catch (Exception e) {
            //ignored
        }
        emp.orElseThrow(() -> new EmployeeDidNotExist("Employee with id " + employeeId + " does not exist"));
        emp.ifPresent(e1 -> {
            if(! (Employee.Active.equals(e1.getStatus()))) {
                throw new EmployeeNotFoundException("Employee with id " + employeeId + " is inactive");
            }
        });
        return emp.get();
    }

    @RequestMapping(method=RequestMethod.POST, value="/employees")
    public String createEmployee(@RequestBody Employee emp) {
        emp.setStatus(Employee.Active);
        this.empRepository.save(emp);
        return "Employee saved!";
    }

    @RequestMapping(method=RequestMethod.PUT, value="/employees/{id}")
    public String updateEmployee(@RequestBody Employee emp, @PathVariable int id) {
        String retStr = null;
        try {
            emp.setStatus(Employee.Active);
            emp.setId(id);
            this.empRepository.save(emp);
            retStr = "Employe named " + emp.getFirstName() + " " + emp.getLastName() + " is updated";
        } catch (Exception e) {
            retStr = "Failed to update employee";
        }
        return retStr;
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Optional<Employee> emp = this.empRepository.findById(employeeId);
        emp.orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + employeeId + " did not exist"));
        String retStr = null;
        Employee em = null;
        try {
            em = emp.get();
            em.setStatus(Employee.InActive);
            this.empRepository.save(em);
            retStr = "Employee named " + em.getFirstName() + " " + em.getLastName() + " is deleted";
        } catch (Exception e) {
            throw e;
        }
        return retStr;
    }
}
