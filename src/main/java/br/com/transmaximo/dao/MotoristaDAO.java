package br.com.transmaximo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

	public Motorista buscarPorId(Long id) {
		String sql = "SELECT * FROM MOTORISTA WHERE ID = ?";

		return jdbcTemplate.queryForObject(sql, new RowMapper<Motorista>() {
			@Override
			public Motorista mapRow(ResultSet rs, int rowNum) throws SQLException {
				Motorista motorista = new Motorista();
				motorista.setId(rs.getLong("ID"));
				motorista.setDocumento(rs.getLong("DOCUMENTO"));
				motorista.setNome(rs.getString("NOME"));
				motorista.setEndereco(rs.getString("ENDERECO"));
				motorista.setDataNascimento(rs.getString("DATANASCIMENTO"));

				return motorista;
			}
		}, new Object[] { id });
	}

	public Motorista buscarPorNome(String nome) {
		String sql = "SELECT * FROM MOTORISTA WHERE NOME = ?";

		return jdbcTemplate.queryForObject(sql, new RowMapper<Motorista>() {

			public Motorista mapRow(ResultSet rs, int rowNum) throws SQLException {
				Motorista motorista = new Motorista();

				motorista.setId(rs.getLong("ID"));
				motorista.setDocumento(rs.getLong("DOCUMENTO"));
				motorista.setNome(rs.getString("NOME"));
				motorista.setEndereco(rs.getString("ENDERECO"));
				motorista.setDataNascimento(rs.getString("DATANASCIMENTO"));

				return motorista;
			}
		}, new Object[] { nome });
	}

	public void atualizar(Motorista motorista, Long id) {

		String sql = "UPDATE MOTORISTA SET DOCUMENTO = ?, NOME = ?, ENDERECO = ?, DATANASCIMENTO = ? WHERE ID = ?";

		jdbcTemplate.update(sql, new Object[] { motorista.getDocumento(), motorista.getNome(), motorista.getEndereco(),
				motorista.getDataNascimento(), id });
	}
	
	public void deletar(Long id) {
		String sql = "DELETE FROM MOTORISTA WHERE ID = ?";
		
		jdbcTemplate.update(sql, new Object[] {id});
	}

}
