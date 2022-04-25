package sk.stuba.fei.uim.vsa;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import sk.stuba.fei.uim.vsa.web.LectureResource;
import sk.stuba.fei.uim.vsa.web.StudentResource;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/school")
public class SchoolApplication extends Application {

    static final Set<Class<?>> appClasses = new HashSet<>();

    static {
        appClasses.add(StudentResource.class);
        appClasses.add(LectureResource.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return appClasses;
    }

}
