package sk.stuba.fei.uim.vsa.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.vsa.domain.Grade;
import sk.stuba.fei.uim.vsa.domain.Student;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {

    private String firstname;
    private String lastname;
    private String email;
    private Grade grade;

    public StudentResponse(final Student student) {
        this.firstname = student.getFirstname();
        this.lastname = student.getLastname();
        this.email = student.getEmail();
        this.grade = student.getGrade();
    }

}
