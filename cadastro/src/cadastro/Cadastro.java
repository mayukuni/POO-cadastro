package cadastro;
import java.util.Scanner;

class Cliente {
	private static int nextId = 1;
	private int id;
	private String nome;
	private int idade;
	private String endereco;
	private String telefone;
	
	public Cliente(String nome, int idade, String endereco, String telefone) {
		this.setId(nextId++);
		this.nome = nome;
		this.idade = idade;
		this.endereco = endereco;
		this.telefone = telefone;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String toString() {
	    return String.format("Cliente %d, nome: %s, idade: %d, endereco: %s, telefone: %s", 
	            getId(), nome, idade, endereco, telefone);
	}
	
	public void setId(int id) {
		this.id = id;
	}
}

class Node {
	Cliente cliente;
	Node next;
	
	public Node() {
		this.cliente = null;
		this.next = null;
	}
	
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}

class CadastroClientes {
	private Node head;
	
	public void add(Cliente cliente) {
		Node newNode = new Node();
		newNode.setCliente(cliente);
		if (head == null) {
			head = newNode;
		} else {
			Node atual = head;
			while (atual.next != null) {
				atual = atual.next;
			}
			atual.next = newNode;
		}
	}
	
	public Cliente get(String nome, boolean isName) {
		Node atual = head;
		while (atual != null) {
			if (isName && atual.cliente.getNome().equals(nome)) {
				return atual.cliente;
			} else if (!isName && Integer.toString(atual.cliente.getId()).equals(nome)) {
				return atual.cliente;
			}
			atual = atual.next;
		}
		return null;
	}
	
	public boolean update(String nome, Cliente novoCliente) {
	    Node atual = head;
	    while (atual != null) {
	        if (atual.cliente.getNome().equals(nome) || 
	            Integer.toString(atual.cliente.getId()).equals(nome)) {
	            novoCliente.setId(atual.cliente.getId()); 
	            atual.cliente = novoCliente; 
	            return true;
	        }
	        atual = atual.next;
	    }
	    return false;
	}
	
	public boolean remove(String nome) {
		if (head == null) return false;
		if (head.cliente.getNome().equals(nome)) {
			head = head.next;
		    return true;
		}
		Node atual = head;
		while (atual.next != null) {
		    if (atual.next.cliente.getNome().equals(nome)) {
		    	atual.next = atual.next.next;
		        return true;
		    }
		    atual = atual.next;
		}
		return false;
	}
	
	  public void display() {
		Node atual = head;
		while (atual != null) {
			System.out.println(atual.cliente);
			atual = atual.next;
		}
	  }
}

public class Cadastro {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		CadastroClientes cadastro = new CadastroClientes();
		int idade = 0;
		int opcao = 0;
		
