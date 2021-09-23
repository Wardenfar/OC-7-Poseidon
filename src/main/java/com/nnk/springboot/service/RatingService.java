package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.error.EntityNotValidException;
import com.nnk.springboot.model.Field;
import com.nnk.springboot.repositories.RatingRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class RatingService implements IService<Rating> {

    @Autowired
    RatingRepository repository;

    public List<Rating> findAll() {
        return repository.findAll();
    }

    public Optional<Rating> findById(Integer id) {
        return repository.findById(id);
    }

    public boolean validate(Rating e) {
        return true;
    }

    public boolean isExists(Integer id) {
        return repository.existsById(id);
    }

    public void delete(Rating e) {
        repository.delete(e);
    }

    public void save(Rating e) throws EntityNotValidException {
        boolean valid = validate(e);
        if (!valid) {
            throw new EntityNotValidException("Rating not valid");
        }
        repository.save(e);
    }

    public List<Field> listFields(Rating e) {
        return List.of(
                new Field("moodysRating", "Moodys Rating", "text", e.getMoodysRating()),
                new Field("orderNumber", "Order Number", "int", e.getOrderNumber()),
                new Field("sandPRating", "SandPR Rating", "text", e.getSandPRating()),
                new Field("fitchRating", "Fitch Rating", "text", e.getFitchRating())
        );
    }
}
