package formulario;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import documentos.Planilha;
import bancodados.BancoDados;
import bancodados.dao.LoteriaDao;
import bancodados.dao.NomeDao;
import bancodados.dao.PalpiteDao;
import bancodados.dao.SorteioDao;
import bancodados.dao.VendaDao;
import bancodados.model.Loteria;
import bancodados.model.Nome;
import bancodados.model.Palpite;
import bancodados.model.Sorteio;
import bancodados.model.Venda;

public class Formulario extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel labelTitle, lbVenda, lbNome, lbReferencia, lbEndereco,
			lbFone, lbEmail, lblData, lblauthor;
	private JTextField tfNome, tfReferencia, tfEndereco, tfFone, tfEmail;
	private JSpinner spPalpite[], spPalpite01, spPalpite02, spPalpite03,
			spPalpite04, spPalpite05;
	private JSpinner spSequencia[], spSorteio01, spSorteio02, spSorteio03,
			spSorteio04, spSorteio05;
	private JButton botaoRegistrar, botaoAcertos, botaoLoteria, botaoNomes,
			botaoPalpites, botaoSorteios, botaoVendas, botaoNovoConcurso,
			botaoNovoSorteio, botaoSortearConcurso, botaoSortearDezenas;;
	private JTextField tfVenda, tfData;

	public Nome nome;
	public Venda venda;
	public Palpite palpite;
	private JPasswordField passwordField;

	public Formulario() {
		super("Área de Cadastros");
		setTitle("Lotadinha");
		setSize(500, 437);
		setLocationRelativeTo(getContentPane());

		panel = new JPanel();
		panel.setLayout(null);

		labelTitle = new JLabel("Área de Cadastros");
		labelTitle.setFont(new Font("TimesRoman", Font.BOLD, 14));
		labelTitle.setBounds(180, 6, 300, 20);
		labelTitle.setForeground(Color.blue);
		panel.add(labelTitle);

		lbVenda = new JLabel("Vendedor(a): ", SwingConstants.RIGHT);
		lbVenda.setBounds(10, 32, 100, 20);
		panel.add(lbVenda);
		tfVenda = new JTextField();
		tfVenda.setToolTipText("Informe o nome completo");
		tfVenda.setBounds(111, 33, 300, 20);
		panel.add(tfVenda);

		lbNome = new JLabel("Nome: ", SwingConstants.RIGHT);
		lbNome.setBounds(10, 65, 100, 20);
		panel.add(lbNome);
		tfNome = new JTextField();
		tfNome.setToolTipText("Informe o nome completo");
		tfNome.setBounds(111, 66, 300, 20);
		panel.add(tfNome);

		lbReferencia = new JLabel("Referência: ", SwingConstants.RIGHT);
		lbReferencia.setBounds(10, 97, 100, 20);
		panel.add(lbReferencia);
		tfReferencia = new JTextField();
		tfReferencia.setSize(150, 20);
		tfReferencia.setLocation(111, 98);
		panel.add(tfReferencia);

		spPalpite = new JSpinner[5];
		spPalpite01 = new JSpinner();
		spPalpite01.setModel(new SpinnerNumberModel(1, 1, 76, 1));
		spPalpite01.setBounds(273, 86, 37, 32);
		panel.add(spPalpite01);
		spPalpite[0] = spPalpite01;

		spPalpite02 = new JSpinner();
		spPalpite02.setModel(new SpinnerNumberModel(2, 2, 77, 1));
		spPalpite02.setBounds(320, 86, 37, 32);
		panel.add(spPalpite02);
		spPalpite[1] = spPalpite02;

		spPalpite03 = new JSpinner();
		spPalpite03.setModel(new SpinnerNumberModel(3, 3, 78, 1));
		spPalpite03.setBounds(367, 86, 37, 32);
		panel.add(spPalpite03);
		spPalpite[2] = spPalpite03;

		spPalpite04 = new JSpinner();
		spPalpite04.setModel(new SpinnerNumberModel(4, 4, 79, 1));
		spPalpite04.setBounds(410, 86, 37, 32);
		panel.add(spPalpite04);
		spPalpite[3] = spPalpite04;

		spPalpite05 = new JSpinner();
		spPalpite05.setModel(new SpinnerNumberModel(5, 5, 80, 1));
		spPalpite05.setBounds(451, 86, 37, 32);
		panel.add(spPalpite05);
		spPalpite[4] = spPalpite05;

		lbEndereco = new JLabel("Endereço: ", SwingConstants.RIGHT);
		lbEndereco.setBounds(10, 127, 100, 20);
		panel.add(lbEndereco);
		tfEndereco = new JTextField();
		tfEndereco.setBounds(111, 128, 300, 20);
		panel.add(tfEndereco);

		lbFone = new JLabel("Telefone: ", SwingConstants.RIGHT);
		lbFone.setBounds(10, 157, 100, 20);
		panel.add(lbFone);
		tfFone = new JTextField();
		tfFone.setBounds(111, 158, 150, 20);
		panel.add(tfFone);

		lbEmail = new JLabel("e-mail: ", SwingConstants.RIGHT);
		lbEmail.setBounds(10, 187, 100, 20);
		panel.add(lbEmail);
		tfEmail = new JTextField();
		tfEmail.setBounds(111, 188, 300, 20);
		panel.add(tfEmail);

		botaoRegistrar = new JButton("Registrar");
		botaoRegistrar.setBounds(200, 217, 110, 20);
		botaoRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					botaoEnviarActionEvent(event);
				} catch (ParseException | IOException e) {
					JOptionPane.showMessageDialog(null, e, "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		panel.add(botaoRegistrar);
		getRootPane().setDefaultButton(botaoRegistrar);

		// Gerar planilha de vendedores
		botaoVendas = new JButton("Vendas");
		botaoVendas.setBounds(83, 242, 110, 20);
		botaoVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				botaoVendasActionEvent(event);
			}
		});
		panel.add(botaoVendas);

		// Gerar planinha de concorrentes
		botaoNomes = new JButton("Nomes");
		botaoNomes.setBounds(203, 242, 110, 20);
		botaoNomes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				botaoNomesActionEvent(event);
			}
		});
		panel.add(botaoNomes);

		// Gerar planilha da Loteria
		botaoLoteria = new JButton("Loteria");
		botaoLoteria.setBounds(28, 380, 110, 20);
		botaoLoteria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				botaoLoteriaActionEvent(event);
			}
		});
		panel.add(botaoLoteria);

		// Gerar planilha de palpites
		botaoPalpites = new JButton("Palpites");
		botaoPalpites.setBounds(325, 242, 110, 20);
		botaoPalpites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				botaoPalpitesActionEvent(event);
			}
		});
		panel.add(botaoPalpites);

		// Gerar planilha de sorteios
		botaoSorteios = new JButton("Sorteios");
		botaoSorteios.setBounds(203, 380, 110, 20);
		botaoSorteios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					botaoSorteiosActionEvent(event);
				} catch (IOException | ParseException e) {
					JOptionPane.showMessageDialog(null, e, "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		panel.add(botaoSorteios);

		// Gerar planilha de acertos
		botaoAcertos = new JButton("Acertos");
		botaoAcertos.setBounds(367, 317, 110, 20);
		botaoAcertos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				botaoAcertosActionEvent(event);
			}
		});
		panel.add(botaoAcertos);

		// Cadastro de nova sequência
		spSequencia = new JSpinner[5];
		spSorteio01 = new JSpinner();
		spSorteio01.setModel(new SpinnerNumberModel(1, 1, 76, 1));
		spSorteio01.setBounds(28, 274, 37, 31);
		panel.add(spSorteio01);
		spSequencia[0] = spSorteio01;

		spSorteio02 = new JSpinner();
		spSorteio02.setModel(new SpinnerNumberModel(2, 2, 77, 1));
		spSorteio02.setBounds(75, 274, 37, 31);
		panel.add(spSorteio02);
		spSequencia[1] = spSorteio02;

		spSorteio03 = new JSpinner();
		spSorteio03.setModel(new SpinnerNumberModel(3, 3, 78, 1));
		spSorteio03.setBounds(122, 274, 37, 31);
		panel.add(spSorteio03);
		spSequencia[2] = spSorteio03;

		spSorteio04 = new JSpinner();
		spSorteio04.setModel(new SpinnerNumberModel(4, 4, 79, 1));
		spSorteio04.setBounds(165, 274, 37, 31);
		panel.add(spSorteio04);
		spSequencia[3] = spSorteio04;

		spSorteio05 = new JSpinner();
		spSorteio05.setModel(new SpinnerNumberModel(5, 5, 80, 1));
		spSorteio05.setBounds(206, 274, 37, 31);
		panel.add(spSorteio05);
		spSequencia[4] = spSorteio05;

		lblData = new JLabel("Data");
		lblData.setBounds(267, 282, 70, 23);
		panel.add(lblData);
		tfData = new JTextField();
		tfData.setBounds(320, 280, 114, 25);
		panel.add(tfData);
		tfData.setColumns(10);

		// Cadastro de novo concurso da Loteria
		botaoNovoConcurso = new JButton("Novo concurso");
		botaoNovoConcurso.setBounds(28, 317, 138, 20);
		botaoNovoConcurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				botaoNovoConcursoActionEvent(event);
			}
		});
		panel.add(botaoNovoConcurso);

		// Cadastro de novo sorteio
		botaoNovoSorteio = new JButton("Novo Sorteio");
		botaoNovoSorteio.setBounds(200, 317, 138, 20);
		botaoNovoSorteio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				botaoNovoSorteioActionEvent(event);
			}
		});
		panel.add(botaoNovoSorteio);

		// Sortear concurso da Loteria
		botaoSortearConcurso = new JButton("Sortear concurso");
		botaoSortearConcurso.setBounds(28, 343, 165, 25);
		botaoSortearConcurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				botaoSortearConcursoActionEvent(event);
			}
		});
		panel.add(botaoSortearConcurso);

		// Sortear dezenas da Loteria
		botaoSortearDezenas = new JButton("Sortear dezenas");
		botaoSortearDezenas.setBounds(200, 343, 167, 25);
		botaoSortearDezenas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				botaoSortearDezenasActionEvent(event);
			}
		});
		panel.add(botaoSortearDezenas);

		lblauthor = new JLabel("by Hugo Dionizio Santos");
		lblauthor.setFont(new Font("Dialog", Font.BOLD, 9));
		lblauthor.setBounds(349, 385, 139, 15);
		panel.add(lblauthor);

		getContentPane().add(panel);
	}

	protected void botaoEnviarActionEvent(ActionEvent event)
			throws ParseException, FileNotFoundException, IOException {
		// BancoDados bd = new BancoDados();

		String aviso = "";
		boolean referencias = false;

		// Validação de vendedor
		venda = new Venda();
		if (tfVenda.getText().length() > 0) {
			venda.setNomeVendedor(tfVenda.getText());
		} else
			aviso += "Informe o vendedor.\n";

		// Validação de concorrente
		nome = new Nome();
		if (tfNome.getText().length() > 0) {
			nome.setNomeConcorrente(tfNome.getText());
		} else
			aviso += "Informe o concorrente.\n";

		// Validação de contato do Concorrente
		if (tfReferencia.getText().length() > 0) {
			nome.setReferencia(tfReferencia.getText());
			referencias = true;
		}

		if (tfEndereco.getText().length() > 0) {
			nome.setEndereco(tfEndereco.getText());
			referencias = true;
		}
		
		if (tfFone.getText().length() > 0) {
			nome.setFone(tfFone.getText());
			referencias = true;
		}
		
		if (tfEmail.getText().length() > 0) {
			nome.setEmail(tfEmail.getText());
			referencias = true;
		}
		
		if (!referencias)
			aviso+="Informe ao menos um contato.";
		
		// Gravação de palpite
		if (aviso.length() == 0) {
			// Validação de palpite
			palpite = new Palpite();
			SpinnerNumberModel spTmp;
			for (int i = 0; i < 5; i++) {
				spTmp = (SpinnerNumberModel) spPalpite[i].getModel();
				if (spTmp.getNumber() != null) {
					palpite.setPalpite(spTmp.getNumber().intValue(), i);
				}
			}

			String result = PalpiteDao.cadastrarPalpite(venda, nome, palpite);
			JOptionPane.showMessageDialog(null, result, "Cadastro",
					JOptionPane.INFORMATION_MESSAGE);

		} else
			JOptionPane.showMessageDialog(null, aviso, "Aviso",
					JOptionPane.INFORMATION_MESSAGE);
	}

	protected void botaoVendasActionEvent(ActionEvent event) {
		try {
			Planilha.criarVendas();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e, "Aviso",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException | ParseException e) {
			JOptionPane.showMessageDialog(null, e, "Aviso",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void botaoNomesActionEvent(ActionEvent event) {
		try {
			Planilha.criarNomes();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e, "Aviso",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException | ParseException e) {
			JOptionPane.showMessageDialog(null, e, "Aviso",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void botaoLoteriaActionEvent(ActionEvent event) {
		try {
			Planilha.criarLoteria();
		} catch (IOException | ParseException e) {
			JOptionPane.showMessageDialog(null, e, "Aviso",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void botaoSorteiosActionEvent(ActionEvent event)
			throws FileNotFoundException, IOException, ParseException {
		Planilha.criarSorteios();
	}

	protected void botaoPalpitesActionEvent(ActionEvent event) {
		try {
			Planilha.criarPalpites();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e, "Aviso",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException | ParseException e) {
			JOptionPane.showMessageDialog(null, e, "Aviso",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void botaoAcertosActionEvent(ActionEvent event) {
		try {
			Planilha.criarAcertos();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e, "Aviso",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException | ParseException e) {
			JOptionPane.showMessageDialog(null, e, "Aviso",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void botaoNovoConcursoActionEvent(ActionEvent event) {
		// Validação de novo concurso
		Loteria concurso = new Loteria();

		SpinnerNumberModel spTmp;
		for (int i = 0; i < 5; i++) {
			spTmp = (SpinnerNumberModel) spSequencia[i].getModel();
			if (spTmp.getNumber() != null) {
				concurso.setDezenaConcurso(spTmp.getNumber().intValue(), i);
			}
		}
		String result = (new LoteriaDao()).cadastrarConcurso(concurso);
		JOptionPane.showMessageDialog(null, result, "Concurso",
				JOptionPane.INFORMATION_MESSAGE);
	}

	protected void botaoNovoSorteioActionEvent(ActionEvent event) {
		// Validação de novo sorteio
		Sorteio sorteio = new Sorteio();

		String aviso = "";
		// Validação de data
		if (tfData.getText().length() > 0) {
			sorteio.setData(tfData.getText());
		} else
			aviso += "Informe a data.\n";

		if (aviso.length() == 0) {
			SpinnerNumberModel spTmp;
			for (int i = 0; i < 5; i++) {
				spTmp = (SpinnerNumberModel) spSequencia[i].getModel();
				if (spTmp.getNumber() != null) {
					sorteio.setSorteio(spTmp.getNumber().intValue(), i);
				}
			}
			String result = (new SorteioDao()).cadastrarSorteio(sorteio);
			JOptionPane.showMessageDialog(null, result, "Sorteio",
					JOptionPane.INFORMATION_MESSAGE);
		} else
			JOptionPane.showMessageDialog(null, aviso, "Aviso",
					JOptionPane.INFORMATION_MESSAGE);
	}

	protected void botaoSortearConcursoActionEvent(ActionEvent event) {
		String result = SorteioDao.sortearConcurso();
		JOptionPane.showMessageDialog(null, result, "Sorteando concurso",
				JOptionPane.INFORMATION_MESSAGE);
	}

	protected void botaoSortearDezenasActionEvent(ActionEvent event) {
		String result = SorteioDao.sortearDezenas();
		JOptionPane.showMessageDialog(null, result, "Sorteando dezenas",
				JOptionPane.INFORMATION_MESSAGE);
	}
}