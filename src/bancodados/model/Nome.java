package bancodados.model;


public class Nome {
	private int id_concorrente;
    private String nomeConcorrente;
	private int idade;
	private String referencia;
	private String endereco;
	private String fone;
	private String email;

    /**
	 * @param nomeConcorrente
	 * @param idade
	 * @param referencia
	 * @param endereco
	 * @param fone
	 * @param email
	 */
	public Nome(String nomeConcorrente, int idade,
			String referencia, String endereco, String fone, String email) {
		super();
		this.nomeConcorrente = nomeConcorrente;
		this.idade = idade;
		this.referencia = referencia;
		this.endereco = endereco;
		this.fone = fone;
		this.email = email;
	}

	public Nome() {
		// TODO Auto-generated constructor stub
	}

	public Nome(String concorrente, String referencia, String endereco,
			String fone, String email) {
		super();
		this.nomeConcorrente = concorrente;
		this.referencia = referencia;
		this.endereco = endereco;
		this.fone = fone;
		this.email = email;
	}

	public int getId_concorrente() {
		return id_concorrente;
	}

	public void setId_concorrente(int id_concorrente) {
		this.id_concorrente = id_concorrente;
	}

	public String getNomeConcorrente() {
		return nomeConcorrente;
	}

	public void setNomeConcorrente(String nomeConcorrente) {
		this.nomeConcorrente = nomeConcorrente;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

    @Override
	public String toString() {
		return "Nome [id_concorrente=" + id_concorrente + ", nomeConcorrente="
				+ nomeConcorrente + ", idade=" + idade + ", referencia="
				+ referencia + ", endereco=" + endereco + ", fone=" + fone
				+ ", email=" + email + "]";
	}
}