package bancodados.model;

import java.util.Arrays;

import bancodados.dao.AcertoDao;
import bancodados.dao.PalpiteDao;
import bancodados.dao.SorteioDao;

public class Acerto {
    private int id_acerto;
    private int jogo;
    private int numeroAcertos;
    private boolean[] acertos;
    private String tipoAcerto;
    private int id_concorrente;
    private int id_palpite;
    private int id_sorteio;
    
    public enum TipoAcerto {
    	PIO(1,"Pio"),
    	DUQ(2,"Duque"),
        TRN(3,"Terno"), 
		QDR(4,"Quadra"), 
		QUN(5,"Quina");

        private int indice;
        private String descricao;

        TipoAcerto(int indice, String descricao) {
            this.indice = indice;
            this.descricao = descricao;
        }

        public int getIndice() {
            return indice;
        }

        public String getDescricao() {
            return descricao;
        }
    }

    public Acerto(int jogo, int numeroAcertos, boolean[] acertos,
			String tipoAcerto, int id_concorrente, int id_palpite,
			int id_sorteio) {
		super();
		this.jogo = jogo;
		this.numeroAcertos = numeroAcertos;
		this.acertos = acertos;
		this.tipoAcerto = tipoAcerto;
		this.id_concorrente = id_concorrente;
		this.id_palpite = id_palpite;
		this.id_sorteio = id_sorteio;
	}

	/**
	 * @param numero
	 * @param tipoAcerto
	 * @param id_concorrente
	 * @param id_sorteio
	 */
	public Acerto(int id_palpite, int id_sorteio) {
		super();
		boolean acumulou;
		AcertoDao daoAcerto = new AcertoDao();
		PalpiteDao daoPalpite = new PalpiteDao();
		SorteioDao daoSorteio = new SorteioDao();
		
		jogo = daoAcerto.findUltimaAcumulacao();
		
		if(daoAcerto.tipo(jogo) == TipoAcerto.QUN.getIndice() &&
				daoAcerto.findInAcerto(jogo, "id_sorteio") == daoPalpite.findIdSorteio(id_palpite)) {
			acumulou = true;
		}
		else
			acumulou = false;
				
		int[] palpites = new int[5], sorteio = new int[5];
		for (int i = 0; i < 5; i++) {
			palpites[i] = daoPalpite.findDezena(id_palpite, i);
			sorteio[i] = daoSorteio.findDezena(id_sorteio, i);
		}
		
		acertos = new boolean[5];
		for (int i = 0; i < 5; i++) {
        	for (int j = 0; j < 5; j++) {
        		if (sorteio[i] == palpites[j] && sorteio[i] != 0 && palpites[j] != 0) {
        			System.out.println("sorteio ("+id_sorteio+"), dezena ("+i+") = "+sorteio[i]);
        			acertos[i] = true;
        			numeroAcertos++;
        			//this.acumulado = true;
        		}
        	}
        }
		
		numeroAcertos+=daoAcerto.findInAcerto(id_palpite, "numeroAcertos");

//		System.out.println("Dentro: "+toString());
		
		if (numeroAcertos > 0) {			
			switch (numeroAcertos) {
				case 1: tipoAcerto = "PIO"; break;
			   	case 2: tipoAcerto = "DUQUE"; break;
		    	case 3: tipoAcerto = "TERNO"; break;
		    	case 4: tipoAcerto = "QUADR"; break;
		    	case 5: tipoAcerto = "QUINA"; break;
			}
			if(acumulou) jogo++;
			this.id_concorrente = daoPalpite.findConcorrente(id_palpite);
			this.id_palpite = id_palpite;
			this.id_sorteio = id_sorteio;
		}
	}

	public Acerto() {
		// TODO Auto-generated constructor stub
	}
	
	public void acertos() {
		
	}

	public void setId_acerto(int id_acerto) {
		this.id_acerto = id_acerto;
	}

	public Integer getId_acerto() {
		return id_acerto;
	}
	
	public void setAcerto(boolean acerto, int pos) {
		this.acertos[pos] = acerto;
	}
	
	public boolean getAcerto(int pos) {
		return acertos[pos];
	}

	public void setId_acerto(Integer id_acerto) {
		this.id_acerto = id_acerto;
	}

	public int getJogo() {
		return jogo;
	}

	public void setJogo(int acumulacao) {
		this.jogo = acumulacao;
	}

	public int getNumero() {
		return numeroAcertos;
	}

	public void setNumero(int numero) {
		this.numeroAcertos = numero;
	}

	public boolean[] getAcertos() {
		return acertos;
	}

	public boolean getAcertos(int pos) {
		return acertos[pos];
	}
	
	public void setAcertos(boolean[] acertos) {
		this.acertos = acertos;
	}

	public String getTipoAcerto() {
		return tipoAcerto;
	}

	public void setTipo(String tipo) {
		this.tipoAcerto = tipo;
	}

	public int getId_concorrente() {
		return id_concorrente;
	}

	public void setId_concorrente(int id_concorrente) {
		this.id_concorrente = id_concorrente;
	}

	public int getId_palpite() {
		return id_palpite;
	}

	public void setId_palpite(int id_palpite) {
		this.id_palpite = id_palpite;
	}

	public int getId_sorteio() {
		return id_sorteio;
	}

	public void setId_sorteio(int id_sorteio) {
		this.id_sorteio = id_sorteio;
	}

	@Override
	public String toString() {
		return "Acerto [id_acerto=" + id_acerto + ", jogo=" + jogo
				+ ", numeroAcertos=" + numeroAcertos + ", acertos="
				+ Arrays.toString(acertos) + ", tipoAcerto=" + tipoAcerto
				+ ", id_concorrente=" + id_concorrente + ", id_palpite="
				+ id_palpite + ", id_sorteio=" + id_sorteio +  "]";
	}
 }