package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Conexao;

/**
 * 
 * @author miguel rosa
 *
 */

public class Album {
	
	private Long id;
	private String nomeArtista;
	private String nomeAlbum;
	private int anoLancamento;
	
	public Album () {
		this.nomeAlbum = "";
	}
	
	/**
	 * 
	 * @param id id do album
	 * @param nomeArtista nome do Artista
	 * @param nomeAlbum nome do Album
	 * @param anoLancamento ano de Lancamento
	 */
	
	public Album(Long id, String nomeArtista, String nomeAlbum, int anoLancamento) {
		super();
		this.id = id;
		this.nomeArtista = nomeArtista;
		this.nomeAlbum = nomeAlbum;
		this.anoLancamento = anoLancamento;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeArtista() {
		return nomeArtista;
	}
	public void setNomeArtista(String nomeArtista) {
		this.nomeArtista = nomeArtista;
	}
	public String getNomeAlbum() {
		return nomeAlbum;
	}
	public void setNomeAlbum(String nomeAlbum) {
		this.nomeAlbum = nomeAlbum;
	}
	public int getAnoLancamento() {
		return anoLancamento;
	}
	public void setAnoLancamento(int anoLancamento) {
		this.anoLancamento = anoLancamento;
	}
	
	/**
	 * 
	 * @param nomeArtista nome do Artista
	 * @param nomeAlbum nome do Album
	 * @param anoLancamento ano de lancamento
	 * @return boolean
	 */


	public boolean cadastrarAlbum(String nomeArtista, String nomeAlbum, int anoLancamento) {
		
		// Define a conexão
		Connection conexao = null;
		
		try {
			conexao = Conexao.conectaBanco();
			
			// Define a consulta
			String sql = "insert into album set nomeArtista=?, nomeAlbum=?, anoLancamento=?;";
			
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			// Define os parâmetros da consulta
			ps.setString(1, nomeArtista); // Substitui o primeiro parâmetro da consulta 
			ps.setString(2, nomeAlbum); // Substitui o segundo parâmetro da consulta 
			ps.setInt(3, anoLancamento); // Substitui o terceiro parâmetro da consulta 
			
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("Não foi feito o cadastro!");
				return false;
			}
			System.out.println("Cadastro realizado!");
			return true;
		} catch (SQLException erro) {
			System.out.println("Erro ao cadastrar álbum: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}
	
	/**
	 * 
	 * @param nomeAlbum nome do Album
	 * @return boolean
	 */


	public boolean consultarAlbum(String nomeAlbum) {
		
		// Define a conexão
		Connection conexao = null;
		
		try {
			conexao = Conexao.conectaBanco();
			
			// Define a consulta pelo nome do álbum, pois não se repete
			String sql = "select * from album where nomeAlbum=? ";
			
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			// Define os parâmetros da consulta
			ps.setString(1, nomeAlbum); // Substitui o primeiro parâmetro da consulta 


			// Executa a consulta, resultando em um objeto da classe ResultSet
			ResultSet rs = ps.executeQuery();
			
			if (!rs.isBeforeFirst()) { // Verifica se não está antes do primeiro registro
				return false; // Album não cadastrado
			} else {
				// Efetua a leitura do registro da tabela
				while (rs.next()) {
					this.nomeArtista = rs.getString("nomeArtista");
					this.nomeAlbum= rs.getString("nomeAlbum");
					this.anoLancamento = rs.getInt("anoLancamento");
				}
				return true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar álbum: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	/**
	 * 
	 * @param nomeArtista nome do Artista
	 * @param nomeAlbum nome do Album
	 * @param anoLancamento ano de lancamento
	 * @return boolean
	 */


	public boolean atualizarAlbum(String nomeArtista, String nomeAlbum, int anoLancamento) {
		if (!consultarAlbum(nomeAlbum))
			return false;
		else {
			
			// Define a conexão
			Connection conexao = null;
			try {
				
				// Define a conexão
				conexao = Conexao.conectaBanco();
				
				// Define a consulta
				String sql = "update album set nomeArtista=?, nomeAlbum=?, anoLancamento=? where nomeAlbum=? ";
				
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);
				
				// Define os parâmetros da atualização
				ps.setString(1, nomeArtista); // Substitui o primeiro parâmetro da consulta 
				ps.setString(2, nomeAlbum); // Substitui o segundo parâmetro da consulta 
				ps.setInt(3, anoLancamento); // Substitui o terceiro parâmetro da consulta 
				ps.setString(4, nomeAlbum); // Substitui o quarto parâmetro da consulta 
				
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi feita a atualização.");
				else
					System.out.println("Atualização realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao atualizar álbum: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}
	
	/**
	 * 
	 * @param nomeArtista nome do Artista
	 * @param nomeAlbum nome do Album		
	 * @param anoLancamento ano de lancamento	
	 * @return boolean
	 */
	
	public boolean excluirAlbum(String nomeArtista, String nomeAlbum, int anoLancamento) {
		if (!consultarAlbum(nomeAlbum))
			return false;
		else {
			
			// Define a conexão
			Connection conexao = null;
			try {
				
				// Define a conexão
				conexao = Conexao.conectaBanco();
				
				// Define a consulta
				String sql = "delete from album where nomeArtista=? and nomeAlbum=? and anoLancamento=?";
				
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);
				
				// Define os parâmetros da atualização
				ps.setString(1, nomeArtista); // Substitui o primeiro parâmetro da consulta 
				ps.setString(2, nomeAlbum); // Substitui o segundo parâmetro da consulta 
				ps.setInt(3, anoLancamento); // Substitui o terceiro parâmetro da consulta 
				
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi feita a exclusão.");
				else
					System.out.println("Exclusão realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao deletar álbum: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}	
}

	
	
	
	

