package example.myVocabulary.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name="words")
@NoArgsConstructor
@AllArgsConstructor
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name="foreign_word")
    @NotNull(message = "The 'word' cannot be null")
    @Size(max = 255, message = "The length shouldn't be more than 255 symbols")
    private String foreignWord;

    @Column (name="translation_word")
    @NotNull(message = "The 'translation' cannot be null")
    @Size(max = 1000, message = "The length shouldn't be more than 1000 symbols")
    private String translationWord;

    @NotNull(message = "The 'tag' cannot be null")
    @ManyToOne
    @JoinColumn(name="tag_id")
    private Tag tag;
}
