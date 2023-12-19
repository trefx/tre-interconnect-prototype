package com.arjuna.sde.analysis.datashield;

import java.util.Base64;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.QueryParam;

@RegisterRestClient(configKey = "armadillo")
@ClientHeaderParam(name = "Authorization", value = "{genAuth}")
public interface ArmadilloService
{
    @POST
    @Path("/select-profile")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public String postSelectProfile(String profileName, @QueryParam("async") Boolean async);

    @POST
    @Path("/load-table")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public String postLoadTable(@QueryParam("symbol") String symbolName, @QueryParam("table") String tableName, @QueryParam("async") Boolean async);

    @POST
    @Path("/execute")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public String postExecute(String command, @QueryParam("async") Boolean async);

    @GET
    @Path("/tables")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTables(@QueryParam("async") Boolean async);

    public default String genAuth()
    {
        return "Basic " + Base64.getEncoder().encodeToString("admin:admin".getBytes());
    }
}
