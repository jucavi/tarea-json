package com.example.modeljson.controller;


import com.example.modeljson.dto.attributetype.AttributeTypeDto;
import com.example.modeljson.dto.attributetype.AttributeTypePostDto;
import com.example.modeljson.dto.attributetype.AttributeTypeUpdateDto;
import com.example.modeljson.model.AttributeType;
import com.example.modeljson.service.interfaces.AttributeTypeServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attrs-types")
public class AttributeTypeController {

    private final AttributeTypeServiceInterface service;
    private final ModelMapper modelMapper;


    /**
     * Get all attributes names from database
     */
    @GetMapping
    @Operation(summary = "All attributes types", tags = {"GET"}, description = "Retrieve all attributes types from database")
    public ResponseEntity<List<AttributeTypeDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }


    /**
     * Get all attributes names from database even if soft deleted
     */
    @GetMapping("deep")
    @Operation(summary = "All attributes types even if soft deleted", tags = {"GET"}, description = "Retrieve all attributes types from database even if deleted")
    public ResponseEntity<List<AttributeTypeDto>> findDeepAll() {
        return ResponseEntity.ok(service.findDeepAll());
    }

    /**
     * Find an attribute name from database
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get attribute type by ID", tags = {"GET"}, description = "Retrieve an attribute type from database")
    public ResponseEntity<AttributeTypeDto> findDeepById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findDeepById(id));
    }

    /**
     * Find an attribute name from database
     */
    @GetMapping("deep/{id}")
    @Operation(summary = "Get attribute type by ID even if soft deleted", tags = {"GET"}, description = "Retrieve an attribute type from database even is deleted")
    public ResponseEntity<AttributeTypeDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }


    /**
     * Create an attribute name in the database
     */
    @PostMapping
    @Operation(summary = "Create an attribute type", tags = {"POST"}, description = "Create an attribute type")
    public ResponseEntity<AttributeTypeDto> create(@RequestBody AttributeTypePostDto attributeTypePostDto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(
                        modelMapper.map(attributeTypePostDto, AttributeType.class)));
    }


    /**
     * Update an attribute name info
     */
    @PutMapping
    @Operation(summary = "Update an attribute type", tags = {"PUT"}, description = "Update an attribute type")
    public ResponseEntity<AttributeTypeDto> update(@RequestBody AttributeTypeUpdateDto attributeTypeUpdateDto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.update(
                        modelMapper.map(attributeTypeUpdateDto, AttributeType.class)));
    }


    /**
     * Delete an attribute name from a database
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an attribute type", tags = {"DELETE"}, description = "Delete an attribute type")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
