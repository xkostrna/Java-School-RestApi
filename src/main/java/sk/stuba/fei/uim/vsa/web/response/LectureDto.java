package sk.stuba.fei.uim.vsa.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.vsa.domain.Lecture;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LectureDto {

    private Long id;
    private String name;
    private String classroom;
    private List<StudentDto> students;

    public LectureDto(Lecture lecture) {
        this.id = lecture.getId();
        this.name = lecture.getName();
        this.classroom = lecture.getClassroom();
        this.students = lecture.getStudents().stream().map(StudentDto::new).collect(Collectors.toList());
    }

}
