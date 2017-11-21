/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;

/**
 *
 * @author janvanzetten
 */
public class DataBaseConector {
    private SQLServerDataSource dataSource;

    public DataBaseConector() throws IOException
    {
        dataSource = new SQLServerDataSource();

        dataSource.setServerName("EASV-DB2");
        dataSource.setPortNumber(1433);
        dataSource.setDatabaseName("inn2powerJan");
        dataSource.setUser("CS2017A_14_java");
        dataSource.setPassword("javajava");//indtast password
    }

    public Connection getConnection() throws SQLServerException
    {
        return dataSource.getConnection();
    }
    
}
