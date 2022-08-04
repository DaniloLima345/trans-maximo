package br.com.transmaximo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import br.com.transmaximo.model.Motorista;
import br.com.transmaximo.paginacao.ConfigPagina;

@Component
public class MotoristaDAO extends DataAccessObject<Motorista> {

	@Override
	public Motorista salvar(Motorista motorista) {

		SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).usingGeneratedKeyColumns("ID")
				.withTableName("MOTORISTA");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("DOCUMENTO", motorista.getDocumento());
		parameters.put("NOME", motorista.getNome());
		parameters.put("ENDERECO", motorista.getEndereco());
		parameters.put("DATANASCIMENTO", motorista.getDataNascimento());

		return buscarPorId(insert.executeAndReturnKey(parameters).longValue()).get();
	}

	@Override
	public Optional<Motorista> buscarPorId(Long id) {
		String sql = "SELECT * FROM MOTORISTA WHERE ID = ?";

		return jdbcTemplate.queryForObject(sql, new RowMapper<Optional<Motorista>>() {
			@Override
			public Optional<Motorista> mapRow(ResultSet rs, int rowNum) throws SQLException {
				Motorista motorista = new Motorista();

				motorista.setId(rs.getLong("ID"));
				motorista.setDocumento(rs.getLong("DOCUMENTO"));
				motorista.setNome(rs.getString("NOME"));
				motorista.setEndereco(rs.getString("ENDERECO"));
				motorista.setDataNascimento(rs.getString("DATANASCIMENTO"));

				return Optional.of(motorista);
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

	@Override
	public void atualizar(Motorista motorista, Long id) {

		StringBuilder sqlBuilder = new StringBuilder();

		sqlBuilder.append("UPDATE MOTORISTA SET ");

		if (motorista.getDocumento() != null)
			sqlBuilder.append("DOCUMENTO = " + motorista.getDocumento());
		if (motorista.getNome() != null)
			sqlBuilder.append("NOME = '" + motorista.getNome() + "'");
		if (motorista.getEndereco() != null)
			sqlBuilder.append("ENDERECO = '" + motorista.getEndereco() + "'");
		if (motorista.getDataNascimento() != null)
			sqlBuilder.append("DATANASCIMENTO = '" + motorista.getDataNascimento() + "'");

		sqlBuilder.append(" WHERE ID = '" + id + "'");

		String sql = sqlBuilder.toString();

		jdbcTemplate.update(sql);

	}

	@Override
	public void deletar(Long id) {
		String sql = "DELETE FROM MOTORISTA WHERE ID = ?";

		jdbcTemplate.update(sql, new Object[] { id });
	}

	@Override
	public List<Motorista> listarTodos(ConfigPagina configPagina) {
		String sql = "SELECT * FROM MOTORISTA LIMIT ?, ?";

		return jdbcTemplate.query(sql, new RowMapper<Motorista>() {

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

		}, new Object[] { configPagina.getPrimeiroElemento(), configPagina.getTamanho() });
	}

}
