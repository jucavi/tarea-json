package com.example.modeljson.controller;


import com.example.modeljson.dto.assemblers.AttributeTypeAssembler;
import com.example.modeljson.dto.attributetype.AttributeTypeDto;
import com.example.modeljson.dto.attributetype.AttributeTypePostDto;
import com.example.modeljson.dto.attributetype.AttributeTypeUpdateDto;
import com.example.modeljson.service.interfaces.IAttributeTypeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attrs-types")
public class AttributeTypeController {

    private final IAttributeTypeService service;


    /**
     * Get all attributes names from database
     */
    @GetMapping
    @Operation(summary = "All attributes types",  description = "Retrieve all attributes types from database")
    public ResponseEntity<List<AttributeTypeDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }


    /**
     * Get all attributes names from database even if soft deleted
     */
    @GetMapping("deep")
    @Operation(summary = "All attributes types even if soft deleted",  description = "Retrieve all attributes types from database even if deleted")
    public ResponseEntity<List<AttributeTypeDto>> findDeepAll() {
        return ResponseEntity.ok(service.findDeepAll());
    }

    /**
     * Find an attribute name from database
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get attribute type by ID",  description = "Retrieve an attribute type from database")
    public ResponseEntity<AttributeTypeDto> findDeepById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findDeepById(id));
    }

    /**
     * Find an attribute name from database
     */
    @GetMapping("deep/{id}")
    @Operation(summary = "Get attribute type by ID even if soft deleted",  description = "Retrieve an attribute type from database even is deleted")
    public ResponseEntity<AttributeTypeDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }


    /**
     * Create an attribute name in the database
     */
    @PostMapping
    @Operation(summary = "Create an attribute type", description = "Create an attribute type")
    public ResponseEntity<AttributeTypeDto> create(@RequestBody AttributeTypePostDto attributeTypePostDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(
                        AttributeTypeAssembler.mapFromDto(attributeTypePostDto)));
    }


    /**
     * Update an attribute name info
     */
    @PutMapping
    @Operation(summary = "Update an attribute type", description = "Update an attribute type")
    public ResponseEntity<AttributeTypeDto> update(@RequestBody AttributeTypeUpdateDto attributeTypeUpdateDto) {

        var entity = AttributeTypeAssembler.mapFromDto(attributeTypeUpdateDto);
        System.out.println("attributeTypeUpdateDto = " + attributeTypeUpdateDto);
        System.out.println("entity = " + entity);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.update(
                        AttributeTypeAssembler.mapFromDto(attributeTypeUpdateDto))) ;
    }


    /**
     * Delete an attribute name from a database
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an attribute type", description = "Delete(soft) an attribute type")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
