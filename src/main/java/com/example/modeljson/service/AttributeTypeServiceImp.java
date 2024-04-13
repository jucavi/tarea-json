package com.example.modeljson.service;


import com.example.modeljson.dto.attributetype.AttributeTypeDto;
import com.example.modeljson.error.notfound.AttributeTypeNotFoundException;
import com.example.modeljson.model.AttributeType;
import com.example.modeljson.repository.IAttributeTypeRepository;
import com.example.modeljson.service.interfaces.IAttributeTypeService;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Log4j2
public class AttributeTypeServiceImp implements IAttributeTypeService {

    private final IAttributeTypeRepository repository;
    private final ModelMapper modelMapper;


    /**
     * Retrieve all attribute types from database
     *
     * @return a list of attributes types
     */
    @Override
    public List<AttributeTypeDto> findAll() {

        log.info("Retrieving all players");
        return repository.findAll()
                .stream()
                .filter(attr -> !attr.getDeleted())
                .map(attr -> modelMapper.map(attr, AttributeTypeDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Retrieve all attribute types from database even if soft deleted
     *
     * @return a list of attributes types even if marked as deleted
     */
    @Override
    public List<AttributeTypeDto> findDeepAll() {

        log.info("Retrieving all players(deep)");
        return repository.findAll()
                .stream()
                .map(attr -> modelMapper.map(attr, AttributeTypeDto.class))
                .collect(Collectors.toList());
    }


    /**
     * Retrieve attribute type by identifier
     *
     * @param id attribute type identifier
     * @return an attribute type
     * @throws AttributeTypeNotFoundException if attribute type not found in database
     */
    @Override
    public AttributeTypeDto findById(@NonNull Long id) throws AttributeTypeNotFoundException {

        log.info("Retrieving attribute type with id: {}", id);

        var attributeType = repository.findById(id);

        if (attributeType.isEmpty() || attributeType.get().getDeleted()) {
            throw new AttributeTypeNotFoundException("Attribute type not found.");
        }

        return modelMapper.map(attributeType.get(), AttributeTypeDto.class);
    }


    /**
     * Retrieve attribute type by identifier deep
     *
     * @param id attribute type identifier
     * @return an attribute type
     * @throws AttributeTypeNotFoundException if attribute type not found in database
     */
    @Override
    public AttributeTypeDto findDeepById(Long id) {

        log.info("Retrieving attribute type(deep) with id: {}", id);

        var attributeType = repository.findById(id);

        if (attributeType.isEmpty()) {
            throw new AttributeTypeNotFoundException("Attribute type not found.");
        }

        return modelMapper.map(attributeType.get(), AttributeTypeDto.class);
    }


    /**
     * Create new attribute type
     *
     * @param attributeType attribute type
     * @return attribute type
     * @throws RuntimeException if trying to create attribute type with not null id
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
            throw new RuntimeException("Unable to create attribute type.");
        }

        return modelMapper.map(result, AttributeTypeDto.class);
    }


    /**
     * Update an attribute type for the given id
     *
     * <p>
     * Only enum description, and deleted properties can be updated.
     * </p>
     *
     * @param attributeType attribute type
     * @return attribute type
     * @throws RuntimeException if trying to create attribute type with null id
     */
    @Override
    public AttributeTypeDto update(AttributeType attributeType) throws  RuntimeException {

        log.info("Updating attribute type");

        if (attributeType.getId() == null) {
            throw new RuntimeException("Trying to update attribute type, but ID not null.");
        }

        var oldEntityOp = repository.findById(attributeType.getId());

        if (oldEntityOp.isEmpty()) {
            throw new AttributeTypeNotFoundException("Attribute type entity not found");
        }

        System.out.println(oldEntityOp.get()); // TODO REMOVE
        System.out.println(attributeType); // TODO REMOVE

        var oldEntity = oldEntityOp.get();
        oldEntity.setEnumDescription(attributeType.getEnumDescription());

        AttributeType result;
        try {
            result = repository.save(oldEntity);
            log.info("Attribute type '{}' updated.", result);

        } catch (Exception ex) {
            throw new RuntimeException("Unable to update attribute type.");
        }

        return modelMapper.map(result, AttributeTypeDto.class);
    }


    /**
     * Remove an attribute type from database with the given id (soft deleted)
     *
     * @param id attribute type identifier
     */
    @Override
    public void deleteById(Long id) {
        log.info("Soft deleting attribute type with ID: {}", id);

        var entityOp = repository.findById(id);

        if (entityOp.isEmpty()) {
            throw new AttributeTypeNotFoundException("Attribute type entity not found");
        }

        var entity = entityOp.get();
        entity.setDeleted(true);

        repository.save(entity);
    }
}
