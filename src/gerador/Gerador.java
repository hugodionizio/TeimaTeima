package gerador;

import java.util.Random;

public class Gerador {
	
	private int[] v;
	private int tam;

	public void embaralhar()
	{
		int[] u = new int[tam];
		int i, j, k;
		boolean[] aux = new boolean[tam];
		boolean ok;
		
		Random gerador = new Random();

		//System.out.println("Embaralhando vetor de tamanho " + tam + "...\n");

		for (k = 0; k < tam; ++k) {
			ok = false;
			do {
				j = gerador.nextInt(tam);
				for (int l = 0; l < tam; l++) {
				//	System.out.print(aux[l] + " ");					
				}
				//System.out.println(" " + v[j]);
				if (aux[j] == false) {
					u[k] = v[j];
					aux[j] = true;
					ok = true;
				}
			} while(ok == false);
		}

		for(i = 0; i < tam; i++) v[i] = u[i];
	}
	
	public void setTam(int tam) {
		this.tam = tam;
		v = new int[tam];
	}
	
	public void setGerador(int pos, int value) {
		v[pos] = value;
	}
	
	public int getGerador(int pos) {
		return v[pos];
	}
	
	public void print() {
		System.out.println("\nVetor resultante:");
		for (int i = 0; i < tam; i++) {
			System.out.print(v[i] + " ");
		}
		System.out.println("");
	}
	
	public Gerador() {
		System.out.println("Inicializando vetor...");
		
		tam = 10;
		v = new int[tam];
		for(int i = 0; i < tam; i++) v[i] = i;
	}

	public Gerador(int tam) {
		System.out.println("Inicializando vetor...");
		
		this.tam = tam;
		v = new int[tam];
		for(int i = 0; i < tam; i++) v[i] = i;
	}
	
	public Gerador(int tam, int deslocamento) {
		System.out.println("Inicializando vetor...");
		
		this.tam = tam;
		v = new int[tam];
		for(int i = 0; i < tam; i++) v[i] = i + deslocamento;
	}
}