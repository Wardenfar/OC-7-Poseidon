package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rule;
import com.nnk.springboot.error.EntityNotValidException;
import com.nnk.springboot.model.Field;
import com.nnk.springboot.repositories.RuleRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class RuleService implements IService<Rule> {

    @Autowired
    RuleRepository repository;

    public List<Rule> findAll() {
        return repository.findAll();
    }

    public Optional<Rule> findById(Integer id) {
        return repository.findById(id);
    }

    public boolean validate(Rule e) {
        return true;
    }

    public boolean isExists(Integer id) {
        return repository.existsById(id);
    }

    public void delete(Rule e) {
        repository.delete(e);
    }

    public void save(Rule e) throws EntityNotValidException {
        boolean valid = validate(e);
        if (!valid) {
            throw new EntityNotValidException("Rule not valid");
        }
        repository.save(e);
    }

    public List<Field> listFields(Rule e) {
        return List.of(
                new Field("name", "Name", "text", e.getName()),
                new Field("description", "Description", "text", e.getDescription()),
                new Field("json", "Json", "text", e.getJson()),
                new Field("template", "Template", "text", e.getTemplate()),
                new Field("sqlStr", "Sql", "text", e.getSqlStr()),
                new Field("sqlPart", "SqlPart", "text", e.getSqlPart())
        );
    }
}
