package sk.stuba.fei.uim.vsa.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sk.stuba.fei.uim.vsa.domain.Student;
import sk.stuba.fei.uim.vsa.service.StudentService;
import sk.stuba.fei.uim.vsa.web.response.StudentDto;

@Path("/student")
public class StudentResource {

    public static final String EMPTY_RESPONSE = "{}";
    private final StudentService service = new StudentService();
    private final ObjectMapper json = new ObjectMapper();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getStudents() {
        try {
            return this.json.writeValueAsString(this.service.findAll());
        } catch (Exception e) {
            return EMPTY_RESPONSE;
        }
    }

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
    public Response postStudent(String requestBody) {
        try {
            StudentDto dto = this.json.readValue(requestBody, StudentDto.class);
            Student student = this.service.createStudent(new Student(dto));
            if(student == null) {
                return Response.status(Response.Status.CONFLICT).build();
            }
            return Response.status(Response.Status.CREATED)
                    .entity(this.json.writeValueAsString(student))
                    .build();
        } catch (JsonProcessingException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteStudent(@PathParam("id") Long id) {
        try {
            Student student = this.service.findStudentById(id);
            if(student == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            student = this.service.deleteStudent(student);
            return Response.status(Response.Status.OK)
                           .entity(this.json.writeValueAsString(new StudentDto(student)))
                           .build();
        } catch (JsonProcessingException e) {
            return Response.noContent().build();
        }
    }
}
