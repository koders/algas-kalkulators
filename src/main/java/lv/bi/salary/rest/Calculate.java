package lv.bi.salary.rest;

import lv.bi.salary.model.Salary;
import lv.bi.salary.service.SalaryService;
import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *  REST API
 *
 *  @author Rihards
 */
@Path("/")
public class Calculate {

    private static final Logger log = Logger.getLogger(Calculate.class.getName());

    /**
     * 2nd step method
     * @param bruto bruto salary
     * @return HTTP 200 with salary object, HTTP 500 if error occurred
     */
    @GET
    @Path("calculate2")
    @Produces(MediaType.APPLICATION_JSON)
    public Response calculate2(@QueryParam("bruto") Double bruto) {
        try {
            // For longer ajax call simulation :)
            Thread.sleep(1000);
            return Response.ok(SalaryService.calculateNetoValue(bruto, 0)).build();
        } catch (Exception e) {
            log.warn(e.getMessage());
            return Response.serverError().entity("Failed to calculate salary").build();
        }
    }

    /**
     * 3rd step method
     * @param bruto bruto salary
     * @param dependents dependents
     * @return HTTP 200 with salary as double value, HTTP 500 if error occurred
     */
    @GET
    @Path("calculate3")
    @Produces(MediaType.APPLICATION_JSON)
    public Response calculate3(@QueryParam("bruto") Double bruto,
                               @QueryParam("dependents") Integer dependents) {
        try {
            // JVM needs to chill a bit...
            Thread.sleep(1000);
            return Response.ok(SalaryService.calculateNetoValue(bruto, dependents)).build();
        } catch (Exception e) {
            log.warn(e.getMessage());
            return Response.serverError().entity("Failed to calculate salary").build();
        }
    }

    /**
     * 4th and 5th step method
     * @param bruto bruto salary
     * @param dependents dependents
     * @return HTTP 200 with salary object, HTTP 500 if error occurred
     */
    @GET
    @Path("calculate4")
    @Produces(MediaType.APPLICATION_JSON)
    public Response calculate4(@QueryParam("bruto") Double bruto,
                               @QueryParam("dependents") Integer dependents) {
        try {
            Salary salary = new Salary(bruto, dependents);
            SalaryService.calculateNetoValue(salary);
            // Sweet dreams JVM
            Thread.sleep(1000);
            return Response.ok(salary).build();
        } catch (Exception e) {
            log.warn(e.getMessage());
            return Response.serverError().entity("Failed to calculate salary").build();
        }
    }

}
