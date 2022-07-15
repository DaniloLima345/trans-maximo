package br.com.transmaximo.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import br.com.transmaximo.model.Documento;

@Component
public class DocumentoDAO {

//	@Autowired
//	private JdbcTemplate jdbcTemplate;
	@Autowired
	private DataSource dataSource;

	public int salvar(Documento documento) {

		SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("DOCUMENTOS");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("TIPODOCUMENTO", documento.getTipoDocumento());
		parameters.put("DATAVENCIMENTO", documento.getDataVencimento());

		return insert.execute(parameters);
	}

}
