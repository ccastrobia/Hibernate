package modelo;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table (name = "pessoas")

public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1l;
	
	@Id
	/*
	 * @GeneratedValue - tornar a coluna na tabela de banco 
	 * de dados com a carascteristicas de preenchimento de 
	 * valores de forma automatica
	 * 
	 */
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected int idPessoa;
	
	@Column(name = "nome", nullable = false)
	protected String nomePessoa;
	
	@Column(name = "endereco")
	protected String enderecoPessoa;
	
	@Column(name = "cep")
	protected long cepPessoa;
	
	@Column(name = "telefone")
	protected String telefonePessoa;
	
	@Column(name = "renda")
	protected double rendaPessoa;
	
	@Column(name = "situacao")
	protected int situacaoPessoa;
	
	/*
	 * FetchType.LAZY diz ao provedor de persistência para não buscar
	 * as entidade associadas
	 * All - exclui os objetos relacionados a ele
	 
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "pessoas_contascomuns", joinColumns = { @JoinColumn(name = "idpessoa", referencedColumnName = "id")},
	inverseJoinColumns = { @JoinColumn(name = "numeroconta", referencedColumnName = "numeroconta")})
	//Set - coleção de dados. gera consultas mais eficientes (utilizando hibernate)
	protected Set<ContaComum> contas;
	*/
	
	// Construtores 
	
	public Pessoa()
	{
		this.situacaoPessoa = 1;
	}
	
	public Pessoa(String nomePessoa, String enderecoPessoa, long cepPessoa, String telefonePessoa,
			double rendaPessoa, int situacaoPessoa)
	{
		this.nomePessoa = nomePessoa;
		this.enderecoPessoa = enderecoPessoa;
		this.cepPessoa = cepPessoa;
		this.telefonePessoa = telefonePessoa;
		this.rendaPessoa = rendaPessoa;
		this.situacaoPessoa = situacaoPessoa;
	}
	
	//Getters and Setters
	
	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getEnderecoPessoa() {
		return enderecoPessoa;
	}

	public void setEnderecoPessoa(String enderecoPessoa) {
		this.enderecoPessoa = enderecoPessoa;
	}

	public long getCepPessoa() {
		return cepPessoa;
	}

	public void setCepPessoa(long cepPessoa) {
		this.cepPessoa = cepPessoa;
	}

	public String getTelefonePessoa() {
		return telefonePessoa;
	}

	public void setTelefonePessoa(String telefonePessoa) {
		this.telefonePessoa = telefonePessoa;
	}

	public double getRendaPessoa() {
		return rendaPessoa;
	}

	public void setRendaPessoa(double rendaPessoa) {
		this.rendaPessoa = rendaPessoa;
	}

	public int getSituacaoPessoa() {
		return situacaoPessoa;
	}

	public void setSituacaoPessoa(int situacaoPessoa) {
		this.situacaoPessoa = situacaoPessoa;
	}

}