package resources;
import dao.DentistaDAO;
import entities.Dentista;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/dentistas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DentistaResource {
    private DentistaDAO dao = new DentistaDAO();

    @POST
    public String inserir(Dentista d) {
        dao.inserir(d);
        return "Dentista cadastrado com sucesso!";
    }
    @GET
    public List<Dentista> listar() {
        return dao.listar();
    }
    @PUT
    public String atualizar(Dentista d) {
        dao.atualizar(d);
        return "Dentista atualizado com sucesso!";
    }
    @DELETE
    @Path("/{id}")
    public String deletar(@PathParam("id") Long id) {
        dao.deletar(id);
        return "Dentista deletado com sucesso!";
    }
}