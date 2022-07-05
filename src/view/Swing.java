package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import model.Album;
/**
 * 
 * @author miguel rosa
 *
 */

public class Swing {
	public static void main(String[] args) {
		/**
		 * Define a janela
		 */
		
		JFrame janela = new JFrame("Cadastro de album"); // Janela Normal
		janela.setResizable(true); // A janela poderá ter o tamanho ajustado
		janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		janela.setSize(600, 300); // Define tamanho da janela
		
		/**
		 *  Define o layout da janela
		 */
		Container caixa = janela.getContentPane();
		caixa.setLayout(null);
		
		/** 
		 * Define os labels dos campos
		 */
		JLabel labelArtista = new JLabel("Nome do Artista: ");
		JLabel labelAlbum = new JLabel("Nome do Album: ");
		JLabel labelLancamento = new JLabel("Ano de Lançamento: ");
		
		/**
		 *  Posiciona os labels na janela
		 */
		labelArtista.setBounds(50, 40, 150, 30); // coluna, linha, largura, tamanho
		labelAlbum.setBounds(50, 80, 150, 30); // coluna, linha, largura, tamanho
		labelLancamento.setBounds(50, 120, 150, 30); // coluna, linha, largura, tamanho
		
		/**
		 *  Define os input box
		 */
		JTextField jTextArtista = new JTextField();
		JTextField jTextAlbum = new JTextField();
		JTextField jTextLancamento = new JTextField();
		
		/**
		 *  Define se os campos estão habilitados ou não no início
		 */
		jTextArtista.setEnabled(true);
		jTextAlbum.setEnabled(true);
		jTextLancamento.setEnabled(true);
		
		/**
		 *  Posiciona os input box
		 */
		jTextArtista.setBounds(180, 40, 130, 20);
		jTextAlbum.setBounds(180, 80, 130, 20);
		jTextLancamento.setBounds(180, 120, 50, 20);
		
		/**
		 * Adiciona os rótulos e os input box na janela
		 */
		janela.add(labelArtista);
		janela.add(labelAlbum);
		janela.add(labelLancamento);
		janela.add(jTextArtista);
		janela.add(jTextAlbum);
		janela.add(jTextLancamento);
		
		
		/**
		 *  Define botões e a localização deles na janela
		 */
		JButton botaoGravar = new JButton("Gravar");
		botaoGravar.setBounds(50, 200, 100, 20); //coluna, linha, largura, tamanho
		janela.add(botaoGravar);
		
		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(150, 200, 100, 20); //coluna, linha, largura, tamanho
		janela.add(botaoConsultar);
		
		JButton botaoAtualizar = new JButton("Atualizar");
		botaoAtualizar.setBounds(250, 200, 100, 20); //coluna, linha, largura, tamanho
		janela.add(botaoAtualizar);
		
		
		JButton botaoExcluir = new JButton("Excluir");
		botaoExcluir.setBounds(350, 200, 100, 20); //coluna, linha, largura, tamanho
		janela.add(botaoExcluir);
		
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(450, 200, 100, 20); //coluna, linha, largura, tamanho
		janela.add(botaoLimpar);

		
		/**
		 *  Define objeto album para pesquisar no banco de dados
		 */
		Album album = new Album();
		
		/**
		 *  Define ações dos botões
		 */
		botaoGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeArtista = jTextArtista.getText();
				String nomeAlbum = jTextAlbum.getText();
				int anoLancamento = Integer.parseInt(jTextLancamento.getText());
				
				if (!album.consultarAlbum( nomeAlbum)) {
					if (!album.cadastrarAlbum(nomeArtista, nomeAlbum, anoLancamento))
						JOptionPane.showMessageDialog(janela, "Erro na inclusão do álbum.");
					else
						JOptionPane.showMessageDialog(janela, "Inclusão realizada!");
					
				} else if (!album.consultarAlbum(nomeAlbum)) {
					if (!album.atualizarAlbum(nomeArtista, nomeAlbum, anoLancamento))
						JOptionPane.showMessageDialog(janela, "Erro na atualização do álbum.");
					else
						JOptionPane.showMessageDialog(janela, "Alteração realizada!");
					
				} else  if (!album.consultarAlbum(nomeAlbum)) {
					if (!album.excluirAlbum(nomeArtista, nomeAlbum, anoLancamento))
						JOptionPane.showMessageDialog(janela, "Erro na exlusão do álbum.");
					else
						JOptionPane.showMessageDialog(janela, "Exclusão realizada!");
				}
				
				jTextArtista.setText(""); // Limpar campo
				jTextAlbum.setText(""); // Limpar campo
				jTextLancamento.setText(""); // Limpar campo
			}
		}
		);


		 botaoConsultar.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                // Pega os valores dos campos
	            	            	
	            	album.setNomeAlbum(jTextAlbum.getText());
	
	                // Chama o m�todo para consultar no banco de dados
	                album.consultarAlbum(jTextAlbum.getText());
	       
	                // Preenche os demais campos com os dados do banco de dados
	                jTextArtista.setText(album.getNomeArtista());
	                jTextAlbum.setText(album.getNomeAlbum());
	                jTextLancamento.setText (String.valueOf(album.getAnoLancamento()));
	                
	                // Após a consulta, o usuário pode editar ou excluir o registro
	                botaoExcluir.setEnabled(true);
	                botaoAtualizar.setEnabled(true);
	                
	                // Impede de ativar botões sem utilidade
	                botaoGravar.setEnabled(false);
	                botaoConsultar.setEnabled(false);
	            }
	        });
		
		
		botaoAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Confere se está aberta a consulta
					if (album.consultarAlbum(jTextAlbum.getText())) {
						
						botaoGravar.setEnabled(false);
						botaoConsultar.setEnabled(false);
						botaoAtualizar.setEnabled(true);
						
					 // Pega os valores dos campos
						album.setNomeArtista(jTextArtista.getText());
						album.setNomeAlbum(jTextAlbum.getText());
						album.setAnoLancamento(Integer.parseInt(jTextLancamento.getText()));
					
					
					// Chama o m�todo para alterar no banco de dados
					
						album.atualizarAlbum(jTextArtista.getText(), jTextAlbum.getText(), (Integer.parseInt(jTextLancamento.getText())));
						
					
					// Limpa os campos
						jTextArtista.setText(""); // Limpar campo
						jTextAlbum.setText(""); // Limpar campo
						jTextLancamento.setText(""); // Limpar campo
						
					} else {
						System.out.println("Preencha os campos do álbum corretamente.");
					}
						
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janela, "Preencha os campos do álbum corretamente!");
				}
			}
		});
		
		

		botaoExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String nomeArtista = jTextArtista.getText();
					String nomeAlbum = jTextAlbum.getText();
					int anoLancamento = Integer.parseInt(jTextLancamento.getText());
					
					botaoGravar.setEnabled(true);
					
					if (album.excluirAlbum(nomeArtista, nomeAlbum, anoLancamento)) {

					jTextArtista.setEnabled(false);
					jTextAlbum.setEnabled(false);
					jTextLancamento.setEnabled(false);
					jTextArtista.requestFocus();
					botaoExcluir.setEnabled(true);
					
					} else {
						System.out.println("Preencha os campos do álbum corretamente.");
					}
						
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janela, "Preencha os campos do álbum corretamente!");
				}
				
				jTextArtista.setText(""); // Limpar campo
				jTextAlbum.setText(""); // Limpar campo
				jTextLancamento.setText(""); // Limpar campo
			}
		});
		
		
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextArtista.setText(""); // Limpar campo
				jTextAlbum.setText(""); // Limpar campo
				jTextLancamento.setText(""); // Limpar campo
				jTextArtista.setEnabled(true);
				jTextAlbum.setEnabled(true);
				jTextLancamento.setEnabled(true);
				botaoConsultar.setEnabled(true);
				botaoGravar.setEnabled(true);
				jTextArtista.requestFocus(); // Colocar o foco em um campo
			}
		});
		
		/**
		 *  Apresenta a janela
		 */
		janela.setVisible(true); // Exibe a janela
		
		
		
	}
}