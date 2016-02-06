package bancodados.model;

import java.util.Arrays;

public class Loteria {
	private int id_concurso;
	private int[] dezenaConcurso;
	/**
	 * @param id_concurso
	 * @param dezenaConcurso
	 */
	public Loteria(int[] concurso) {
		super();
		this.dezenaConcurso = concurso;
	}

	public Loteria() {
		this.dezenaConcurso = new int[5];
	}

	public int[] getDezenaConcurso() {
		return dezenaConcurso;
	}

	public int getConcurso(int pos) {
		return dezenaConcurso[pos];
	}
	
	public void setDezenaConcurso(int[] dezenaConcurso) {
		this.dezenaConcurso = dezenaConcurso;
	}

	public void setDezenaConcurso(int dezenaConcurso, int pos) {
		this.dezenaConcurso[pos] = dezenaConcurso;
	}

	public void setId_concurso(int id_concurso) {
		this.id_concurso = id_concurso;
	}

	public int getId_concurso() {
		return id_concurso;
	}

	@Override
	public String toString() {
		return "Loteria [id_concurso=" + id_concurso + ", dezenaConcurso="
				+ Arrays.toString(dezenaConcurso) + "]";
	}
}