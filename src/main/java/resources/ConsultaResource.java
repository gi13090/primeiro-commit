package resources;

import dao.ConsultaDAO;
import entities.Consulta;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/consultas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConsultaResource {

    private ConsultaDAO dao = new ConsultaDAO();

    @POST
    public Response inserir(Consulta c) {
        try {
            dao.inserir(c);
            return Response.status(Response.Status.CREATED)
                    .entity(c)
                    .build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao inserir consulta")
                    .build();
        }
    }
    @GET
    public List<Consulta> listar() {
        return dao.listarTodos();
    }
    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") Long id) {
        Consulta c = dao.buscarPorId(id);

        if (c == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(c).build();
    }
    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, Consulta c) {
        Consulta existente = dao.buscarPorId(id);

        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        try {
            c.setId(id);
            dao.atualizar(c);

            return Response.ok(c).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar consulta")
                    .build();
        }
    }
    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        Consulta existente = dao.buscarPorId(id);

        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        dao.deletar(id);

        return Response.status(Response.Status.NO_CONTENT).build();
    }
}