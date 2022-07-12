package br.com.transmaximo.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import br.com.transmaximo.model.Motorista;

@Component
public class MotoristaDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private DataSource dataSource;
	
	public int salvar(Motorista motorista) {
		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("MOTORISTA");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("DOCUMENTO", motorista.getDocumento());
		parameters.put("NOME", motorista.getNome());
		parameters.put("ENDERECO", motorista.getEndereco());
		parameters.put("DATANASCIMENTO", motorista.getDataNascimento());
		
		return insert.execute(parameters);
	}

}
