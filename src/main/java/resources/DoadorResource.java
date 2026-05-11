package resources;

import dao.DoadorDAO;
import entities.Doador;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/doadores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DoadorResource {
    private DoadorDAO dao = new DoadorDAO();

    @POST
    public Response inserir(Doador d) {
        try {
            dao.inserir(d);
            return Response.status(Response.Status.CREATED)
                    .entity(d)
                    .build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao inserir doador")
                    .build();
        }
    }
    @GET
    public List<Doador> listar() {
        return dao.listar();
    }
    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") Long id) {

        Doador d = dao.buscarPorId(id);

        if (d == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(d).build();
    }
    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, Doador d) {
        Doador existente = dao.buscarPorId(id);

        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {
            d.setId(id);
            dao.atualizar(d);

            return Response.ok(d).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar doador")
                    .build();
        }
    }
    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        Doador existente = dao.buscarPorId(id);

        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        dao.deletar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}