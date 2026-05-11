import infra.DataBaseConfig;
import java.sql.Connection;
import java.util.logging.Logger;
import java.util.logging.Level;

public class TesteBanco {

    private static final Logger logger = Logger.getLogger(TesteBanco.class.getName());

    public static void main(String[] args) {
        try (Connection conn = DataBaseConfig.getConnection()) {

            logger.info("Conectado com sucesso!");

            var stmt = conn.createStatement();
            var rs = stmt.executeQuery("SELECT 1 FROM dual");

            if (rs.next()) {
                logger.info("Banco respondeu!");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao conectar com o banco", e);
        }
    }
}