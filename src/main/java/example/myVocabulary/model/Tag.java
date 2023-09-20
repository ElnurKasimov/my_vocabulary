package example.myVocabulary.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "The 'name' cannot be null")
    @NotBlank(message = "The 'name' cannot be empty")
    @Column (name = "name")
    private String name;

    @OneToMany(mappedBy = "words", cascade = CascadeType.REMOVE)
    List<Word> words;
}
