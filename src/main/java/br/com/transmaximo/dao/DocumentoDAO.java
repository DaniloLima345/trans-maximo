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

import br.com.transmaximo.model.Documento;

@Component // Spring usa para gerenciar o objeto
public class DocumentoDAO {

	@Autowired // Para o spring injetar automaticamente
	private JdbcTemplate jdbcTemplate;
	@Autowired 
	private DataSource dataSource;

	public int salvar(Documento documento) {

		SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("DOCUMENTOS");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("TIPODOCUMENTO", documento.getTipoDocumento());
		parameters.put("DATAVENCIMENTO", documento.getDataVencimento());
		parameters.put("ID_MOTORISTA", documento.getIdMotorista());

		return insert.execute(parameters);
	}

	public Documento buscarPorId(Long id) {
		String sql = "SELECT * FROM DOCUMENTOS WHERE ID = ?";

		return jdbcTemplate.queryForObject(sql, new RowMapper<Documento>() {
			public Documento mapRow(ResultSet rs, int numRow) throws SQLException {
				Documento documento = new Documento();
				documento.setId(rs.getLong("ID"));
				documento.setTipoDocumento(rs.getString("TIPODOCUMENTO"));
				documento.setDataVencimento(rs.getString("DATAVENCIMENTO"));
				documento.setIdMotorista(rs.getLong("ID_MOTORISTA"));

				return documento;
			}
		}, new Object[] { id });
	}

	public void atualizar(Documento documento, Long id) throws SQLException {
		String sql = "UPDATE DOCUMENTOS SET TIPODOCUMENTO = ?, DATAVENCIMENTO = ? WHERE ID = ?";

		jdbcTemplate.update(sql, new Object[] { documento.getTipoDocumento(), documento.getDataVencimento(), id });
	}

	public void deletar(Long id) throws SQLException {
		String sql = "DELETE FROM DOCUMENTOS WHERE ID = ?";

		jdbcTemplate.update(sql, new Object[] { id });
	}
}
