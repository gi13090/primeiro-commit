package resources;

import dao.BeneficiarioDAO;
import entities.Beneficiario;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/beneficiarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BeneficiarioResource {
    private BeneficiarioDAO dao = new BeneficiarioDAO();

    @POST
    public Response inserir(Beneficiario b) {
        try {
            dao.inserir(b);
            return Response.status(Response.Status.CREATED)
                    .entity(b)
                    .build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao inserir beneficiário")
                    .build();
        }
    }
    @GET
    public List<Beneficiario> listar() {
        return dao.listar();
    }
    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") Long id) {
        Beneficiario b = dao.buscarPorId(id);

        if (b == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(b).build();
    }
    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, Beneficiario b) {
        Beneficiario existente = dao.buscarPorId(id);

        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        try {
            b.setId(id);
            dao.atualizar(b);

            return Response.ok(b).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar beneficiário")
                    .build();
        }
    }
    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        Beneficiario existente = dao.buscarPorId(id);

        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        dao.deletar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}