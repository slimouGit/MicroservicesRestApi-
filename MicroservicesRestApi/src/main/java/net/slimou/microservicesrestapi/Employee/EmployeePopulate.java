package net.slimou.microservicesrestapi.Employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeePopulate {

    Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;

    public EmployeePopulate(EmployeeRepository employeeRepository, EmployeeService employeeService) {
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
    }

    @Bean
    public CommandLineRunner createPerson() {
        return args -> {
            logger.info("Employee is coming");
            Employee e1 = new Employee(Employee.Role.MANAGER, 100000.00);
            this.employeeRepository.save(e1);
            Optional<Employee> e2 = this.employeeRepository.findById(1l);
            logger.info("Employee {}", e2.get().getId());
            logger.info("Employee exist {}", this.employeeRepository.existsById(1l));
            Employee e3 = this.employeeService.createEmployee(Employee.Role.CONSULTANT, 40000.00);
            Employee e4 = this.employeeService.createEmployee(20000.00);
            Employee e5 = this.employeeService.createEmployee(43000.00);
            Iterable<Employee> employees = this.employeeRepository.findAll();
            employees.forEach(e -> logger.info("Employee {} {} {}", e.getId(), e.getRole(), e.getSalary()));
        };
    }
}
