//package org.api;
//
//import org.api.rest.dto.EmployeeDto;
//import org.api.rest.entity.Department;
//import org.api.rest.entity.Employee;
//import org.api.rest.entity.Role;
//import org.api.rest.entityManager.Database;
//import org.api.rest.services.EmployeeServices;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
//public class EmployeeServicesTest {
//
//    @Before
//    public void setUp() {
//
//
//        // Populate the database with test data
//        Database.doInTransactionWithoutResult(em -> {
//            // Add test employees to the database
//            Employee employee1 = new Employee();
//            employee1.setName("John Doe");
//            employee1.setUserName("johndoe");
//            employee1.setPassword("password123");
//            employee1.setEmail("johndoe@example.com");
//            //Need to add Role Table [Manager , Developer , Employee]
//
//            Role role = new Role();
//            role.setName(Role.RoleName.DEVELOPER);
//            employee1.setRole(role);
//
//
//            employee1.setGender("Male");
//            employee1.setSalary(new BigDecimal("50000"));
//            Date dobValue = new Date(90, 0, 1);
//            employee1.setDob(dobValue);
//            employee1.setJoining(dobValue);
//            Department department = new Department();
//            department.setName("Human Resources");
//            employee1.setDepartment(department);
//
//            em.persist(employee1);
//
//        });
//    }
//
//    @After
//    public void tearDown() {
//        Database.close();
//    }
//
//    @Test
//    public void testGetAllEmployees() {
//        // Arrange
//        EmployeeServices employeeServices = new EmployeeServices();
//
//        // Act
//        List<EmployeeDto> result = employeeServices.getAllEmployees();
//
//        // Assert
//        assertEquals(1, result.size());
//
//    }
//}
