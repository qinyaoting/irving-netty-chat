package com.irving.netty;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/*@SpringBootTest
class Springboot04MybatisApplicationTests {

    @Autowired(required = false)
    DataSource dataSource;



    @Test
    void contextLoads() throws SQLException {
        System.out.println("default datasource:" + dataSource.getClass());
        Connection conn = dataSource.getConnection();
        System.out.println("conn" + conn);
        conn.close();
    }

}*/
