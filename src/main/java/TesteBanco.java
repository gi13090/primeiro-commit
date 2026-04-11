import infra.DataBaseConfig;
import java.sql.Connection;

public class TesteBanco {

    public static void main(String[] args) {
        try (Connection conn = DataBaseConfig.getConnection()) {
            System.out.println("✅ Conectado com sucesso!");

            var stmt = conn.createStatement();
            var rs = stmt.executeQuery("SELECT 1 FROM dual");

            if (rs.next()) {
                System.out.println("✅ Banco respondeu!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}