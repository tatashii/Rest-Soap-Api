import org.api.rest.dto.EmployeeDto;
import org.api.rest.entity.*;
import org.api.rest.entityManager.Database;
//import org.api.rest.entityManager.EntityManagerFactorySingleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

//import static org.api.rest.services.EmployeeServices.getAllEmployees;


public class Main {

    public static void main(String[] args) {

//        EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getEntityManagerFactory();
//        EntityManager em = entityManagerFactory.createEntityManager();

        try {


            // Instantiate entities
            Department department = new Department();
            department.setName("Human Resources");

            Work work = new Work();
            work.setName("Project X");
            work.setDescription("Description of Project X");
            // For example, January 1, 1990, 12:00:00 AM can be represented as new Date(90, 0, 1)
            Date dobValue = new Date(90, 0, 1); // Represents January 1, 1990
            work.setStartTime(dobValue);
            work.setDeadLine(dobValue);
            work.setStatus("In Progress");
            work.setType("Software Development");

            Employee employee = new Employee();
            employee.setName("John Doe");
            employee.setUserName("john.doe");
            employee.setPassword("password");
            employee.setEmail("john.doe@example.com");


            Role role = new Role();
            role.setName(Role.RoleName.DEVELOPER);
            employee.setRole(role);


            employee.setGender("Male");
            employee.setSalary(new java.math.BigDecimal(5000));
            employee.setDob(dobValue);
            employee.setJoining(dobValue);
            employee.setDepartment(department); // Assigning department to employee

            Address address = new Address();
            address.setHouseNo("123");
            address.setStreet("Main Street");
            address.setCity("City");
            address.setPincode("12345");
            address.setState("State");
            address.setEmployee(employee); // Assigning employee to address

            Leavee leave = new Leavee();
            leave.setReason("Vacation");
            leave.setStatus("Approved");
            leave.setLeaveStartDate(dobValue);
            leave.setLeaveEndDate(dobValue);
            leave.setEmployee(employee); // Assigning employee to leave

            // Persist entities

            //em.getTransaction().begin();

            Database.doInTransaction(em->{
                em.persist(department);
                em.persist(work);
                em.persist(employee);
                em.persist(address);
                em.persist(leave);
                return "";
            });

            //em.getTransaction().commit();
            System.out.println("Entities persisted successfully.");

            //-----------------------------------------------------------------------------
//            System.out.println("Hello Hajar ......");
//            for (EmployeeDto emp : getAllEmployees()){ // getAllEmployees ---> change it to be static
//                System.out.println("employee = "+emp);
//            }
            //-----------------------------------------------------------------------------


        } catch (Exception e) {

//            em.getTransaction().rollback();
            Database.doInTransactionWithoutResult(entityManager -> {
                entityManager.getTransaction().rollback();
            });
            e.printStackTrace();
        } finally {
//            em.close();
//            entityManagerFactory.close();
            Database.close();
        }
    }
}
