package com.example.modeljson.service.interfaces;


import com.example.modeljson.dto.ConfigDto;
import com.example.modeljson.model.Config;

import java.util.List;

public interface ConfigServiceInterface {

    List<ConfigDto> findAll();
    ConfigDto findById(Long id);
    ConfigDto create(Config configType);
    ConfigDto update(Config configType);
    void deleteById(Long id);
}
