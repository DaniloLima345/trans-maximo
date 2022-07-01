package br.com.transmaximo.config.database;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
	public DataSource dataSource;
	
	public ConnectionFactory() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/transmaximo?useTimezone=true&serverTimezone=UTC");
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("");
		
		this.dataSource = comboPooledDataSource;
	}
	
	public Connection recuperarDados() throws SQLException {
		return this.dataSource.getConnection();
	}

}
