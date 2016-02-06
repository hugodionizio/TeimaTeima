/**
 * 
 */
package teima;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import bancodados.BancoDados;
import bancodados.dao.AcertoDao;
import bancodados.dao.PalpiteDao;
import bancodados.dao.SorteioDao;
import bancodados.model.Acerto;
import bancodados.model.Nome;
import bancodados.model.Palpite;
import bancodados.model.Sorteio;
import bancodados.model.Venda;
import formulario.Formulario;
import gerador.Gerador;

/**
 * @author hugo
 *
 */
public class Main {

	public void Pio() {
		JOptionPane.showMessageDialog(null, "Acertou no pio!", "Lotadinha",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void Duque() {
		JOptionPane.showMessageDialog(null, "Acertou no duque!", "Lotadinha",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void Terno() {
		JOptionPane.showMessageDialog(null, "Acertou no terno!", "Lotadinha",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void Quadra() {
		JOptionPane.showMessageDialog(null, "Acertou na quadra!!", "Lotadinha",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void Quina() {
		JOptionPane.showMessageDialog(null, "Acertou na quina!!!", "Lotadinha",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void SemPremio() {
		JOptionPane.showMessageDialog(null, "Não teve sorte.", "Lotadinha",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * @param args
	 * @throws ParseException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws NullPointerException,
			ParseException, FileNotFoundException, IOException {
		System.out.println("Olá! Este é o Lotadinha!");
		
		
		// new BancoDados();

		/*PalpiteDao.cadastrarPalpite(new Venda("Sicrano"), new Nome("Sicrano",
				"Beltrano", "Rua dos Sicranos", "(84) 9877-6543",
				"sicrano@silva.com"), new Palpite(new int[] { 1, 2, 3, 4, 6 }));*/
		
/*		Palpite novoPalpite = new Palpite(new int[]{9, 15, 23, 31, 54}, 4, 5, 11);
		novoPalpite.setId_palpite(101);
		(new PalpiteDao()).update(novoPalpite);
				
		novoPalpite.setPalpite(25, 2);
		novoPalpite.setId_palpite(102);
		(new PalpiteDao()).update(novoPalpite);
		
		novoPalpite.setPalpite(34, 3);
		novoPalpite.setId_palpite(201);
		(new PalpiteDao()).update(novoPalpite);
*/

		String senha = "";
		JPasswordField jpassword = new JPasswordField();
		try {
			do {
				if (JOptionPane.showConfirmDialog(null, jpassword,
						"Lotadinha - Senha admin (123456)",
						JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
					System.out.println(new String(jpassword.getPassword()));
					senha = new String(jpassword.getPassword());
					System.out.println("Senha = " + senha);
				} else
					break;

				if (senha.equalsIgnoreCase("123456")) {
					Formulario formulario = new Formulario();
					formulario.setVisible(true);
					formulario.setResizable(false);
					formulario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					break;
				}
			} while (!senha.equalsIgnoreCase("123456"));
		} catch (NullPointerException e) {
			System.out.println("Lotadinha finalizado");
		}
	}

	public boolean validarPalpite(String sequencia) {
		try {
			int numPalpites = 0;
			String num;
			int posSeq = 0;
			int[] palpites = new int[5];
			if (sequencia.length() > 0) {
				for (numPalpites = 0; numPalpites < 5; numPalpites++) {
					num = "";
					while (posSeq < sequencia.length()
							&& sequencia.charAt(posSeq) != ' ') {
						num = num + sequencia.charAt(posSeq);
						posSeq++;
					}
					palpites[numPalpites] = Integer.parseInt(num);
					if (posSeq == sequencia.length())
						break;
					posSeq++;
				}
			}

			if (posSeq > 0)
				numPalpites++;

			if (numPalpites < 5) {
				while (numPalpites < 5) {
					sequencia = JOptionPane
							.showInputDialog(
									null,
									"Você digitou apenas "
											+ numPalpites
											+ " palpites.\n"
											+ "Digite uma sequência com 5 palpites, de 1 a 80: ",
									"Lotadinha",
									JOptionPane.INFORMATION_MESSAGE);

					posSeq = 0;
					if (sequencia.length() > 0) {
						numPalpites = 1;
						for (numPalpites = 0; numPalpites < 5; numPalpites++) {
							num = "";
							while (posSeq < sequencia.length()
									&& sequencia.charAt(posSeq) != ' ') {
								num = num + sequencia.charAt(posSeq);
								posSeq++;
							}
							palpites[numPalpites] = Integer.parseInt(num);
							if (posSeq == sequencia.length())
								break;
							posSeq++;
						}
					}
					if (posSeq > 0)
						numPalpites++;
				}
			}

			JOptionPane.showMessageDialog(null, "A palavra \"" + sequencia
					+ "\" tem " + numPalpites + " palpites", "Lotadinha",
					JOptionPane.INFORMATION_MESSAGE);

			Gerador ger3 = new Gerador(80, 1);
			ger3.embaralhar();
			ger3.print();

			int[] sorteio = new int[5];
			for (int i = 0; i < 5; i++) {
				sorteio[i] = ger3.getGerador(i);
			}

			Random sorteioEscolhido = new Random();
			int[][] sorteios = null;
			int tamanho = sorteios.length;
			System.out.println("\nO número de sorteios é : " + tamanho);
			int temp = sorteioEscolhido.nextInt(tamanho);
			System.out.println("\nO sorteio escolhido foi: " + temp);
			sorteio = sorteios[temp];

			int numAcertos = 0;
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (sorteio[i] == palpites[j])
						numAcertos++;
				}
			}

			switch (numAcertos) {
			case 1:
				Pio();
				break;
			case 2:
				Duque();
				break;
			case 3:
				Terno();
				break;
			case 4:
				Quadra();
				break;
			case 5:
				Quina();
				break;
			default:
				SemPremio();
			}
		} catch (NullPointerException e) {
		}
		return false;
	}
}
