package com.example.modeljson.service;

import com.example.modeljson.dto.ConfigDto;
import com.example.modeljson.error.notfound.AttributeTypeNotFoundException;
import com.example.modeljson.error.notfound.ConfigNotFoundException;
import com.example.modeljson.model.Config;
import com.example.modeljson.repository.IConfigRepository;
import com.example.modeljson.service.interfaces.IConfigService;
import com.example.modeljson.dto.assemblers.ConfigAssembler;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ConfigServiceImp implements IConfigService {

    private final IConfigRepository repository;

    /**
     * Retrieve all config nodes from database
     *
     * @return a list of all config nodes
     */
    @Override
    public List<ConfigDto> findAll() {
        
        log.info("Retrieving all config nodes");
        return repository.findAll()
                .stream()
                .filter(conf -> !conf.getDeleted())
                .map(ConfigAssembler::mapToDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve all config nodes from database even if deleted
     *
     * @return a list all config nodes even if marked as deleted
     */
    @Override
    public List<ConfigDto> findDeepAll() {
        
        log.info("Retrieving all config nodes(deep)");
        return repository.findAll()
                .stream()
                .map(ConfigAssembler::mapToDto)
                .collect(Collectors.toList());
    }


    /**
     * Retrieve config node by identifier
     *
     * @param id config node identifier
     * @return an config node
     * @throws AttributeTypeNotFoundException if config node not found in database
     */
    @Override
    public ConfigDto findById(@NonNull Long id) throws ConfigNotFoundException {
        
        log.info("Retrieving config node with id: {}", id);

        var config = repository.findById(id);

        if (config.isEmpty() || config.get().getDeleted()) {
            throw new ConfigNotFoundException("Config node not found.");
        }

        return ConfigAssembler.mapToDto(config.get());
    }

    /**
     * Retrieve config node by identifier deep
     *
     * @param id config node identifier
     * @return a config node
     * @throws AttributeTypeNotFoundException if config node not found in database
     */
    @Override
    public ConfigDto findDeepById(Long id) throws ConfigNotFoundException {
        
        log.info("Retrieving config node(deep) with id: {}", id);

        var config = repository.findById(id);

        if (config.isEmpty()) {
            throw new ConfigNotFoundException("Config node not found.");
        }

        return ConfigAssembler.mapToDto(config.get());
    }

    /**
     * Create new config node
     *
     * @param config config node
     * @return config node
     * @throws RuntimeException if trying to create config node with not null id
     */
    @Override
    public ConfigDto create(Config config) {
        log.info("Creating config node");

        if (config.getId() != null) {
            throw new RuntimeException("Trying to create an config node, but ID not null.");
        }

        Config result;
        try {
            result = repository.save(config);
            log.info("Config node '{}' created with ID: {}", result, result.getId());

        } catch (Exception ex) {
            throw new RuntimeException("Unable to create config node.");
        }

        return ConfigAssembler.mapToDto(result);
    }

    @Override
    public ConfigDto update(Config config) {
        throw new NotImplementedException("Update not supported");
    }


    /**
     * Remove a config node from database with the given id (soft deleted)
     *
     * @param id config node identifier
     */
    @Override
    public void deleteById(Long id) {
        throw new NotImplementedException("Sorry, not implemented yet");
    }
}
