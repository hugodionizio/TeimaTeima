package bancodados.view;

import java.util.List;

import bancodados.dao.AcertoDao;
import bancodados.dao.LoteriaDao;
import bancodados.dao.NomeDao;
import bancodados.dao.PalpiteDao;
import bancodados.dao.SorteioDao;
import bancodados.dao.VendaDao;
import bancodados.model.Acerto;
import bancodados.model.Loteria;
import bancodados.model.Nome;
import bancodados.model.Palpite;
import bancodados.model.Sorteio;
import bancodados.model.Venda;

public class ListTables {

	public static boolean showVendedores() {
		int num = 0;
		
		List<Venda> vendas = (new VendaDao()).findVendas();
		if(vendas != null)
			num = vendas.size();
		if (num > 0) {
			System.out.println("Número de vendedores: "+num);
			for (Venda venda : vendas) {
				System.out.println(venda.toString());
			}
			
			return true;
		}
		else {
			System.out.println("Sem vendedores.");
			return false;			
		}
	}
	
	public static boolean showConcorrentes() {
		int num = 0;
		
		List<Nome> concorrentes = (new NomeDao()).findNomes();
		if(null!=concorrentes)
			num = concorrentes.size();
		if (num > 0) {
			System.out.println("Número de concorrentes: "+num);
			for (Nome concorrente : concorrentes) {
				System.out.println(concorrente.toString());
			}
			
			return true;
		}
		else {
			System.out.println("Sem concorrentes.");
			return false;			
		}
	}
	
	public static boolean showPalpites() {
		int num = 0;
		
		List<Palpite> palpites = (new PalpiteDao()).findPalpites();
		if(null!=palpites)
			num = palpites.size();
		if (num > 0) {
			System.out.println("Número de palpites: "+num);
			for (Palpite palpite : palpites) {
				System.out.println(palpite.toString());
			}
			
			return true;
		}
		else {
			System.out.println("Sem palpites.");
			return false;			
		}
	}
	
	public static boolean showLoteria() {
		int num = 0;
		
		List<Loteria> concursos = (new LoteriaDao()).findConcursos();
		if(null!=concursos)
			num = concursos.size();
		if (num > 0) {
			System.out.println("Número de concursos: "+num);
			for (Loteria concurso : concursos) {
				System.out.println(concurso.toString());
			}
			
			return true;
		}
		else {
			System.out.println("Sem concursos.");
			return false;
		}
	}
		
	public static boolean showSorteios() {
		int num = 0;
		
		List<Sorteio> sorteios = (new SorteioDao()).findSorteios();
		if(null!=sorteios)
			num = sorteios.size();
		if (num > 0) {
			System.out.println("Número de sorteios: "+num);
			for (Sorteio sorteio : sorteios) {
				System.out.println(sorteio.toString());
			}
			
			return true;
		}
		else {
			System.out.println("Sem sorteios.");
			return false;			
		}
	}

	public static boolean showAcertos() {
		int num = 0;
		
		List<Acerto> acertos = (new AcertoDao()).findAcertos();
		if(null!=acertos)
			if(acertos != null)
			num = acertos.size();
		if (num > 0) {
			System.out.println("Número de acertos: "+num);
			for (Acerto acerto : acertos) {
				System.out.println(acerto.toString());
			}
			
			return true;
		}
		else {
			System.out.println("Sem acertos.");
			return false;			
		}
	}
	
	public static void main(String[] args) {
		showVendedores();
		showConcorrentes();
		showPalpites();
		showLoteria();
		showSorteios();
		showAcertos();
	}
}
