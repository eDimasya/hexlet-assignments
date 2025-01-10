package exercise.model;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.*;

// BEGIN
@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"price", "title"})
public class Product {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column
    private int price;
    @Column
    private String title;
}
// END
