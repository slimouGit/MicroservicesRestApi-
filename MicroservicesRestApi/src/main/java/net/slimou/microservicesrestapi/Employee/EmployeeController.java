package net.slimou.microservicesrestapi.Employee;

import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;

    public EmployeeController(EmployeeRepository employeeRepository, EmployeeService employeeService) {
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
    }

    private static final String template = "with this salary, you must be a , %s!";

    @GetMapping("/employee/{id}")
    Employee showOne(@PathVariable Long id) {
        return this.employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @GetMapping("/employees")
    Iterable<Employee> showAll() {
        return this.employeeRepository.findAll();
    }

    @GetMapping("/delete/{id}")
    void delete(@PathVariable Long id) {
        this.employeeRepository.deleteById(id);
    }

    @GetMapping("/new/{salary}")
    Employee create(@PathVariable Double salary) {
        return this.employeeService.createEmployee(salary);
    }

    @GetMapping("/greeting/{salary}")
    public String greeting(@PathVariable Double salary) {
        Employee.Role role = this.employeeService.getRoleBySalary(salary);
        return String.format(template, role);
    }
}
