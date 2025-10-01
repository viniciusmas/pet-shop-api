package br.edu.infnet.petshopapi.config;

import org.h2.tools.Server;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class H2ServerConfig {

    @Bean
    public DataSource initDataSource() throws SQLException {
        String userDir = System.getProperty("user.dir");
        String[] args = {"-tcp", "-ifNotExists", "-tcpPort", "9092", "-baseDir", userDir.concat("/h2db")};
        Server.createTcpServer(args).start();
        return DataSourceBuilder.create().url("jdbc:h2:tcp://localhost:9092/databasePetShop").username("sa").password("").build();
    }
}