package com.example.modeljson.controller;


import com.example.modeljson.service.interfaces.IRdbms2JsonService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
//@RequestMapping("/model")
public class Rdbms2JsonController {

    private final IRdbms2JsonService service;


    @GetMapping("/store")
    public ResponseEntity<?> storeConfigJson(@RequestParam("file") @NonNull MultipartFile file) {

        service.storeConfigJson(file);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/load")
    public ResponseEntity<?> loadConfigJson() {
        return ResponseEntity.ok(service.buildConfigJson());
    }

    @GetMapping("/benchmark")
    public ResponseEntity<Long> benchmark() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            service.buildConfigJson();
        }

        return ResponseEntity.ok(System.currentTimeMillis() - startTime);
    }
}