		while (true) {
			boolean opcaoValida = false;
		    while (!opcaoValida) {
		    	System.out.println("Escolha uma opção");
				System.out.println("1 - Cadastrar cliente");
				System.out.println("2 - Consultar cliente");
				System.out.println("3 - Atualizar cliente");
				System.out.println("4 - Remover cliente");
				System.out.println("5 - Exibir todos os clientes ");
				System.out.println("6 - Sair");
		        if (sc.hasNextInt()) {
		            opcao = sc.nextInt();
		            opcaoValida = true;
		        } else {
		            System.out.println("A opção precisa ser um número inteiro.");
		            sc.next();
		        }
		    }
			sc.nextLine();
			
			switch (opcao) {
			case 1:
				System.out.println("Nome:");
				String nome = sc.nextLine();
			    boolean idadeValida = false;
			    while (!idadeValida) {
			        System.out.println("Idade:");
			        if (sc.hasNextInt()) {
			            idade = sc.nextInt();
			            idadeValida = true;
			        } else {
			            System.out.println("A idade precisa ser um número inteiro.");
			            sc.next();
			        }
			    }
				sc.nextLine();
				System.out.println("Endereço:");
				String endereco = sc.nextLine();
				System.out.println("Telefone:");
				String telefone = sc.nextLine();
				cadastro.add(new Cliente(nome, idade, endereco, telefone));
				break;
			case 2:
			    System.out.println("Para consultar cliente por nome, digite 1.");
			    System.out.println("Para consultar cliente por id, digite 2.");
			    int consultaOpcao = 0;
			    boolean opcaoValidaa = false;
			    while (!opcaoValidaa) {
			        if (sc.hasNextInt()) {
			            consultaOpcao = sc.nextInt();
			            sc.nextLine();
			            opcaoValidaa = true;
			        } else {
			            System.out.println("Opção inválida, digite 1 para consultar por nome ou 2 para buscar por id.");
			            sc.next();
			        }
			    }
			    if (consultaOpcao == 1) {
			        System.out.print("Nome do cliente a consultar: ");
			        nome = sc.nextLine();
			        Cliente cliente = cadastro.get(nome, true);
			        if (cliente != null) {
			            System.out.println(cliente);
			        } else {
			            System.out.println("Cliente não encontrado.");
			        }
			    } else if (consultaOpcao == 2) {
			        System.out.print("ID do cliente a consultar: ");
			        String id = sc.nextLine();
			        Cliente cliente = cadastro.get(id, false);
			        if (cliente != null) {
			            System.out.println(cliente);
			        } else {
			            System.out.println("Cliente não encontrado.");
			        }
			    } else {
			        System.out.println("Opção inválida, digite 1 para consultar por nome ou 2 para buscar por id.");
			    }
			    break;
			case 3:
				System.out.println("Para atualizar cliente por nome, digite 1.");
			    System.out.println("Para atualizar cliente por id, digite 2.");
			    int buscaOpcao = sc.nextInt();
			    sc.nextLine();
			    Cliente cliente = null;

			    if (buscaOpcao == 1) {
			        System.out.print("Nome do cliente a atualizar: ");
			        nome = sc.nextLine();
			        cliente = cadastro.get(nome, true);
			    } else if (buscaOpcao == 2) {
			        System.out.print("ID do cliente a atualizar: ");
			        String id = sc.nextLine();
			        cliente = cadastro.get(id, false);
			    } else {
			        System.out.println("Opção inválida.");
			        break;
			    }

			    if (cliente != null) {
			        System.out.print("Novo Nome: ");
			        String novoNome = sc.nextLine();
			        System.out.print("Nova Idade: ");
			        int novaIdade = sc.nextInt();
			        sc.nextLine(); 
			        System.out.print("Novo Endereço: ");
			        String novoEndereco = sc.nextLine();
			        System.out.print("Novo Telefone: ");
			        String novoTelefone = sc.nextLine();
			        
			        cliente.setNome(novoNome);
			        cliente.setIdade(novaIdade);
			        cliente.setEndereco(novoEndereco);
			        cliente.setTelefone(novoTelefone);
			        
			        boolean atualizado = cadastro.update(cliente.getNome(), cliente);
			        if (atualizado) {
			            System.out.println("Cliente atualizado com sucesso.");
			        } else {
			            System.out.println("Cliente não encontrado.");
			        }
			    } else {
			        System.out.println("Cliente não encontrado.");
			    }
			    break;
			case 4:
				System.out.println("Para remover cliente por nome, digite 1.");
			    System.out.println("Para remover cliente por id, digite 2.");
			    int removeOpcao = sc.nextInt();
			    sc.nextLine();

			    if (removeOpcao == 1) {
			        System.out.print("Nome do cliente a remover: ");
			        nome = sc.nextLine();
			        boolean removido = cadastro.remove(nome);
			        if (removido) {
			            System.out.println("Cliente removido com sucesso.");
			        } else {
			            System.out.println("Cliente não encontrado.");
			        }
			    } else if (removeOpcao == 2) {
			        System.out.print("ID do cliente a remover: ");
			        String id = sc.nextLine();
			        Cliente cliente2 = cadastro.get(id, false);
			        if (cliente2 != null) {
			            boolean removido = cadastro.remove(cliente2.getNome());
			            if (removido) {
			                System.out.println("Cliente removido com sucesso.");
			            } else {
			                System.out.println("Cliente não encontrado.");
			            }
			        } else {
			            System.out.println("Cliente não encontrado.");
			        }
			    } else {
			        System.out.println("Opção inválida.");
			    }
			    break;
			case 5:
				System.out.println("Lista de clientes cadastrados:");
				cadastro.display();
				break;
			case 6:
				sc.close();
				return;
			default:
				System.out.println("Opção inválida");				
			}
		}
	}
}
