package sk.stuba.fei.uim.vsa.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import sk.stuba.fei.uim.vsa.service.StudentService;
import sk.stuba.fei.uim.vsa.web.response.StudentResponse;

@Path("/student")
public class StudentResource {

    public static final String EMPTY_RESPONSE = "{}";
    private final StudentService service = new StudentService();
    private final ObjectMapper json = new ObjectMapper();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StudentResponse getStudent(@PathParam("id") Long id) {
        return new StudentResponse(this.service.getStudent(id));
    }
}
