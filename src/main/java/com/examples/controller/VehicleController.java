package com.examples.controller;

import com.examples.resource.Vehicle;
import com.examples.service.VehicleService;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @PostMapping("/dynamic/string")
    public ResponseEntity<String> createDynamicFieldsString(@RequestBody String jsonString) {
        // Convert the payload to a Vehicle object
        Vehicle vehicle = new Vehicle();
        JsonElement jsonElement = JsonParser.parseString(jsonString);
        if(jsonElement.isJsonObject()) {
            vehicle.setDynamicFieldsJson(jsonElement.toString());
        }
        if(jsonElement.isJsonArray()) {
            return ResponseEntity.badRequest().body("JSON Array is not supported");
        }
        if(jsonElement.isJsonPrimitive()) {
            return ResponseEntity.badRequest().body("JSON Primitive is not supported");
        }
        if(jsonElement.isJsonNull()) {
            return ResponseEntity.badRequest().body("JSON Null is not supported");
        }

        // Save the vehicle to the database
        vehicleService.createVehicle(vehicle);
        return ResponseEntity.ok("Created record: " + jsonString);
    }

    @PostMapping("/dynamic/map")
    public ResponseEntity<String> createDynamicFieldsMap(@RequestBody Map<String, Object> payload) {
        // Convert the payload to a Vehicle object
        Vehicle vehicle = new Vehicle();
        vehicle.setDynamicFieldsMap(payload);

        // Save the vehicle to the database
        vehicleService.createVehicle(vehicle);
        return ResponseEntity.ok("Created record:" + payload);
    }

    // Create a vehicle with dynamic fields http://localhost:8080/api/vehicle/dynamic
    // Example payload:
    // {
    //    "make" : "Honda",
    //    "model" : "CR-V",
    //    "price" : "30,0000"
    //}
    @PostMapping("/dynamic")
    public ResponseEntity<String> createDynamicFields(@RequestBody JsonNode payload) {
        // Convert the payload to a Vehicle object
        Vehicle vehicle = new Vehicle();
        vehicle.setDynamicFieldsJsonNode(payload);

        // Save the vehicle to the database
        vehicleService.createVehicle(vehicle);
        return ResponseEntity.ok("Created record: " + payload);
    }

    // Create a vehicle with dynamic fields http://localhost:8080/api/vehicle/dynamic
    // Example payload:
    //    {
    //        "id": "1",
    //            "vehicle" : {
    //                "make" : "Honda",
    //                "model" : "CR-V",
    //                "price" : "30,0000",
    //                "year" : "2017"
    //          }
    //    }
    @PutMapping("/dynamic")
    public ResponseEntity<String> putDynamicFields(@RequestBody JsonNode payload) {
        // Convert the payload to a Vehicle object
        Vehicle vehicle = new Vehicle();
        vehicle.setId(Long.valueOf(payload.get("id").asText()));
        vehicle.setDynamicFieldsJsonNode(payload.get("vehicle"));

        // Save the vehicle to the database
        vehicleService.updateVehicle(vehicle);

        return ResponseEntity.ok("Updated record: " + payload);
    }

    // Fetch a vehicle by ID http://localhost:8080/api/vehicle/dynamic?id=1
    @GetMapping("/dynamic")
    public ResponseEntity<Vehicle> getDynamicField(@RequestParam String id) {
        // Fetch the vehicle with the given ID
        Optional<Vehicle> vehicle = vehicleService.getVehicle(Long.valueOf(id));
        return ResponseEntity.ok(vehicle.orElse(null));
    }

    // Fetch all vehicles http://localhost:8080/api/vehicle/dynamic/all
    @GetMapping("/dynamic/all")
    public ResponseEntity<List<Vehicle>> getDynamicFields() {
        // Fetch all vehicles
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }
}
