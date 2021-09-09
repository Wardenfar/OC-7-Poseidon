package com.nnk.springboot.service;

import com.nnk.springboot.domain.Bid;
import com.nnk.springboot.error.EntityNotValidException;
import com.nnk.springboot.model.Field;
import com.nnk.springboot.repositories.BidRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@NoArgsConstructor
public class BidService {

    @Autowired
    BidRepository repository;

    public List<Bid> findAll() {
        return repository.findAll();
    }

    public Optional<Bid> findById(Integer id) {
        return repository.findById(id);
    }

    public boolean validate(Bid e) {
        return true;
    }

    public boolean isExists(Integer id) {
        return repository.existsById(id);
    }

    public void delete(Bid e) {
        repository.delete(e);
    }

    public void save(Bid e) throws EntityNotValidException {
        boolean valid = validate(e);
        if (!valid) {
            throw new EntityNotValidException("Bid not valid");
        }
        repository.save(e);
    }

    public List<Field> listFields() {
        return List.of(
                new Field("bidId", "ID", "int"),
                new Field("account", "Account", "text"),
                new Field("type", "Type", "text"),
                new Field("bidQuantity", "Bid Quantity", "double"),
                new Field("askQuantity", "Ask Quantity", "double"),
                new Field("bid", "Bid", "double"),
                new Field("ask", "Ask", "double"),
                new Field("benchmark", "Benchmark", "double"),
                new Field("bidDate", "Date", "date")
        );
    }
}
