package com.model;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Launch 
{

	// INSERT into table
    public static void insertEmployee(SessionFactory sessionFactory) 
    {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Employee emp = new Employee(1,"Aditya",22,68000);
        session.persist(emp);

        transaction.commit();
        session.close();
        System.out.println("Employee Inserted");
    }

    // FETCH from table
    public static void fetchEmployee(SessionFactory sessionFactory,int id) 
    {
        Session session = sessionFactory.openSession();

        Employee emp = session.get(Employee.class,id);
        System.out.println("Fetched Employee: "+emp);
        session.close();
    }

    // UPDATE in table
    public static void updateSalary(SessionFactory sessionFactory,int id,double newSalary) 
    {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Employee emp=session.get(Employee.class,id);

        if(emp!=null) 
        {
            emp.setSalary(newSalary);
            session.merge(emp);
            System.out.println("Salary Updated");
        } 
        else
            System.out.println("Employee Not Found");
        transaction.commit();
        session.close();
    }

    // DELETE from table
    public static void deleteEmployee(SessionFactory sessionFactory,int id) 
    {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Employee emp=session.get(Employee.class, id);

        if(emp!=null) 
        {
            session.remove(emp);
            System.out.println("Employee Deleted");
        } 
        else
            System.out.println("Employee Not Found");

        transaction.commit();
        session.close();
    }
	
    public static void main(String[] args) 
    {
        Configuration config=new Configuration();
        config.configure();
        config.addAnnotatedClass(Employee.class);
        SessionFactory sessionFactory=config.buildSessionFactory();

        //INSERT
        insertEmployee(sessionFactory);

        //FETCH
        fetchEmployee(sessionFactory,1);

        //UPDATE
        updateSalary(sessionFactory,1,80000);

        //DELETE
        deleteEmployee(sessionFactory,1);
        sessionFactory.close();
    }
}