package com.example.modeljson.service.interfaces;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

public interface IRdbms2JsonService {

    void storeJson(@NonNull MultipartFile file);

    JsonNode buildJson();
}
