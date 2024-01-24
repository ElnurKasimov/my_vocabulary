package example.myVocabulary.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class WordTest {

    @Test
    @DisplayName("Test test that fields of the Word not null")
    void ctestThatNameNotNull() {
        Word word = new Word();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Word>> violations = validator.validate(word);
        assertEquals(3, violations.size());
        Iterator<ConstraintViolation<Word>> iterator = violations.iterator();
        assertNull(getInvalidValue(iterator, "foreignWord"));
        assertNull(getInvalidValue(iterator, "translationWord"));
        assertNull(getInvalidValue(iterator, "tag"));
        factory.close();
    }

    private Word getInvalidValue(Iterator<ConstraintViolation<Word>> iterator, String propertyName) {
        while (iterator.hasNext()) {
            ConstraintViolation<Word> violation = iterator.next();
            if (propertyName.equals(violation.getPropertyPath().toString())) {
                return (Word) violation.getInvalidValue();
            }
        }
        return null;
    }

}