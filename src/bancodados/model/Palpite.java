package bancodados.model;

import java.util.Arrays;

import bancodados.dao.SorteioDao;

public class Palpite {
    private int id_palpite;
    private int[] palpite;
    private int id_vendedor;
    private int id_concorrente;
    private int id_sorteio;
    private int count;

    /**
	 * @param palpite
     * @param id_vendedor TODO
     * @param id_concorrente
     * @param id_sorteio 
     * @param id_palpite
	 */
	public Palpite(int[] palpite, int id_vendedor, int id_concorrente, int id_sorteio) {
		super();
    	//this.palpite = new int[5];
		this.palpite = palpite;
		this.id_vendedor = id_vendedor;
		this.id_concorrente = id_concorrente;
		this.id_sorteio = id_sorteio;
	}

    public Palpite() {
		// TODO Auto-generated constructor stub
    	this.palpite = new int[5];
    	this.count = 0;
	}

	public Palpite(int[] palpite) {
		super();
		this.palpite = palpite;
	}

	public int getId_palpite() {
		return id_palpite;
	}

	public void setId_palpite(int id_palpite) {
		this.id_palpite = id_palpite;
	}

	public int getPalpite(int pos) {
		return palpite[pos];
	}

	public void setPalpite(int palpite) {
		if (count < 5 /*&& this.palpite[count] == 0*/) {
			this.palpite[count] = palpite;
			count++;
		}
	}

	public void setPalpite(int palpite, int pos) {
		this.palpite[pos] = palpite;
	}

	public int getId_vendedor() {
		return id_vendedor;
	}

	public void setId_vendedor(int id_vendedor) {
		this.id_vendedor = id_vendedor;
	}

	public int getId_concorrente() {
		return id_concorrente;
	}

	public void setId_concorrente(int id_concorrente) {
		this.id_concorrente = id_concorrente;
	}

	/**
	 * @return the id_sorteio
	 */
	public int getId_sorteio() {
		return id_sorteio;
	}

	/**
	 * @param id_sorteio the id_sorteio to set
	 */
	public void setId_sorteio(int id_sorteio) {
		this.id_sorteio = id_sorteio;
	}

	@Override
	public String toString() {
		return "Palpite [id_palpite=" + id_palpite + ", palpite="
				+ Arrays.toString(palpite) + ", id_vendedor=" + id_vendedor
				+ ", id_concorrente=" + id_concorrente + ", id_sorteio="
				+ id_sorteio + ", count=" + count + "]";
	}

	public int[] getPalpite() {
		return this.palpite;
	}

	public void setPalpite(int[] palpite) {
		this.palpite = palpite;
	}
}