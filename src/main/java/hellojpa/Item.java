package hellojpa;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Item {
    @Id @GeneratedValue
    private Long id;
    
    private String name;
    private int price;
}
