package com.nnk.springboot.service;

import com.nnk.springboot.domain.Bid;
import com.nnk.springboot.error.EntityNotValidException;
import com.nnk.springboot.model.Field;
import com.nnk.springboot.repositories.BidRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class BidService implements IService<Bid> {

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

    public List<Field> listFields(Bid e) {
        return List.of(
                new Field("account", "Account", "text", e.getAccount()),
                new Field("type", "Type", "text", e.getType()),
                new Field("bidQuantity", "Bid Quantity", "double", e.getBidQuantity()),
                new Field("askQuantity", "Ask Quantity", "double", e.getAskQuantity()),
                new Field("bidValue", "Bid", "double", e.getBidValue()),
                new Field("askValue", "Ask", "double", e.getAskValue()),
                new Field("benchmark", "Benchmark", "double", e.getBenchmark()),
                new Field("bidDate", "Date", "date", e.getBidDate()),
                new Field("commentary", "Commentary", "text", e.getCommentary()),
                new Field("security", "Security", "text", e.getSecurity()),
                new Field("status", "Status", "text", e.getStatus()),
                new Field("trader", "Trader", "text", e.getTrader()),
                new Field("book", "Book", "text", e.getBook()),
                new Field("creationName", "Name of Creation", "text", e.getCreationName()),
                new Field("creationDate", "Date of Creation", "date", e.getCreationDate()),
                new Field("revisionName", "Name of Revision", "text", e.getRevisionName()),
                new Field("revisionDate", "Date of Revision", "date", e.getRevisionDate()),
                new Field("dealName", "Name of Deal", "text", e.getDealName()),
                new Field("dealType", "Type of Deal", "text", e.getDealType()),
                new Field("sourceListId", "List of Sources", "text", e.getSourceListId()),
                new Field("side", "Side", "text", e.getSide())
        );
    }
}
