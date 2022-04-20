package sk.stuba.fei.uim.vsa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.vsa.web.response.StudentDto;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;
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
