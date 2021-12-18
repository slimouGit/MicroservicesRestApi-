package net.slimou.microservicesrestapi.Employee;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(double salary){
        Employee.Role role = getRole(salary);
        Employee e = new Employee(role, salary);
        return this.employeeRepository.save(e);
    }

    public Employee createEmployee(Employee.Role role, double salary){
        Employee e = new Employee(role, salary);
        return this.employeeRepository.save(e);
    }

    public Employee.Role getRoleBySalary(Double salary) {
        return getRole(salary);
    }

    private Employee.Role getRole(double salary) {
        return (salary >60000)?(Employee.Role.MANAGER):(salary >40000&& salary <=60000)?(Employee.Role.CONSULTANT):(Employee.Role.TRAINEE);
    }
}
