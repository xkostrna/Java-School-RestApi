package sk.stuba.fei.uim.vsa.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sk.stuba.fei.uim.vsa.domain.Lecture;
import sk.stuba.fei.uim.vsa.service.LectureService;
import sk.stuba.fei.uim.vsa.web.response.LectureDto;

@Path("/lecture")
public class LectureResource {

    public static final String EMPTY_RESPONSE = "{}";
    private final LectureService service = new LectureService();
    private final ObjectMapper json = new ObjectMapper();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postLecture(String requestBody) {
        try {
            LectureDto dto = json.readValue(requestBody, LectureDto.class);
            Lecture lecture = this.service.createLecture(new Lecture(dto));
            if(lecture == null) {
                return Response.status(Response.Status.CONFLICT).build();
            }
            return Response.status(Response.Status.CREATED)
                    .entity(json.writeValueAsString(lecture))
                    .build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
