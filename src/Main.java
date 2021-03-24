import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import modelo.ContaComum;
import modelo.Movimento;
import modelo.Pessoa;
import modelo.repositorio.ContaComumRepository;
import modelo.repositorio.PersistenceConfig;
import modelo.repositorio.PessoaRepository;

public class Main {
	
	private static final DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyy HH:mm");
	private static final Locale locale = new Locale("pt", "BR");
	private static final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Pessoa p1 = new Pessoa("Maria dos Santos", "Av. Brasil, 1262", 1430001l, "(99) 99999-9999", 2500.55, 1);
		Pessoa p2 = new Pessoa("João dos Santos", "Av. Brasil, 1262", 1430001l, "(88) 88888-8888", 2300.25, 1);
		
		PessoaRepository pessoaRepository = new PessoaRepository();
		
		System.out.println("\n********** Exemplo de Inserção 1: **********\n");
		
		pessoaRepository.criarPessoa(p1);
		pessoaRepository.criarPessoa(p2);
		
		System.out.println("ID da Pessoa p1: " + p1.getIdPessoa());
		System.out.println("ID da Pessoa p2: " + p2.getIdPessoa());
		
		ContaComum cc1 = new ContaComum(1l, Calendar.getInstance(), null, 1, 123456, 5000.00);
				
		cc1.getPessoas().add(p1);
		cc1.getPessoas().add(p2);
		
		ContaComumRepository contaComumRepository = new ContaComumRepository();
		
		System.out.println("\n********** Exemplo de Inserção 2: **********\n");
		
		contaComumRepository.criarContaComum(cc1);
		
		Movimento m1 = new Movimento(1, 1250.0, cc1); // Depósito de R$ 1.250,00
		
		System.out.println("\n********** Exemplo de Inserção 3: **********\n");
		
		m1.registrarMovimento();
		
		Movimento m2 = new Movimento(2, 3500.0, cc1); // Saque de R$ 3.500,00
		
		System.out.println("\n********** Exemplo de Inserção 4: **********\n");
		
		m2.registrarMovimento();
		
		Movimento m3 = new Movimento(2, 10500.0, cc1); // Saque de R$ 10.500,00 - Saldo não disponível
		
		System.out.println("\n********** Tentativa de Inserção sem sucesso: **********\n");
		
		m3.registrarMovimento();
		
		PersistenceConfig.closeEntityManager();
	}

}
