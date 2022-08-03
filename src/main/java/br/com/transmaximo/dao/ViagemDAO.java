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
		parameters.put("ID_MOTORISTA", viagem.getIdMotorista());
		parameters.put("ID_CAMINHAO", viagem.getIdCaminhao());
		return buscarPorId(insert.executeAndReturnKey(parameters).longValue()).get();
	}

	@Override
	public Optional<Viagem> buscarPorId(Long id) {

		String sql = "SELECT * FROM VIAGEM WHERE ID = ?";

		return jdbcTemplate.queryForObject(sql, new RowMapper<Optional<Viagem>>() {
			@Override
			public Optional<Viagem> mapRow(ResultSet rs, int rowNum) throws SQLException {
				Viagem viagem = new Viagem();

				viagem.setId(rs.getLong("ID"));
				viagem.setDestino(rs.getString("DESTINO"));
				viagem.setTipoCarga(rs.getString("TIPO_CARGA"));
				viagem.setIdMotorista(rs.getLong("ID_MOTORISTA"));
				viagem.setIdCaminhao(rs.getLong("ID_CAMINHAO"));

				return Optional.of(viagem);
			}
		}, new Object[] { id });
	}

	@Override
	public void atualizar(Viagem Object, Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletar(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Viagem> listarTodos(ConfigPagina configPagina) {
		// TODO Auto-generated method stub
		return null;
	}

}
