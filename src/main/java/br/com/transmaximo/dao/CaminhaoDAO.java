package br.com.transmaximo.dao;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import br.com.transmaximo.model.Caminhao;

@Component
public class CaminhaoDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private DataSource dataSource;
	
	public int salvar(Caminhao caminhao) {

		SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("CAMINHAO");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PLACA", caminhao.getPlaca());
		parameters.put("MODELO", caminhao.getModelo());
		parameters.put("ANOFABRICACAO", caminhao.getAnoFabricacao());
		parameters.put("CAPACIDADE", caminhao.getCapacidade());
		
		return insert.execute(parameters);
	}
	
	public Caminhao buscarPorId(Long id) {
		
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
		String sql = "SELECT * FROM CAMINHAO WHERE ID = :id";
		
		return jdbcTemplate.queryForObject(sql, new Object[] {id}, (rs,rowNum)->
				new Caminhao(rs.getLong("id"), rs.getString("placa"), rs.getString("modelo"), rs.getString("anoFabricacao"), rs.getDouble("capacidade")));
		
	}

}
