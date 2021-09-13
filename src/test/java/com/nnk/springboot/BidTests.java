package com.nnk.springboot;

import com.nnk.springboot.domain.Bid;
import com.nnk.springboot.repositories.BidRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BidTests {

    @Autowired
    private BidRepository bidRepository;

    @Test
    public void bidTest() {
        Bid bid = new Bid("Account Test", "Type Test", 10d);

        // Save
        bid = bidRepository.save(bid);
        assertNotNull(bid.getId());
        assertEquals(bid.getBidQuantity(), 10d, 10d);

        // Update
        bid.setBidQuantity(20d);
        bid = bidRepository.save(bid);
        assertEquals(bid.getBidQuantity(), 20d, 20d);

        // Find
        List<Bid> listResult = bidRepository.findAll();
        assertTrue(listResult.size() > 0);

        // Delete
        Integer id = bid.getId();
        bidRepository.delete(bid);
        Optional<Bid> bidOpt = bidRepository.findById(id);
        assertFalse(bidOpt.isPresent());
    }
}
