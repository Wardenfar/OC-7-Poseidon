package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.error.EntityNotValidException;
import com.nnk.springboot.model.Field;
import com.nnk.springboot.repositories.CurvePointRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class CurvePointService implements IService<CurvePoint> {

    @Autowired
    CurvePointRepository repository;

    @Override
    public List<CurvePoint> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<CurvePoint> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public boolean validate(CurvePoint e) {
        return true;
    }

    @Override
    public boolean isExists(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public void delete(CurvePoint e) {
        repository.delete(e);
    }

    @Override
    public void save(CurvePoint e) throws EntityNotValidException {
        boolean valid = validate(e);
        if (!valid) {
            throw new EntityNotValidException("CurvePoint not valid");
        }
        repository.save(e);
    }

    @Override
    public List<Field> listFields() {
        return List.of(
                new Field("curveId", "ID", "int"),
                new Field("asOfDate", "As of Date", "date"),
                new Field("term", "Term", "double"),
                new Field("value", "Value", "double"),
                new Field("creationDate", "Date of Creation", "date")
        );
    }
}
