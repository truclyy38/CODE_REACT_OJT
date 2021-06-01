package ojt.demo.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name ="classes")
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name="id")
    private int id;

    @Column(nullable = false, name="name")
    private String name;

    @Column(nullable = false, name="semester")
    private int semester;

    @Column(nullable = false, name="majorid")
    private int majorid;

    @Column(nullable = false, name="total")
    private int total;

}

