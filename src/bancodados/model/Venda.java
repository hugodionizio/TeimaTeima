package bancodados.model;

public class Venda {
	private int id_vendedor;
    private String nomeVendedor;
	public Venda(String nomeVendedor) {
		super();
		this.nomeVendedor = nomeVendedor;
	}
	
    public Venda() {
		// TODO Auto-generated constructor stub
	}

	public int getId_vendedor() {
		return id_vendedor;
	}

	public void setId_vendedor(int id_vendedor) {
		this.id_vendedor = id_vendedor;
	}

	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}

	

    //gerar os metodos getters and setters

    @Override
	public String toString() {
		return "Venda [id_vendedor=" + id_vendedor + ", nomeVendedor="
				+ nomeVendedor + "]";
	}
}