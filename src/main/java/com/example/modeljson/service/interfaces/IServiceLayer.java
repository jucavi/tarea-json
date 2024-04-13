package com.example.modeljson.service.interfaces;


import java.util.List;

/**
 * Service interface
 * 
 * @param <S> Data Transfer Object Class 
 * @param <T> Data Access Object Class
 */
public interface IServiceLayer<S, T> {
    List<S> findAll();
    List<S> findDeepAll();
    S findById(Long id);
    S findDeepById(Long id);
    S create(T attributeType);
    S update(T attributeType);
    void deleteById(Long id);
}
