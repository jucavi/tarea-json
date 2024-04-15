package com.example.modeljson.service.interfaces;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface IRdbms2JsonService {

    void storeConfigJson(@NonNull MultipartFile file);

    JsonNode buildConfigJson();
}
