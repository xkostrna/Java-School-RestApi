package sk.stuba.fei.uim.vsa.domain;

import lombok.*;
import sk.stuba.fei.uim.vsa.web.response.StudentDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQuery(name = Student.FIND_ALL, query = "select s from Student s")
@NamedQuery(name = Student.FIND_BY_EMAIL, query = "select s from Student s where s.email = :email")
public class Student implements Serializable {

    public static final String FIND_ALL = "Student.findAll";
    public static final String FIND_BY_EMAIL = "Student.findByEmail";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;
    @Column(unique = true, nullable = false)
    private String email;
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @ToString.Exclude
    @ManyToMany
    private List<Lecture> lectures;

    public Student(StudentDto dto) {
        this.id = dto.getId();
        this.firstname = dto.getFirstname();
        this.lastname = dto.getLastname();
        this.email = dto.getEmail();
        this.grade = dto.getGrade();
        this.lectures = dto.getLectures().stream().map(Lecture::new).collect(Collectors.toList());
    }


}
