package modelo.repositorio;

import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityTransaction;

import modelo.Pessoa;

public class PessoaRepository extends PersistenceConfig {
	
	public boolean criarPessoa(Pessoa pessoa) {
		
		boolean resultado= true;
		EntityTransaction transaction = getEntityManager().getTransaction();
		
		try {
			transaction.begin();
			getEntityManager().persist(pessoa);
			transaction.commit();
			
		} catch (Exception e) {
			System.out.println("Erro ao tentar persistir uma nova pessoa! " + e.getMessage());
			e.printStackTrace();
			transaction.rollback();
			resultado = false;
		}
		
		return resultado;
		
	}
	
	public Pessoa recuperarPessoaPorId(int id) {
		
		Pessoa resultado = null;
		
		try {
			
			resultado = getEntityManager().find(Pessoa.class, id);
			
		} catch (Exception e) {
			System.out.println("Erro ao tentar recuperar a pessoa! " + e.getMessage());
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	public Set<Pessoa> recuperarPessoas() {
		Set<Pessoa> resultado = null;
		
		try {
			Stream<Pessoa> pessoasStream = getEntityManager().createQuery("FROM" + Pessoa.class.getName() + " ORDER BY idPessoa ASC").getResultStream();
			
			resultado = pessoasStream.collect(Collectors.toSet());
			
		} catch (Exception e) {
			System.out.println("Erro ao tentar recuperar as pessoas cadastradas! " + e.getMessage());
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	public boolean atualizarPessoa(Pessoa pessoa) {
		boolean resultado = true;
		EntityTransaction transaction = getEntityManager().getTransaction();
		
		try {
			
			transaction.begin();
			getEntityManager().merge(pessoa);
			transaction.commit();
			
		} catch (Exception e) {
			System.out.println("Erro ao tentar atualizar os dados da pessoa! " + e.getMessage());
			e.printStackTrace();
			transaction.rollback();
			resultado = false;
		}
		
		return resultado;
	}
	
	public boolean excluirPessoa(Pessoa pessoa) {
		
		boolean resultado = true;
		EntityTransaction transaction = getEntityManager().getTransaction();
		
		try {
			
			transaction.begin();
			pessoa = getEntityManager().find(Pessoa.class, pessoa.getIdPessoa());
			getEntityManager().remove(pessoa);
			transaction.commit();
			
		} catch (Exception e) {
			System.out.println("Erro ao tentar remover a pessoa! " + e.getMessage());
			e.printStackTrace();
			transaction.rollback();
			resultado = false;
		}
		
		return resultado;
	}
	
	public boolean excluirPessoaPorId(int id) {
		boolean resultado = true;
		
		try {
			Pessoa pessoa = recuperarPessoaPorId(id);
			resultado = excluirPessoa(pessoa);
		}
		catch (Exception e) {
			System.out.println("Erro ao tentar remover a pessoa! " + e.getMessage());
			e.printStackTrace();
			resultado = false;
		}
		
		return resultado;
	}

}
