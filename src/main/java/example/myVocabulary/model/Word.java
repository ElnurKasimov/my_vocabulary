package example.myVocabulary.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "The 'word' cannot be null")
    @NotBlank(message = "The 'word' cannot be empty")
    @Column (name="foreign_word")
    private String foreignWord;

    @NotNull(message = "The 'translation' cannot be null")
    @NotBlank(message = "The 'translation' cannot be empty")
    @Column (name="translation_word")
    private String translationWord;

    @NotNull(message = "The 'tag' cannot be null")
    @ManyToOne
    @JoinColumn(name="tag_id")
    private Tag tag;
}
