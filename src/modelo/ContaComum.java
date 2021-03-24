package modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//anotações (data annotations) é um recurso para mapear o que dentro de uma classe 
//de dominio será transformado em um elemento especifico de banco de dados

/* @Entity deixa explicito que a classe ContaComum será 
 * controlada pelo hibernate como uma entidade de banco de dados
 */
@Entity

/*@Table confirgurar como será a tabela para o qual
 * os objetos desse tipo (ContaComum) serão mapeados
 * como linhas e registros. 
*/
@Table (name = "contascomuns")

/* interface Serializable faz com que a classe contenha
os métodos e atributos necessários para que as bibliotecas
do Java tarsnformem a classe em um dado serializado (salvado em disco),
independente do formato */
public class ContaComum implements Serializable {
	
	/* atributo que permite que a marquina virtual Java 
	 * identifique a versao do bytecode referente aquela 
	 * classe quando precisa serializar ou deserializar
	*/
	private static final long serialVersionUID = 1l;
	
	@Id
	/*
	 * @Column permite fazer customizações realacioandas aquela coluna da tabela 
	 * que será mapeada para aquele atributo
	 */
	@Column(name = "numeroconta")
	protected long numeroConta;
	
	/*
	 * @Temporal para atributos de tempo (data/hora) Date ou Calendar
	 */
	@Temporal(TemporalType.DATE)
	//nullable este atributo é obrigatório
	@Column(name = "aberturaconta", nullable = false)
	protected Calendar aberturaConta;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fechamentoconta")
	protected Calendar fechamentoConta;
	
	@Column(name = "situacaoconta")
	protected int situacaoConta;
	
	@Column(name = "senhaconta")
	protected int senhaConta;
	
	@Column(name = "saldoconta")
	protected double saldoConta;
	
	// Muitos para muitos
	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	@JoinTable(name = "pessoas_contascomuns", joinColumns = { @JoinColumn(name = "numeroconta", referencedColumnName = "numeroconta")},
	inverseJoinColumns = { @JoinColumn(name = "idpessoa", referencedColumnName = "id")})
	protected Set<Pessoa> pessoas;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "contaMovimento", fetch = FetchType.LAZY)
	protected Set<Movimento> movimentosConta;

	public long getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(long numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Calendar getAberturaConta() {
		return aberturaConta;
	}

	public void setAberturaConta(Calendar aberturaConta) {
		this.aberturaConta = aberturaConta;
	}

	public Calendar getFechamentoConta() {
		return fechamentoConta;
	}

	public void setFechamentoConta(Calendar fechamentoConta) {
		this.fechamentoConta = fechamentoConta;
	}

	public int getSituacaoConta() {
		return situacaoConta;
	}

	public void setSituacaoConta(int situacaoConta) {
		this.situacaoConta = situacaoConta;
	}

	public double getSaldoConta() {
		return saldoConta;
	}

	public void setSaldoConta(double saldoConta) {
		this.saldoConta = saldoConta;
	}

	public Set<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(Set<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Set<Movimento> getMovimentosConta() {
		return movimentosConta;
	}

	public void setMovimentosConta(Set<Movimento> movimentosConta) {
		this.movimentosConta = movimentosConta;
	}
	
	public ContaComum()
	{
		this.pessoas = new HashSet<Pessoa>();
		this.movimentosConta = new HashSet<Movimento>();
	}
	
	public ContaComum(long numeroConta, Calendar aberturaConta, Calendar fechamentoConta, int situacaoConta,
			int senhaConta, double saldoConta)
	{
		this.numeroConta = numeroConta;
		this.aberturaConta = aberturaConta;
		this.fechamentoConta = fechamentoConta;
		this.situacaoConta = situacaoConta;
		this.senhaConta = senhaConta;
		this.saldoConta = saldoConta;
		this.pessoas = new HashSet<Pessoa>();
		this.movimentosConta = new HashSet<Movimento>();
	}

}
