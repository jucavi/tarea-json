package com.example.modeljson.service;


import com.example.modeljson.dto.AttributeTypeDto;
import com.example.modeljson.error.AttributeTypeNotFoundException;
import com.example.modeljson.model.AttributeType;
import com.example.modeljson.repository.AttributeTypeRepository;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Log4j2
public class AttributeTypeServiceImp implements AttributeTypeServiceInterface {

    private final AttributeTypeRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public List<AttributeTypeDto> findAll() {
        log.info("Retrieving all players from database");
        return repository.findAll()
                .stream()
                .map(attr -> modelMapper.map(attr, AttributeTypeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AttributeTypeDto findById(Long id) {
        log.info("Retrieving attribute type with id: {}", id);

        Optional<AttributeType> attributeType = repository.findById(id);

        if (attributeType.isEmpty()) {
            throw new AttributeTypeNotFoundException("Attribute type not found.");
        }

        return modelMapper.map(attributeType.get(), AttributeTypeDto.class);
    }

    @Override
    public AttributeTypeDto create(AttributeType attributeType) {
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

    @Override
    public AttributeTypeDto update(AttributeType attributeType) {
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

    @Override
    public void deleteById(Long id) {
        log.info("Deleting attribute type with ID: {}", id);
        repository.deleteById(id);
    }
}
