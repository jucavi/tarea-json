package com.example.modeljson.error;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class AppError {

    @NonNull
    private HttpStatus status;
    @NonNull
    private LocalDateTime timestamp = LocalDateTime.now();
    @NonNull
    private Map<String, String> errors;
}
