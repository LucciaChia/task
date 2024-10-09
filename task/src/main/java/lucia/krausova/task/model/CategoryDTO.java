package lucia.krausova.task.model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * not used yet
 * make the controller endpoints more meaningful by using DTOs
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {

    private Integer id;
    private String name;
    private Set<ProductDTO> products = new HashSet<>();
}
