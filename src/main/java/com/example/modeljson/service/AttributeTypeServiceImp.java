package com.example.modeljson.service;


import com.example.modeljson.dto.AttributeTypeDto;
import com.example.modeljson.error.notfound.AttributeTypeNotFoundException;
import com.example.modeljson.model.AttributeType;
import com.example.modeljson.repository.AttributeTypeRepository;
import com.example.modeljson.service.interfaces.AttributeTypeServiceInterface;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Log4j2
public class AttributeTypeServiceImp implements AttributeTypeServiceInterface {

    private final AttributeTypeRepository repository;
    private final ModelMapper modelMapper;


    /**
     * Retrieve all attribute types from database
     *
     * @return a list of attributes types dtos
     */
    @Override
    public List<AttributeTypeDto> findAll() {
        log.info("Retrieving all players from database");
        return repository.findAll()
                .stream()
                .map(attr -> modelMapper.map(attr, AttributeTypeDto.class))
                .collect(Collectors.toList());
    }


    /**
     * Retrieve attribute type by identifier
     *
     * @param id attribute type identifier
     * @return an attribute type dto
     * @throws AttributeTypeNotFoundException raise exception if attribute type not found in database
     */
    @Override
    public AttributeTypeDto findById(@NonNull Long id) throws AttributeTypeNotFoundException {
        log.info("Retrieving attribute type with id: {}", id);

        Optional<AttributeType> attributeType = repository.findById(id);

        if (attributeType.isEmpty()) {
            throw new AttributeTypeNotFoundException("Attribute type not found.");
        }

        return modelMapper.map(attributeType.get(), AttributeTypeDto.class);
    }


    /**
     * Create new attribute type
     *
     * @param attributeType attribute type entity
     * @return attribute type dto
     * @throws RuntimeException raise exception if trying to create attribute type with not null id
     */
    @Override
    public AttributeTypeDto create(@Valid AttributeType attributeType) throws  RuntimeException {
        log.info("Creating attribute type");

        if (attributeType.getId() != null) {
            throw new RuntimeException("Trying to create an attribute type, but ID not null.");
        }

        AttributeType result;
        try {
            result = repository.save(attributeType);
            log.info("Attribute type '{}' created with ID: {}", result, result.getId());

        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException("Unable to create a attribute type.");
        }
        return modelMapper.map(result, AttributeTypeDto.class);
    }

    /**
     * Update an attribute type for the given id
     * @param attributeType attribute type
     * @return attribute type dto
     * @throws RuntimeException raise exception if trying to create attribute type with null id
     */
    @Override
    public AttributeTypeDto update(@Valid AttributeType attributeType) throws  RuntimeException {
        log.info("Updating attribute type");

        if (attributeType.getId() == null) {
            throw new RuntimeException("Trying to update an attribute type, but ID not null.");
        }

        AttributeType result;
        try {
            result = repository.save(attributeType);
            log.info("Attribute type '{}' created with ID: {}", result, result.getId());

        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException("Unable to create a attribute type.");
        }
        return modelMapper.map(result, AttributeTypeDto.class);
    }

    /**
     * Remove an attribute type from database with the given id
     * @param id attribute type identifier
     */
    @Override
    public void deleteById(Long id) {
        log.info("Deleting attribute type with ID: {}", id);
        repository.deleteById(id);
    }
}
