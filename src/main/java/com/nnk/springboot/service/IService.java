package com.nnk.springboot.service;

import com.nnk.springboot.error.EntityNotValidException;
import com.nnk.springboot.model.Field;

import java.util.List;
import java.util.Optional;

public interface IService<T> {
    List<T> findAll();

    Optional<T> findById(Integer id);

    boolean validate(T e);

    boolean isExists(Integer id);

    void delete(T e);

    void save(T e) throws EntityNotValidException;

    List<Field> listFields(T e);
}
