package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.error.EntityNotValidException;
import com.nnk.springboot.model.Field;
import com.nnk.springboot.repositories.TradeRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class TradeService implements IService<Trade> {

    @Autowired
    TradeRepository repository;

    public List<Trade> findAll() {
        return repository.findAll();
    }

    public Optional<Trade> findById(Integer id) {
        return repository.findById(id);
    }

    public boolean validate(Trade e) {
        return true;
    }

    public boolean isExists(Integer id) {
        return repository.existsById(id);
    }

    public void delete(Trade e) {
        repository.delete(e);
    }

    public void save(Trade e) throws EntityNotValidException {
        boolean valid = validate(e);
        if (!valid) {
            throw new EntityNotValidException("Trade not valid");
        }
        repository.save(e);
    }

    public List<Field> listFields(Trade e) {
        return List.of(
                new Field("account", "Account", "text", e.getAccount()),
                new Field("type", "Type", "text", e.getType()),
                new Field("buyQuantity", "Buy Quantity", "double", e.getBuyQuantity()),
                new Field("sellQuantity", "Sell Quantity", "double", e.getSellQuantity()),
                new Field("buyPrice", "Buy Price", "double", e.getBuyPrice()),
                new Field("sellPrice", "Sell Price", "double", e.getSellPrice()),
                new Field("benchmark", "Benchmark", "text", e.getBenchmark()),
                new Field("tradeDate", "Trade Date", "date", e.getTradeDate()),
                new Field("security", "Security", "text", e.getSecurity()),
                new Field("status", "Status", "text", e.getStatus()),
                new Field("trader", "Trader", "text", e.getTrader()),
                new Field("book", "Book", "text", e.getBook()),
                new Field("creationName", "Creation Name", "text", e.getCreationName()),
                new Field("creationDate", "Creation Date", "date", e.getCreationDate()),
                new Field("revisionName", "Revision Name", "text", e.getRevisionName()),
                new Field("revisionDate", "Revision Date", "date", e.getRevisionDate()),
                new Field("dealName", "Deal Name", "text", e.getDealName()),
                new Field("dealType", "Deal Type", "text", e.getDealType()),
                new Field("sourceListId", "Source List Id", "text", e.getSourceListId()),
                new Field("side", "Side", "text", e.getSide())
        );
    }
}
