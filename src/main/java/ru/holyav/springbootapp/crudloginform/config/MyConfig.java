
package ru.holyav.springbootapp.crudloginform.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Objects;


@Configuration
//@ComponentScan(basePackages = "ru.holyav.springbootapp.crudloginform")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class MyConfig{
    public MyConfig(){
    }


    @Autowired
    private Environment env;





    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        dataSource.setDriverClass(env.getProperty("database.driver"));
        dataSource.setJdbcUrl(env.getProperty("database.jdbcUrl"));
        dataSource.setUser(env.getProperty("database.user"));
        dataSource.setPassword(env.getProperty("database.password"));
        dataSource.setMinPoolSize(Integer.parseInt(Objects.requireNonNull(env.getProperty("database.minPoolSize"))));
        dataSource.setMaxPoolSize(Integer.parseInt(Objects.requireNonNull(env.getProperty("database.maxPoolSize"))));
        dataSource.setMaxIdleTime(Integer.parseInt(Objects.requireNonNull(env.getProperty("database.maxIdleTime"))));
        return dataSource;
  }







}



