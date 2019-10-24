package persistencia;


import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException{
		/*
		List<Pessoa> lista = new ArrayList<Pessoa>();
		
		Scanner ler  = new Scanner(System.in);
		FileWriter arq = new FileWriter("agenda.csv");
		PrintWriter gravarArq = new PrintWriter(arq);
		
		for (Pessoa pessoa: lista) {
			gravarArq.printf("%d;%s\n",pessoa.getCodigo(),pessoa.getNome());
		}
		arq.close();
		try {
			FileReader arq1 = new FileReader("agenda.csv");
			BufferedReader lerArq = new BufferedReader(arq1);
			String linha = lerArq.readLine();
			lista = new ArrayList<Pessoa>();
			
			while(linha != null) {
				String[] leitura = linha.split(";");
				p = new Pessoa();
				p.setCodigo(Integer.parseInt(leitura[0]));
				p.setNome(leitura[1]);
				lista.add(p);
				linha = lerArq.readLine();
			}
			arq1.close();
		} catch (Exception e) {
			System.err.printf("erro na abertura do arquivo: %s. \n",
			e.getMessage());
		}
		for (Pessoa pessoa: lista) {
			System.out.println(pessoa);
			
		}
		ler.close();
		*/
	
		Pessoa pessoa = new Pessoa();
		Agenda agenda = new Agenda();
		Csv csv = new Csv();
		Gson gson = new Gson();
		Xml xml = new Xml();
		Persistencia persistencia = new Persistencia(csv);
		
		Scanner ler = new Scanner(System.in);
		int var = -1;
		while(var != 0) {
			agenda.atualizarLista(persistencia.ler());
			System.out.println("1- inserção\n"
					+ "2 - exlcuir\n"
					+ "3 -consultar\n"
					+ "4- Alterar\n"
					+ "0 - sair\n");
			var = ler.nextInt();
			if(var == 1) {
				System.out.print("Codigo: ");
				pessoa.setCodigo(ler.nextInt());
				System.out.print("Nome ");
				pessoa.setNome(ler.next());
				System.out.print("Email ");
				pessoa.setEmail(ler.next());
				System.out.print("Fone ");
				pessoa.setTelefone(ler.next());
				System.out.println("Data Nascimento (DD MM AAAA)");
				System.out.print("dia: ");
				int dia = ler.nextInt();
				System.out.print("mes: ");
				int mes = ler.nextInt();
				System.out.print("ano: ");
				int ano = ler.nextInt();
				Calendar calendario = Calendar.getInstance();
				calendario.set(Calendar.DAY_OF_MONTH, dia);
				calendario.set(Calendar.MONTH, mes-1);
				calendario.set(Calendar.YEAR, ano);
				Date data = calendario.getTime();
				pessoa.setDataNascimento(data);
				System.out.println(pessoa.toString());
				agenda.incluir(pessoa);
				persistencia.gravar(agenda.listaPessoa);
				System.out.println("teste");
				
			}else if(var == 2) {
				System.out.println("Digite o codigo da pessoa: ");
				int nome = ler.nextInt();
				agenda.excluir(agenda.localizarPessoa(nome));
				persistencia.gravar(agenda.listaPessoa);
			}else if(var == 3) {
				int varConsulta = 0;
				System.out.println("1- consulta por nome\n"
						+ "2- consulta por dominio\n"
						+ "3- consulta aniversariante mês\n");
				varConsulta = ler.nextInt();
				if(varConsulta == 1) {
					System.out.println("Digite o nome da pessoa: ");
					String nome = ler.next();
					System.out.println(agenda.consultaNome(nome));
				}else if(varConsulta == 2) {
					System.out.println("Digite o dominio: ");
					String dominio = ler.next();
					System.out.print(agenda.listaPessoaDominio(dominio));
				}else if( varConsulta == 3) {
					System.out.print("Digite o mês de Aniversario: ");
					int mes = ler.nextInt();
					System.out.println(agenda.listaPessoaAniversariantes(mes-1));
				}
			}else if(var == 4) {
				Pessoa pessoa1 = new Pessoa();
				System.out.println("Digite o codigo da pessoa: ");
				int cod = ler.nextInt();
				pessoa1 = agenda.localizarPessoa(cod);
				agenda.excluir(agenda.localizarPessoa(cod));
				System.out.println(pessoa1.toString());
				System.out.println("O que deseja alterar?\n"
						+ "1-codigo"
						+ "2-Nome\n"
						+ "3-email\n"
						+ "4-fone\n"
						+ "5-data nascimento\n");
				int op = ler.nextInt();
				if(op ==1) {
					System.out.println("digite o codigo da pessoa: ");
					pessoa1.setCodigo(ler.nextInt());
				}else if(op ==2) {
					System.out.println("digite o nome da pessoa: ");
					pessoa1.setNome(ler.next());
				}else if(op ==3) {
					System.out.println("digite o email da pessoa: ");
					pessoa1.setEmail(ler.next());
				}else if(op == 4) {
					System.out.println("digite o fone da pessoa: ");
					pessoa1.setTelefone(ler.next());
				}else if(op == 5) {
					System.out.println("Data Nascimento (DD MM AAAA)");
					System.out.print("dia: ");
					int dia = ler.nextInt();
					System.out.print("mes: ");
					int mes = ler.nextInt();
					System.out.print("ano: ");
					int ano = ler.nextInt();
					Calendar calendario = Calendar.getInstance();
					calendario.set(Calendar.DAY_OF_MONTH, dia);
					calendario.set(Calendar.MONTH, mes-1);
					calendario.set(Calendar.YEAR, ano);
					Date data = calendario.getTime();
					pessoa1.setDataNascimento(data);
				}
				agenda.incluir(pessoa1);
				persistencia.gravar(agenda.listaPessoa);
			}
		}
		ler.close();
	}
}







