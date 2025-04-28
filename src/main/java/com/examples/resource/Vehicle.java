package com.examples.resource;

import com.examples.util.JsonConverter;
import com.examples.util.JsonMapConverter;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Option 1: Define your entity class to use a JSON column.
    // For databases like MySQL, PostgreSQL, or others that support JSON column types,
    // you can store additional fields dynamically in a JSON column.
    // For example, you can use the @Column annotation with columnDefinition = "json".
    // This allows you to store JSON data directly in the database.
    @Column(columnDefinition = "json")
    private String dynamicFieldsJson; // Store additional fields in JSON format

    // Option 2: Use a Map<String, Object> to store dynamic fields.
    // For databases that do not support JSON column types, you can use a String column
    // and convert the JSON data to a String before saving it.
    @Convert(converter = JsonMapConverter.class)
    private Map<String, Object> dynamicFieldsMap; // Store additional fields in JSON format

    // Option 3: Use a JsonNode to store dynamic fields.
    // You can also use a JsonNode to store dynamic fields.
    @Convert(converter = JsonConverter.class)
    private JsonNode dynamicFieldsJsonNode; // Store additional fields in JSON format
}
