package com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private int age;

    @Column
    private double salary;

    public Employee(){}

    public Employee(int id, String name, int age, double salary) 
    {
        this.id=id;
        this.name=name;
        this.age=age;
        this.salary=salary;
    }

    public int getId() 
    { 
    	return id; 
    }
    
    public void setId(int id) 
    { 
    	this.id=id; 
    }

    public String getName() 
    { 
    	return name; 
    }
    
    public void setName(String name) 
    { 
    	this.name=name; 
    }

    public int getAge() 
    { 
    	return age; 
    }
    
    public void setAge(int age) 
    { 
    	this.age=age; 
    }

    public double getSalary() 
    { 
    	return salary; 
    }
    
    public void setSalary(double salary) 
    { 
    	this.salary=salary; 
    }

    @Override
    public String toString() 
    {
        return "id=" + id + ", name=" + name + ", age=" + age + ", salary=" + salary;
    }
}