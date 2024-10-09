package lucia.krausova.task.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uuid;
    private String name;
    private BigDecimal price;

    @JsonIgnore // Json Ignore - necessary to get rid off the looped children in the GET - double-checked via Postman
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product )) return false;
        return uuid != null && uuid.equals(((Product) o).getUuid());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
