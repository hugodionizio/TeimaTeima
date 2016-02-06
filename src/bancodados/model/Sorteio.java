package bancodados.model;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import bancodados.dao.LoteriaDao;

public class Sorteio {
	private int id_sorteio;
	private int[] sorteio;
	private String data;

	/**
	 * @param id_sorteio
	 * @param sorteio
	 * @param data
	 */
	public Sorteio(int[] sorteio, String data) {
		super();
		//this.id_sorteio = id_sorteio;
		//this.sorteio = new int[5];
		this.sorteio = sorteio;
		this.data = data;
	}

	public Sorteio(boolean gerador, String data) {
		sortear(gerador, data);
	}
	
	public Sorteio(boolean gerador) {
		Date dataC = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");
		String dataS = ft.format(dataC);
		sortear(gerador, dataS);
	}

	public Sorteio() {
		// TODO Auto-generated constructor stub
		this.sorteio = new int[5];
		
		Date dataC = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");
		this.data = ft.format(dataC);
	}
	
	public void sortear(boolean gerador, String data) {
		this.sorteio = new int[5];
		if (gerador) {
			LoteriaDao daoLoteria = new LoteriaDao();
	   		Random sorteioEscolhido = new Random();
	   		
    		int tamanho = daoLoteria.findNumConcursos();
    		System.out.println("\nO número de concursos é : " + tamanho);
    		int temp = sorteioEscolhido.nextInt(tamanho)+1;
    		System.out.println("\nO sorteio escolhido foi: " + temp);
    		for (int i = 0; i < 5; i++) {
        		sorteio[i] = daoLoteria.findDezenaConcurso(temp, i);
			}
    		
    		this.data = data;
		}
	}

	public int getId_sorteio() {
		return id_sorteio;
	}

	public void setId_sorteio(Integer id_sorteio) {
		this.id_sorteio = id_sorteio;
	}

	public int getSorteio(int pos) {
		return sorteio[pos];
	}

	public void setSorteio(int sorteio, int pos) {
		this.sorteio[pos] = sorteio;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Sorteio [id_sorteio=" + id_sorteio + ", sorteio="
				+ Arrays.toString(sorteio) + ", data=" + data + "]";
	}
}