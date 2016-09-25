package lv.bi.salary;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Application configuration
 *
 * @author Rihards
 */
@ApplicationPath("rest")
public class MyApplication extends ResourceConfig {
    public MyApplication() {
        packages("lv.bi.salary.rest");
    }
}

