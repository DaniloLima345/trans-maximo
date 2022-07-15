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

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired // Para o spring injetar automaticamente
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

//	public Documento buscarPorNomeMotorista(Long id) {
//		String sql = "SELECT D.ID, M.ID AS ID_MOTORISTA, M.NOME, M.DATANASCIMENTO FROM DOCUMENTOS D INNER JOIN MOTORISTA M WHERE D.ID_MOTORISTA = M.ID AND M.ID = ?";
//
//		return jdbcTemplate.queryForObject(sql, new RowMapper<Documento>() {
//			public Documento mapRow(ResultSet rs, int numRow) throws SQLException {
//				Documento documento = new Documento();
//				Motorista motorista = new Motorista();
//				documento.setId(rs.getLong("ID"));
//				motorista.setId(rs.getLong("ID"));
//				motorista.setNome(rs.getString("NOME"));
//				motorista.setDataNascimento(rs.getString("DATANASCIMENTO"));
//
//				return documento;
//			}
//		}, new Object[] { id });
//	}

}
