package resources;

import dao.DoadorDAO;
import entities.Doador;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/doadores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DoadorResource {
    private DoadorDAO dao = new DoadorDAO();

    @POST
    public String inserir(Doador d) {
        dao.inserir(d);
        return "Doador cadastrado com sucesso!";
    }
    @GET
    public List<Doador> listar() {
        return dao.listar();
    }
    @PUT
    public String atualizar(Doador d) {
        dao.atualizar(d);
        return "Doador atualizado com sucesso!";
    }
    @DELETE
    @Path("/{id}")
    public String deletar(@PathParam("id") Long id) {
        dao.deletar(id);
        return "Doador deletado com sucesso!";
    }
}