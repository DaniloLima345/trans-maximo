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

import br.com.transmaximo.model.Documento;
import br.com.transmaximo.model.Motorista;
import br.com.transmaximo.paginacao.ConfigPagina;

@Component // Spring usa para gerenciar o objeto
public class DocumentoDAO extends DataAccessObject<Documento> {

	@Override
	public Documento salvar(Documento documento) {

		SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).usingGeneratedKeyColumns("ID")
				.withTableName("DOCUMENTOS");

		System.out.println(documento.getMotorista().getId());
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("TIPODOCUMENTO", documento.getTipoDocumento());
		parameters.put("DATAVENCIMENTO", documento.getDataVencimento());
		parameters.put("ID_MOTORISTA", documento.getMotorista().getId());

		return buscarPorId(insert.executeAndReturnKey(parameters).longValue()).get();
	}

	@Override
	public Optional<Documento> buscarPorId(Long id) {
		String sql = "SELECT * FROM DOCUMENTOS D INNER JOIN MOTORISTA M WHERE D.ID = ? AND D.ID_MOTORISTA = M.ID";

		return jdbcTemplate.queryForObject(sql, new RowMapper<Optional<Documento>>() {
			public Optional<Documento> mapRow(ResultSet rs, int numRow) throws SQLException {
				Documento documento = new Documento();
				Motorista motorista = new Motorista();
				
				motorista.setId(rs.getLong("ID_MOTORISTA"));
				motorista.setNome(rs.getString("NOME"));
				motorista.setEndereco(rs.getString("ENDERECO"));
				motorista.setDataNascimento(rs.getString("DATANASCIMENTO"));
				documento.setId(rs.getLong("ID"));
				documento.setTipoDocumento(rs.getString("TIPODOCUMENTO"));
				documento.setDataVencimento(rs.getString("DATAVENCIMENTO"));
				documento.setMotorista(motorista);
				
				return Optional.of(documento);
			}
		}, new Object[] { id });
	}

	@Override
	public void atualizar(Documento documento, Long id) {
		StringBuilder sqlBuilder = new StringBuilder();

		sqlBuilder.append("UPDATE DOCUMENTO SET ");

		if (documento.getTipoDocumento() != null)
			sqlBuilder.append("TIPODOCUMENTO = '" + documento.getTipoDocumento() + "'");
		if (documento.getDataVencimento() != null)
			sqlBuilder.append("DATAVENCIMENTO = '" + documento.getDataVencimento() + "'");
		if (documento.getMotorista() != null)
			sqlBuilder.append("ID_MOTORISTA = " + documento.getMotorista());

		String sql = sqlBuilder.toString();

		jdbcTemplate.update(sql);
	}

	@Override
	public void deletar(Long id) {
		String sql = "DELETE FROM DOCUMENTOS WHERE ID = ?";

		jdbcTemplate.update(sql, new Object[] { id });
	}

	@Override
	public List<Documento> listarTodos(ConfigPagina configPagina) {
		String sql = "SELECT * FROM DOCUMENTOS LIMIT ?, ?";

		return jdbcTemplate.query(sql, new RowMapper<Documento>() {
			@Override
			public Documento mapRow(ResultSet rs, int rowNum) throws SQLException {
				Documento documento = new Documento();
				Motorista motorista = new Motorista();

				motorista.setId(rs.getLong("ID_MOTORISTA"));
				motorista.setNome(rs.getString("NOME"));
				motorista.setEndereco(rs.getString("ENDERECO"));
				motorista.setDataNascimento(rs.getString("DATANASCIMENTO"));
				documento.setTipoDocumento(rs.getString("TIPODOCUMENTO"));
				documento.setDataVencimento(rs.getString("DATAVENCIMENTO"));
				documento.setMotorista(motorista);

				return documento;
			}
		}, new Object[] { configPagina.getPrimeiroElemento(), configPagina.getTamanho() });
	}
}
