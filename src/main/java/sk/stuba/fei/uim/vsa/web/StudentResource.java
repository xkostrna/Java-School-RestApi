package sk.stuba.fei.uim.vsa.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import sk.stuba.fei.uim.vsa.domain.Student;
import sk.stuba.fei.uim.vsa.service.StudentService;
import sk.stuba.fei.uim.vsa.web.response.StudentDto;

@Path("/student")
public class StudentResource {

    public static final String EMPTY_RESPONSE = "{}";
    private final StudentService service = new StudentService();
    private final ObjectMapper json = new ObjectMapper();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStudent(@PathParam("id") Long id) {
        try {
            return json.writeValueAsString(new StudentDto(this.service.findStudentById(id)));
        } catch (Exception e) {
            return EMPTY_RESPONSE;
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postStudent(String requestBody) {
        try {
            StudentDto dto = json.readValue(requestBody, StudentDto.class);
            Student student = new Student(dto);
            if(this.service.saveStudent(student) != null) {
                dto = new StudentDto(student);
                return json.writeValueAsString(dto);
            } else {
                return EMPTY_RESPONSE;
            }
        } catch (Exception e) {
            return EMPTY_RESPONSE;
        }
    }
}
