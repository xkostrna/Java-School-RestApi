package sk.stuba.fei.uim.vsa;

import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

public class SchoolApplication extends Application {

    static final Set<Class<?>> appClasses = new HashSet<>();

    static {
//        appClasses.add();
    }

    @Override
    public Set<Class<?>> getClasses() {
        return appClasses;
    }

}
