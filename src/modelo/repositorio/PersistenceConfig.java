package modelo.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceConfig {
	
	//transforma em objeto
	protected static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ControleBancarioDatabase");
	//EntityManager um gerenciador conectado a persistence unit permitindo executar diversas operações de mapeamento objeto-relacional e de manipulação dos objetos
	//por meio do framework
	protected static EntityManager entityManager;
	
	public static EntityManager getEntityManager()
	{
		if(entityManager == null)
		{
			try
			{
				entityManager = entityManagerFactory.createEntityManager();
				System.out.println("Gerenciador de Entidades instanciado com sucesso!");
			}
			catch (Exception e)
			{
				System.out.println("Erro ao tentar instanciar um Gerenciador de Entidades! " +
						e.getMessage());
			}
		}
		
		return entityManager;
	}
	
	public static void closeEntityManager()
	{
		try
		{
			if(entityManager != null)
			{
				entityManager.close();
				entityManager = null;
				System.out.println("Gerenciador de Entidades fechado com sucesso!");
			}
		}
		catch (Exception e)
		{
			System.out.println("Erro ao tentar fechar o Gerenciador de Entidades! " + e.getMessage());
		}
	}

}
