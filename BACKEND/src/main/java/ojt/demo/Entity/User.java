package ojt.demo.Entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name ="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name="UserID")
    private int id;

    @Column(nullable = true, name="Student_Code")
    private String studentcode;

    @Column(nullable = false, name="User_Name")
    private String name;

    @Column(nullable = false, name="Full_Name")
    @Length(max = 50, message = "Fullname is too long")
    private String fullname;

    @Column(nullable = false, name="Major_ID")
    private int majorid;

    @Column(nullable = false, name="DOB")
    private String dob;

    @Column(nullable = false, name="Image")
    private String image;

    @Column(nullable = false, name="Email")
    private String email;

    @Column(nullable = false, name="Gender")
    private Boolean gender;

    @Column(nullable = false, name="Phone")
    private String phone;

    @Column(nullable = false, name="Password")
    private String password;

    @Column(nullable = false, name="RoleID")
    private int roleId;

    @Column( name="CertificationID")
    private int certificationID;

}



