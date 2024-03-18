package example.myVocabulary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagRequest {

    @NotNull(message = "The 'name' cannot be null")
    @NotBlank(message = "The 'name' cannot be empty")
    @Size(max = 255, message = "The 'name' length shouldn't be more than 255 symbols")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagRequest that = (TagRequest) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "TagRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
