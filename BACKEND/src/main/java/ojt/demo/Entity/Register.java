package ojt.demo.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name ="register")
public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name="RegisterID")
    private int id;

    @Column(nullable = false, name="ClassID")
    private String classid;

    @Column(nullable = false, name="UserID")
    private String userid;
}





