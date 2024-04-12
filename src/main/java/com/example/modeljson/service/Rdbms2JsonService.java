package com.example.modeljson.service;

import com.example.modeljson.repository.AttributeRepository;
import com.example.modeljson.repository.AttributeTypeRepository;
import com.example.modeljson.repository.AttributeTypeValueRepository;
import com.example.modeljson.repository.ConfigRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class Rdbms2JsonService {

    private final ConfigRepository configRepository;
    private final AttributeRepository attributeRepository;
    private final AttributeTypeRepository attributeTypeRepository;
    private final AttributeTypeValueRepository attributeTypeValueRepository;

    public Map<String, Object> buildConfigJson() {


        return new HashMap<>();
    }
}
