package com.nnk.springboot;

import com.nnk.springboot.domain.Rule;
import com.nnk.springboot.repositories.RuleRepository;
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
public class RuleTests {

    @Autowired
    private RuleRepository ruleRepository;

    @Test
    public void ruleTest() {
        Rule rule = new Rule("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

        // Save
        rule = ruleRepository.save(rule);
        assertNotNull(rule.getId());
        assertEquals("Rule Name", rule.getName());

        // Update
        rule.setName("Rule Name Update");
        rule = ruleRepository.save(rule);
        assertEquals("Rule Name Update", rule.getName());

        // Find
        List<Rule> listResult = ruleRepository.findAll();
        assertTrue(listResult.size() > 0);

        // Delete
        Integer id = rule.getId();
        ruleRepository.delete(rule);
        Optional<Rule> ruleList = ruleRepository.findById(id);
        assertFalse(ruleList.isPresent());
    }
}
