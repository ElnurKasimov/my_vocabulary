package example.myVocabulary.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="words")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name="foreign_word")
    @NotNull(message = "The 'word' cannot be null")
    private String foreignWord;

    @Column (name="translation_word")
    @NotNull(message = "The 'translation' cannot be null")
    private String translationWord;

    @Column (name="description")

    private String description;

    @NotNull(message = "The 'tag' cannot be null")
    @ManyToOne
    @JoinColumn(name="tag_id")
    private Tag tag;
}
