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
import br.com.transmaximo.model.Motorista;
import br.com.transmaximo.model.Viagem;
import br.com.transmaximo.paginacao.ConfigPagina;

@Component
public class ViagemDAO extends DataAccessObject<Viagem> {

	@Override
	public Viagem salvar(Viagem viagem) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).usingGeneratedKeyColumns("ID")
				.withTableName("VIAGEM");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("DESTINO", viagem.getDestino());
		parameters.put("TIPO_CARGA", viagem.getTipoCarga());
		parameters.put("ID_MOTORISTA", viagem.getMotorista().getId());
		parameters.put("ID_CAMINHAO", viagem.getCaminhao().getId());
		return buscarPorId(insert.executeAndReturnKey(parameters).longValue()).get();
	}

	@Override
	public Optional<Viagem> buscarPorId(Long id) {

		String sql = "SELECT * FROM VIAGEM V INNER JOIN MOTORISTA M INNER JOIN CAMINHAO C "
				+ "WHERE V.ID = ? AND V.ID_MOTORISTA = M.ID AND V.ID_CAMINHAO = C.ID";

		return jdbcTemplate.queryForObject(sql, new RowMapper<Optional<Viagem>>() {
			@Override
			public Optional<Viagem> mapRow(ResultSet rs, int rowNum) throws SQLException {
				Viagem viagem = new Viagem();
				Motorista motorista = new Motorista();
				Caminhao caminhao = new Caminhao();

				motorista.setId(rs.getLong("ID"));
				motorista.setNome(rs.getString("NOME"));
				motorista.setEndereco(rs.getString("ENDERECO"));
				motorista.setDataNascimento(rs.getString("DATANASCIMENTO"));

				caminhao.setId(rs.getLong("ID"));
				caminhao.setPlaca(rs.getString("PLACA"));
				caminhao.setModelo(rs.getString("MODELO"));
				caminhao.setAnoFabricacao(rs.getString("ANOFABRICACAO"));
				caminhao.setCapacidade(rs.getDouble("CAPACIDADE"));

				viagem.setId(rs.getLong("ID"));
				viagem.setDestino(rs.getString("DESTINO"));
				viagem.setTipoCarga(rs.getString("TIPO_CARGA"));
				viagem.setMotorista(motorista);
				viagem.setCaminhao(caminhao);

				return Optional.of(viagem);
			}
		}, new Object[] { id });
	}

	@Override
	public void atualizar(Viagem viagem, Long id) {

		StringBuilder sqlBuilder = new StringBuilder();

		sqlBuilder.append("UPDATE VIAGEM SET ");

		if (viagem.getDestino() != null)
			sqlBuilder.append("DESTINO = '" + viagem.getDestino() + "'");
		if (viagem.getTipoCarga() != null)
			sqlBuilder.append("TIPO_CARGA = '" + viagem.getTipoCarga() + "'");
		if (viagem.getMotorista() != null)
			sqlBuilder.append("ID_MOTORISTA = " + viagem.getMotorista());
		if (viagem.getCaminhao() != null)
			sqlBuilder.append("ID_CAMINHAO = " + viagem.getCaminhao());

		sqlBuilder.append(" WHERE ID = " + id);

		String sql = sqlBuilder.toString();

		jdbcTemplate.update(sql);
	}

	@Override
	public void deletar(Long id) {

		String sql = "DELETE FROM VIAGEM WHERE ID = ?";

		jdbcTemplate.update(sql, new Object[] { id });
	}

	@Override
	public List<Viagem> listarTodos(ConfigPagina configPagina) {

		String sql = "SELECT * FROM VIAGEM LIMIT ?, ?";

		return jdbcTemplate.query(sql, new RowMapper<Viagem>() {
			@Override
			public Viagem mapRow(ResultSet rs, int rowNum) throws SQLException {
				Viagem viagem = new Viagem();
				Motorista motorista = new Motorista();
				Caminhao caminhao = new Caminhao();

				motorista.setId(rs.getLong("ID"));
				motorista.setNome(rs.getString("NOME"));
				motorista.setEndereco(rs.getString("ENDERECO"));
				motorista.setDataNascimento(rs.getString("DATANASCIMENTO"));
				caminhao.setId(rs.getLong("ID"));
				caminhao.setPlaca(rs.getString("PLACA"));
				caminhao.setModelo(rs.getString("MODELO"));
				caminhao.setAnoFabricacao(rs.getString("ANOFABRICACAO"));
				caminhao.setCapacidade(rs.getDouble("CAPACIDADE"));
				viagem.setId(rs.getLong("ID"));
				viagem.setDestino(rs.getString("DESTINO"));
				viagem.setTipoCarga(rs.getString("TIPO_CARGA"));
				viagem.setMotorista(motorista);
				viagem.setCaminhao(caminhao);

				return viagem;

			}
		}, new Object[] { configPagina.getPrimeiroElemento(), configPagina.getTamanho() });
	}
}
