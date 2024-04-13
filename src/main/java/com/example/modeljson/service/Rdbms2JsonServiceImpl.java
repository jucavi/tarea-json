package com.example.modeljson.service;

import com.example.modeljson.repository.IAttributeRepository;
import com.example.modeljson.repository.IAttributeTypeRepository;
import com.example.modeljson.repository.IAttributeTypeValueRepository;
import com.example.modeljson.repository.IConfigRepository;
import com.example.modeljson.service.interfaces.IRdbms2JsonService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class Rdbms2JsonServiceImpl implements IRdbms2JsonService {

    private final IConfigRepository configRepository;
    private final IAttributeRepository attributeRepository;
    private final IAttributeTypeRepository attributeTypeRepository;
    private final IAttributeTypeValueRepository attributeTypeValueRepository;

    @Override
    public void storeConfigJson(@NonNull MultipartFile file) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStreamReader reader = new InputStreamReader(file.getInputStream());

            JsonNode rootNode = mapper.readTree(reader);

            var fields = rootNode.fields();

            while (fields.hasNext()) {
                var field = fields.next();
                System.out.println(fields.next());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonNode buildConfigJson() {
        return null;
    }
}
