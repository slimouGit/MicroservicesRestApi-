package net.slimou.microservices;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee {

    private @Id
    @GeneratedValue
    Long id;
    private String name;
    private double salary;
    private departure

    public Employee() {
    }


}
