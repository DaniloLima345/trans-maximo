package br.com.transmaximo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import br.com.transmaximo.model.Caminhao;

@Component
public class CaminhaoDAO {
	
	private Connection connection;
	
	public CaminhaoDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void salvar(Caminhao caminhao) throws SQLException {
		String sql = "INSERT INTO CAMINHAO(PLACA, MODELO, ANOFABRICACAO, CAPACIDADE) VALUES (?, ?, ?, ?)";
		
		try(PreparedStatement pstm = connection.prepareStatement(sql)){
			pstm.setString(1, caminhao.getPlaca());
			pstm.setString(2, caminhao.getModelo());
			pstm.setString(3, caminhao.getAnoFabricacao());
			pstm.setDouble(4, caminhao.getCapacidade());
			
			pstm.execute();
		}
	}
	
//	public static void main(String[] args) throws SQLException {
//		Caminhao caminhao = new Caminhao();
//		
//		caminhao.setPlaca("1234");
//		caminhao.setModelo("112 Scania");
//		caminhao.setAnoFabricacao("2012");
//		caminhao.setCapacidade(15000.0);
//		
//		ConnectionFactory con = new ConnectionFactory();
//		
//		CaminhaoDAO caminhaoDAO = new CaminhaoDAO(con.recuperarDados());
//		
//		caminhaoDAO.salvar(caminhao);
//	}

}
