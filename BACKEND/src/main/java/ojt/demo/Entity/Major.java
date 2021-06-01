package ojt.demo.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@Entity
@Table(name ="major")
public class Major {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name="MajorID")
    private int id;

    @Column(nullable = false, name="Major_Name")
    private String name;

    @Column(nullable = true, name="Major_Code")
    private String majorcode;
}
