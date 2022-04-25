package sk.stuba.fei.uim.vsa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.vsa.web.response.LectureDto;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String classroom;

    @ManyToMany
    private List<Student> students;

    public Lecture(LectureDto lectureDto) {
        this.id = lectureDto.getId();
        this.name = lectureDto.getName();
        this.classroom = lectureDto.getClassroom();
    }

}
