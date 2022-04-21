package sk.stuba.fei.uim.vsa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.vsa.web.response.StudentDto;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQuery(name = Student.FIND_ALL, query = "select s from Student s")
public class Student {

    public static final String FIND_ALL = "Student.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;
    @Column(unique = true, nullable = false)
    private String email;
    @Enumerated(EnumType.STRING)
    private Grade grade;

    public Student(StudentDto dto) {
        this.id = dto.getId();
        this.firstname = dto.getFirstname();
        this.lastname = dto.getLastname();
        this.email = dto.getEmail();
        this.grade = dto.getGrade();
    }


}
