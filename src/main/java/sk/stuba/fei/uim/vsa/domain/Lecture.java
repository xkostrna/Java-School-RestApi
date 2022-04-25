package sk.stuba.fei.uim.vsa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import sk.stuba.fei.uim.vsa.web.response.LectureDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Lecture implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String classroom;

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Student> students;

    public Lecture(LectureDto lectureDto) {
        this.id = lectureDto.getId();
        this.name = lectureDto.getName();
        this.classroom = lectureDto.getClassroom();
        this.students = lectureDto.getStudents().stream().map(Student::new).collect(Collectors.toList());
    }

}
