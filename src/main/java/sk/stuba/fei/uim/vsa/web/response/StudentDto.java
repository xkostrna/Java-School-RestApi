package sk.stuba.fei.uim.vsa.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.vsa.domain.Grade;
import sk.stuba.fei.uim.vsa.domain.Student;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private Grade grade;

    private List<LectureDto> lectures;

    public StudentDto(final Student student) {
        this.id = student.getId();
        this.firstname = student.getFirstname();
        this.lastname = student.getLastname();
        this.email = student.getEmail();
        this.grade = student.getGrade();
        this.lectures = student.getLectures().stream().map(LectureDto::new).collect(Collectors.toList());
    }

}
