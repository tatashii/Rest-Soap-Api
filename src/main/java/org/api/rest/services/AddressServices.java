package org.api.rest.services;

import org.api.rest.dao.impl.AddressDAO;
import org.api.rest.dao.impl.EmployeeDAO;
import org.api.rest.dto.AddressDto;
import org.api.rest.entity.Address;
import org.api.rest.entity.Employee;
import org.api.rest.entityManager.Database;
import org.api.rest.mapper.AddressMapper;
import org.api.rest.mapper.AddressMapper;

import java.util.List;

public class AddressServices {

    private AddressDAO addressDAO;
    private AddressMapper addressMapper;
    private EmployeeDAO employeeDAO;

    public AddressServices() {

        this.addressDAO = new AddressDAO();
        this.addressMapper = AddressMapper.INSTANCE;
        this.employeeDAO = new EmployeeDAO();

    }

    public List<AddressDto> getAllAddresss() {
        try {
            return Database.doInTransaction(em -> {
                List<Address> addresses = addressDAO.getAll(em);
                return addressMapper.toListDto(addresses);
            });
        } catch (Exception e) {
            System.out.println("Failed to retrieve addresses from the database" + e);

            throw new RuntimeException("Failed to retrieve addresses", e);
        }
    }

    public boolean addAddress(AddressDto addressDto) {
        try {
            Address address = addressMapper.toEntity(addressDto);

            // Fetch the employee for the provided address
            Employee employee = Database.doInTransaction(em -> {
                return employeeDAO.findById(addressDto.getEmployeeId(), em);
            });
            if (employee == null) {
                throw new IllegalArgumentException("Employee with ID " + addressDto.getEmployeeId() + " not found");
            }

            address.setEmployee(employee);

            Database.doInTransactionWithoutResult(em -> {
                addressDAO.save(address, em);
            });

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update an existing address
    public boolean updateAddress(AddressDto addressDto) {
        try {
            Address existingAddress = Database.doInTransaction(em -> {
                return addressDAO.findById(addressDto.getId(), em);
            });

            // Check if the address exists
            if (existingAddress == null) {
                throw new IllegalArgumentException("Address with ID " + addressDto.getId() + " not found");
            }

            // Update address properties
            existingAddress.setHouseNo(addressDto.getHouseNo());
            existingAddress.setStreet(addressDto.getStreet());
            existingAddress.setCity(addressDto.getCity());
            existingAddress.setPincode(addressDto.getPincode());
            existingAddress.setState(addressDto.getState());

            // Save the updated address
            Database.doInTransactionWithoutResult(em -> {
                addressDAO.update(existingAddress, em);
            });

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete an existing address
    public boolean deleteAddress(int addressId) {
        try {
            Address addressToDelete = Database.doInTransaction(em -> {
                return addressDAO.findById(addressId, em);
            });

            // Check if the address exists
            if (addressToDelete == null) {
                throw new IllegalArgumentException("Address with ID " + addressId + " not found");
            }

            // Delete the address
            Database.doInTransactionWithoutResult(em -> {
                addressDAO.delete(addressToDelete, em);
            });

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}



