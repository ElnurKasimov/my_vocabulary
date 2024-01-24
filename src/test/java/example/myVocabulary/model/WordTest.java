package example.myVocabulary.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class WordTest {

    @Test
    @DisplayName("Test test that field 'name' of the Tag not empty")
    void testThatNameNotEmpty() {
        Tag tag = new Tag();
        tag.setName("");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Tag>> violations = validator.validate(tag);
        assertEquals(1, violations.size());
        assertEquals("", violations.iterator().next().getInvalidValue());
        factory.close();
    }

    @Test
    @DisplayName("Test test that field 'name' of the Tag not blank")
    void ctestThatNameNotBlank() {
        Tag tag = new Tag();
        tag.setName("   ");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Tag>> violations = validator.validate(tag);
        assertEquals(1, violations.size());
        assertEquals("   ", violations.iterator().next().getInvalidValue());
        factory.close();
    }

    @Test
    @DisplayName("Test test that field 'name' of the Tag not null")
    void ctestThatNameNotNull() {
        Tag tag = new Tag();
        tag.setName(null);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Tag>> violations = validator.validate(tag);
        assertEquals(2, violations.size());
        assertNull(violations.iterator().next().getInvalidValue());
        factory.close();
    }

}