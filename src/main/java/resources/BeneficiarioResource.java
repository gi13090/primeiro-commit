package resources;
import dao.BeneficiarioDAO;
import entities.Beneficiario;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/beneficiarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BeneficiarioResource {
    private BeneficiarioDAO dao = new BeneficiarioDAO();

    @POST
    public String inserir(Beneficiario b) {
        dao.inserir(b);
        return "Beneficiário cadastrado com sucesso!";
    }
    @GET
    public List<Beneficiario> listar() {
        return dao.listar();
    }
    @PUT
    @Path("/{id}")
    public String atualizar(@PathParam("id") Long id, Beneficiario b) {
        b.setId(id);
        dao.atualizar(b);
        return "Beneficiário atualizado com sucesso!";
    }
    @DELETE
    @Path("/{id}")
    public String deletar(@PathParam("id") Long id) {
        dao.deletar(id);
        return "Beneficiário deletado com sucesso!";
    }
}