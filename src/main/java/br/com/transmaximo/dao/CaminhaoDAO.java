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

import br.com.transmaximo.model.Caminhao;
import br.com.transmaximo.paginacao.ConfigPagina;

@Component
public class CaminhaoDAO extends DataAccessObject<Caminhao> {

	@Override
	public Caminhao salvar(Caminhao caminhao) {

		SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).usingGeneratedKeyColumns("ID")
				.withTableName("CAMINHAO");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PLACA", caminhao.getPlaca());
		parameters.put("MODELO", caminhao.getModelo());
		parameters.put("ANOFABRICACAO", caminhao.getAnoFabricacao());
		parameters.put("CAPACIDADE", caminhao.getCapacidade());

		return buscarPorId(insert.executeAndReturnKey(parameters).longValue()).get();
	}

	@Override
	public Optional<Caminhao> buscarPorId(Long id) {

		String sql = "SELECT * FROM CAMINHAO WHERE ID = ?";

		return jdbcTemplate.queryForObject(sql, new RowMapper<Optional<Caminhao>>() {
			@Override
			public Optional<Caminhao> mapRow(ResultSet rs, int rowNum) throws SQLException {
				Caminhao caminhao = new Caminhao();
				caminhao.setId(rs.getLong("ID"));
				caminhao.setPlaca(rs.getString("PLACA"));
				caminhao.setModelo(rs.getString("MODELO"));
				caminhao.setAnoFabricacao(rs.getString("ANOFABRICACAO"));
				caminhao.setCapacidade(rs.getDouble("CAPACIDADE"));

				return Optional.of(caminhao);
			}
		}, new Object[] { id });
	}

	public Caminhao buscarPorPlaca(String placa) {

		String sql = "SELECT * FROM CAMINHAO WHERE PLACA = ?";

		return jdbcTemplate.queryForObject(sql, new RowMapper<Caminhao>() {
			@Override
			public Caminhao mapRow(ResultSet rs, int rowNum) throws SQLException {
				Caminhao caminhao = new Caminhao();
				caminhao.setId(rs.getLong("ID"));
				caminhao.setPlaca(rs.getString("PLACA"));
				caminhao.setModelo(rs.getString("MODELO"));
				caminhao.setAnoFabricacao(rs.getString("ANOFABRICACAO"));
				caminhao.setCapacidade(rs.getDouble("CAPACIDADE"));

				return caminhao;
			}
		}, new Object[] { placa });
	}

	@Override
	public void atualizar(Caminhao caminhao, Long id) {
		String sql = "UPDATE CAMINHAO SET PLACA = ?, MODELO = ?, ANOFABRICACAO = ?, CAPACIDADE = ? WHERE ID = ?";

		jdbcTemplate.update(sql, new Object[] { caminhao.getPlaca(), caminhao.getModelo(), caminhao.getAnoFabricacao(),
				caminhao.getCapacidade(), id });
	}

	@Override
	public void deletar(Long id) {
		String sql = "DELETE FROM CAMINHAO WHERE ID = ?";

		jdbcTemplate.update(sql, new Object[] { id });
	}

	@Override
	public List<Caminhao> listarTodos(ConfigPagina configPagina) {
		String sql = "SELECT * FROM CAMINHAO LIMIT ?, ?";

		return jdbcTemplate.query(sql, new RowMapper<Caminhao>() {

			@Override
			public Caminhao mapRow(ResultSet rs, int rowNum) throws SQLException {
				Caminhao caminhao = new Caminhao();

				caminhao.setPlaca(rs.getString("PLACA"));
				caminhao.setModelo(rs.getString("MODELO"));
				caminhao.setAnoFabricacao(rs.getString("ANOFABRICACAO"));
				caminhao.setCapacidade(rs.getDouble("CAPACIDADE"));

				return caminhao;
			}

		}, new Object[] { configPagina.getPrimeiroElemento(), configPagina.getTamanho() });
	}

}
