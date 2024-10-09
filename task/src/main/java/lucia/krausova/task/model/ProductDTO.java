package lucia.krausova.task.model;

import lombok.*;

import java.math.BigDecimal;

/**
 * not used yet
 * make the controller endpoints more meaningful by using DTOs
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer categoryId;
}
