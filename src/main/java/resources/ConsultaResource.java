package resources;
import dao.ConsultaDAO;
import entities.Consulta;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/consultas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConsultaResource {
    private ConsultaDAO dao = new ConsultaDAO();


    @POST
    public String inserir(Consulta c) {
        dao.inserir(c);
        return "Consulta cadastrada com sucesso!";
    }
    @GET
    public List<Consulta> listar() {
        return dao.listar();
    }
    @PUT
    @Path("/{id}")
    public String atualizar(@PathParam("id") Long id, Consulta c) {
        c.setId(id);
        dao.atualizar(c);
        return "Consulta atualizada com sucesso!";
    }
    @DELETE
    @Path("/{id}")
    public String deletar(@PathParam("id") Long id) {
        dao.deletar(id);
        return "Consulta deletada com sucesso!";
    }
}