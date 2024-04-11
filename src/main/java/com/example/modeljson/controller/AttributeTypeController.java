package com.example.modeljson.controller;


import com.example.modeljson.dto.AttributeTypeDto;
import com.example.modeljson.model.AttributeType;
import com.example.modeljson.service.AttributeTypeServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
     * Find an attribute name from database
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get attribute type by ID", tags = {"GET"}, description = "Retrieve an attribute type from database")
    public ResponseEntity<AttributeTypeDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }


    /**
     * Create an attribute name in the database
     */
    @PostMapping
    public ResponseEntity<AttributeTypeDto> create(@RequestBody @Valid AttributeTypeDto attributeTypeDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(
                        modelMapper.map(attributeTypeDto, AttributeType.class)));
    }


    /**
     * Update an attribute name info
     */
    @PutMapping
    public ResponseEntity<AttributeTypeDto> update(@RequestBody @Valid AttributeTypeDto attributeTypeDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.update(
                        modelMapper.map(attributeTypeDto, AttributeType.class)));
    }


    /**
     * Delete an attribute name from a database
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
