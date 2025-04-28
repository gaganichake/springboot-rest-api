package com.examples.service;

import com.examples.repository.VehicleRepository;
import com.examples.resource.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    // Add your service methods here
    // For example, you can add methods to handle vehicle-related operations
    // such as creating, updating, deleting vehicles, etc.

    public void createVehicle(Vehicle vehicle) {
        // Logic to create a vehicle
        vehicleRepository.save(vehicle);
    }

    public void updateVehicle(Vehicle vehicle) {
        // Logic to update a vehicle
        vehicleRepository.save(vehicle);
    }

    public void deleteVehicle() {
        // Logic to delete a vehicle
    }

    public Optional<Vehicle>  getVehicle(Long id) {
        return vehicleRepository.findById(id);
    }

    public List<Vehicle> getAllVehicles() {
        return (List<Vehicle>) vehicleRepository.findAll();
    }
}
