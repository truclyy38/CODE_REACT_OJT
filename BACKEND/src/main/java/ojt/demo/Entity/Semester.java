package ojt.demo.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name ="semester")
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name="SemesterID")
    private int id;

    @Column(nullable = false, name="Semester")
    private int semester;

    @Column(nullable = false, name="Class_Id")
    private int classId;

    @Column(nullable = false, name="CourseID")
    private int courseId;

}

