package infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DataBaseConfig {

    private static final Logger logger = Logger.getLogger(DataBaseConfig.class.getName());

    private static final String URL = loadUrlFromProperties("database.properties", "url");
    private static final String USER = loadUrlFromProperties("database.properties", "user");
    private static final String PASSWORD = loadUrlFromProperties("database.properties", "password");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void Initialize() {
        var sql = """
                CREATE TABLE BENEFICIARIO (
                    id NUMBER PRIMARY KEY,
                    nome VARCHAR2(255),
                    cpf VARCHAR2(20),
                    telefone VARCHAR2(20),
                    email VARCHAR2(100),
                    endereco VARCHAR2(255)
                );
                CREATE TABLE DENTISTA (
                    id NUMBER PRIMARY KEY,
                    nome VARCHAR2(100),
                    cro VARCHAR2(50)
                );
                CREATE TABLE CONSULTA (
                    id NUMBER PRIMARY KEY,
                    id_beneficiario NUMBER,
                    id_dentista NUMBER,
                    data_hora TIMESTAMP
                );
                CREATE TABLE DOADOR (
                    ID_PESSOA NUMBER NOT NULL,
                    TIPO_DOADOR VARCHAR2(20) NOT NULL
                );
                """;

        try (var conn = getConnection()) {
            var stmt = conn.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException e) {
            if (e.getErrorCode() == 955) {
                logger.info("Tabela já existente, ignorando criação.");
            } else {
                e.printStackTrace();
                throw new RuntimeException("Erro ao inicializar banco de dados", e);
            }
        }
    }

    private static String loadUrlFromProperties(String fileName, String key) {
        try {
            var input = DataBaseConfig.class
                    .getClassLoader()
                    .getResourceAsStream(fileName);

            var props = new java.util.Properties();
            props.load(input);
            return props.getProperty(key);

        } catch (Exception e) {
            throw new RuntimeException("Falha ao carregar database.properties", e);
        }
    }
}