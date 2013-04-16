

import javax.swing.JOptionPane;
//-------------------------------------------------------------------------------------//
/* ArvoreB.java
 * Arvore B
 * Objetivo: Implementacao de uma estrutura de dados arvore B com metodos
 * de exemplificacao e testes de execucao
 * Alunos: Ezequely, Rafael, Bruno.
 * Data: 08/04/2013
 * Ultima Modificacao: 16/04/2013
 */
//-------------------------------------------------------------------------------------//
//Classe que implementa uma arvore B
//árvore de inteiros, onde a ordem d
//significa o número mínimo de chaves
//por página
public class ArvoreB {
        //****ATRIBUTOS****//
	private NoB raiz;
	private int MIN_CHAVES;
	private int MAX_CHAVES;
	private String print;

	//****CONSTRUTOR PADRAO*****//
	//construtor da ArvoreB.
	//inicializa a raiz e o número mínimo e maximo de chaves por página
	public ArvoreB(NoB raiz, int Ordem) {
		super();
		this.raiz = raiz;
		this.MIN_CHAVES = (Ordem - 1) ;
		this.MAX_CHAVES = ((Ordem * 2) - 1);
	}

	//****METODOS****//


	/*========================================================================================*/
//	Metodo de busca de um elemento com chave x na arvore
	public NoB buscarElemento(int x) {
		NoB elem = busca(raiz,x);
		if (elem == null) return null; //nao achou
		else {
			if (elem.chave == x) return elem ;//achou
			else return null;//incorreto, retorna null
		}
	}
	//Metodo auxiliar para buscarElemento, inserirElemento e removerElemento
	private NoB busca(NoB no, int x) {
		NoB pt = no;
		if (pt == null) return null ; //nao achou
		else {
			pt = pt.proximo;
			while (pt.proximo != null && pt.chave < x) pt = pt.proximo;
			if (pt.chave < x && pt.pagina != null) pt = busca(pt.pagina,x);
			else if (pt.chave > x && pt.anterior.pagina != null) pt = busca(pt.anterior.pagina,x);
			return(pt);
		}
	}
	
	/*========================================================================================*/
	//Metodo de cisao para caso de overload na pagina da arvore.
	private void cisaoArvoreB (NoB cabecalho) {
		int n = 0;
		NoB temp1;
		NoB temp2;
		NoB pag1 = cabecalho; //cabecalho e' o cabeçclho da página desbalanceada
		NoB pag2 = new NoB(pag1.chave - MIN_CHAVES - 1, null, pag1, null);
		pag1.chave = MIN_CHAVES;
		temp1 = pag2.proximo;
		
		while (n <= MIN_CHAVES) { //temp1 aponta para o no que vai subir
			temp1 = temp1.proximo;
			n++;
		}
		temp2 = temp1.proximo; //temp2 guarda a nova página
		temp1.proximo = null;
		pag2.pagina = temp1.pagina; //caso o nó que vai subir seja "pai" de uma pagina, preserva-se
		if (pag2.pagina != null) pag2.pagina.anterior = pag2;
		//organizacao de ponteiros
		pag2.proximo = temp2;
		temp2.anterior = pag2;
		temp1.anterior.proximo = null;
		temp1.anterior = null;
		temp1.pagina = pag2;
		pag2.anterior = temp1;
		if (pag1.anterior == null) { //caso a chave que subir torne-se a nova raiz
			NoB pag3;
			pag3 = new NoB(1, pag1, temp1, null);
			pag1.anterior = pag3;
			temp1.anterior = pag3;
			raiz = pag3;
		}
		else {
			pag1 = pag1.anterior;
			temp2 = pag1.proximo;
			pag1.proximo = temp1;
			temp1.anterior = pag1;
			temp1.proximo = temp2;
			if (temp2 != null) temp2.anterior = temp1;
			while (pag1 != null && pag1.pagina != pag2){ //procura cabecalho
				pag2 = pag1;
				pag1 = pag1.anterior;
			}
			pag2.chave++;
			if (pag2.chave >= MAX_CHAVES) cisaoArvoreB(pag2); //cisao executada recursivamente
		}
	}
	/*========================================================================================*/	
	//Metodo de concatenacao da ArvoreB. Concatena cabecalho com a página seguinte
	private void cocatenacaoArvoreB(NoB cabecalho) {
		//Nos temporarios para auxilio na concatenacao
		NoB temp1;
		NoB temp2;
		
		NoB pag1 = cabecalho; //novamente, cabeçalho da página desbalanceada
		NoB pag2 = pag1.anterior;

		
		if (pag2.proximo != null){ pag2 = pag2.proximo; }//correcao da posição da página
		//organizacao de ponteiros
		pag1 = pag2.anterior;
		temp2 = pag2.proximo;
		temp1 = pag1.pagina;
		pag1.proximo = temp2;
		if (temp2 != null){ temp2.anterior = pag1;}
		temp2 = pag2.pagina;
		temp1.chave = temp1.chave + temp2.chave + 1; //atualizacao do cabecalho
		while (temp1.proximo != null) temp1 = temp1.proximo; //vai pro fim da proxima pagina
		//mais organizacao de ponteiros
		temp1.proximo = pag2;
		pag2.anterior = temp1;
		temp2 = temp2.proximo;
		pag2.proximo = temp2;
		if (temp2 != null) temp2.anterior = pag2;
		temp2 = pag2.pagina.pagina;
		pag2.pagina = temp2;
		pag1 = pag2;
		while ((pag2 != null) && (pag2.pagina != pag1)){ //busca do cabecalho
			pag1 = pag2;
			pag2 = pag2.anterior;
		}
		temp1 = pag1;
		pag1 = pag2;
		while ((pag2 != null) && (pag2.pagina != pag1)){ //buscando o cabeçalho da página que "perdeu"
			pag1 = pag2;
			pag2 = pag2.anterior;
		}
		pag1.chave = pag1.chave -1; //atualizando o cabeçalho da página que perdeu um nó
		if (temp1.chave > MAX_CHAVES) cisaoArvoreB(temp1);
		if (pag1.chave < MIN_CHAVES && pag1 != raiz){
			cocatenacaoArvoreB(pag1); //necessita nova concatenacao
		}
		else {
			if (pag1.chave == 0) {
				raiz = temp1;
				raiz.anterior = null;
			}
		}
	}

	/*========================================================================================*/
	//Metodo principal de insercao que recebe uma chave x e e' chamado pelo usuario
	protected boolean inserirElemento(int x) {
		NoB elem = busca(raiz,x);
		NoB novaPagina;
		NoB temp;
		
		if (elem == null) { //caso seja o primeiro elemento inserido

			novaPagina = new NoB(x, null, null, null);
			raiz = new NoB(1, null, novaPagina, null);
			novaPagina.anterior = raiz;
			return(true);

		}
		else {//nao e o primeiro
			if (elem.chave == x) return false;//elemento ja existe na arvore
			else {
				novaPagina = new NoB(x, null, null, null);
				if (elem.chave < x)	{ //decide se irá inserir antes ou depois do nó retornado
					elem.proximo = novaPagina;
					novaPagina.anterior = elem;
				}
				else {
					temp = elem.anterior;
					temp.proximo = novaPagina;
					novaPagina.proximo = elem;
					elem.anterior = novaPagina;
					novaPagina.anterior = temp;
				}
				while (novaPagina != null && novaPagina.pagina != elem) { //em busca do cabecalho
					elem = novaPagina;
					novaPagina = novaPagina.anterior;
				}
				elem.chave = elem.chave + 1; //atualizacao do cabecalho
				if (elem.chave > MAX_CHAVES - 1) {
					cisaoArvoreB(elem); //caso estoure a página
				}
				return(true);
			}
		}
	}

	/*========================================================================================*/	
	//Metodo de remocao de um elemento com chave x na arvore
	protected boolean removerElemento(int x) {
		NoB elem = busca(raiz,x);//busca 
		NoB temp;
		
		if (elem == null) return(false); //nao achou
		else {
			if (elem.chave != x) return(false); //elemento nao encontrado
			else {
				if (elem.pagina != null) { //busca da chave maior do que x
					
					temp = elem.pagina;
					while (temp.pagina != null) temp = temp.pagina;
					elem.chave = temp.proximo.chave; //substituicao de x pela chave maior do que x
					elem = temp.proximo; //o nó a ser removido é folha
					
				}
				temp = elem.anterior;
				temp.proximo = elem.proximo;
				elem.proximo = null;
				elem.anterior = null;
				elem = elem.proximo;
				if (elem != null) elem.anterior = temp;
				elem = temp;
				while (temp != null && temp.pagina != elem) {//pesquisa
					elem = temp;
					temp = temp.anterior;
				}
				elem.chave = elem.chave -1;
				
				if (elem != raiz && elem.chave < MIN_CHAVES ){//necessita concatenacao
					cocatenacaoArvoreB(elem);
				}
				return(true);
			}
		}
	}

	/*========================================================================================*/
	/*Metodo que exibe a arvore em ordem (utiliza metodo privado para auxiliar 
	 * para recursao) na representacao de parenteses aninhados*/
	public String exibirArvoreInOrdem() {
		print = exibirArvoreinOrdem(raiz);
		String s = print;
		print ="";
		return s;
	}
	
	//auxiliar para o metodo de exibir a arvore em ordem
	private String exibirArvoreinOrdem(NoB p) {
		if (p == null) return "";
		if (print == null) print = "";
		print = print + "(";
		while (p != null) {
			if (p.pagina != null) print = exibirArvoreinOrdem(p.pagina);
			p = p.proximo;
			if (p != null) print = print + " " + p.chave + " ";
		}
		print = print + ")";
		return print;
	}
	
	/*========================================================================================*/
	//Metodo de execucao da Arvore B
	public void start(){
		String parametro;
		String tipodeOperacao =" ";
		JOptionPane.showMessageDialog(null,"Arvore B \n Aceita entrada de valores inteiros"," Arvore B",JOptionPane.PLAIN_MESSAGE);
		System.out.println("**Historico de operações**");
		while (tipodeOperacao != null){//inicio do loop
			tipodeOperacao = JOptionPane.showInputDialog("Entre com o tipo de operacao" +
					" que deseja fazer:\n [1] Busca \n [2] Insercao \n [3] " +
			"Remocao\n [4] Impressao");
			if(tipodeOperacao == null || tipodeOperacao.length() == 0){return;}//entrada vazia(cancelamento do panel)

			boolean testeOperador=false;
			for(int j =0; j < tipodeOperacao.length(); j++){//testa se entrada nao é inteiro
				if (!Character.isDigit(tipodeOperacao.charAt(j))){ 
					testeOperador =true;
				}	
			}
			if (testeOperador){JOptionPane.showMessageDialog(null,"Entrada incorreta (entre com um " +
					"numero de 1 a 4)"," Arvore B ",JOptionPane.PLAIN_MESSAGE);
			}
			else{
				//IMPRESSAO
				if(tipodeOperacao.length() == 1 && tipodeOperacao.charAt(0) == '4'){//impressao
					JOptionPane.showMessageDialog(null,"A arvore atual e': " + exibirArvoreInOrdem()
							,"Impressao",JOptionPane.PLAIN_MESSAGE); //impressao
					System.out.println("Impressao da arvore: " + this.exibirArvoreInOrdem());
				}
				else{
					parametro = JOptionPane.showInputDialog("Entre com o parametro da operacao (Int)");
					if (parametro == null ||parametro.length() == 0){return;}//teste de cancelamento ou entrada vazia

					boolean testeParametro=false;
					for(int j =0; j < parametro.length(); j++){
						if (!Character.isDigit(parametro.charAt(j))){ //testa se entrada nao é inteiro
							testeParametro = true;
						}	
					}
					if(testeParametro){
						JOptionPane.showMessageDialog(null,"Entrada incorreta (somente inteiros)"," Arvore B ",JOptionPane.ERROR_MESSAGE);
					}
					else{
						int x = Integer.parseInt(parametro);
						//BUSCA
						if (tipodeOperacao.length() == 1 && tipodeOperacao.charAt(0) == '1'){
							if(this.buscarElemento(x) == null){//busca
								JOptionPane.showMessageDialog(null,"Nao achou elemento: " + parametro
										,"Impressao",JOptionPane.PLAIN_MESSAGE);
								System.out.println("Busca: Nao achou o elemento" + parametro);
							}
							else{
								JOptionPane.showMessageDialog(null,"Achou elemento: " + parametro
										,"Impressao",JOptionPane.PLAIN_MESSAGE);
								System.out.println("Busca: Achou o elemento" + parametro);
							}
						}
						//INSERCAO
						else if(tipodeOperacao.length() == 1 && tipodeOperacao.charAt(0) == '2'){//insercao
							this.inserirElemento(x);
							JOptionPane.showMessageDialog(null,"A arvore atual e': " + exibirArvoreInOrdem()
									,"Impressao",JOptionPane.PLAIN_MESSAGE);
							System.out.println("Tentativa de insercao de: " + parametro);
						}
						//REMOCAO
						else if(tipodeOperacao.length() == 1 && tipodeOperacao.charAt(0) == '3'){//remocao
							this.removerElemento(x);
							JOptionPane.showMessageDialog(null,"A arvore atual e': " + exibirArvoreInOrdem()
									,"Impressao",JOptionPane.PLAIN_MESSAGE);
							System.out.println("Tentativa de remocao de: " + parametro);
						}
						else if(tipodeOperacao == null){//cancelou 
							System.out.println("Fim da operacao");
							System.out.println("Finalizando operacao do programa de execucao da Arvore B");
							return; //teste de cancelamento
						}
						else{//Se diferente de todas as outras opcoes, entrada incorreta
							JOptionPane.showMessageDialog(null,"Entrada incorreta"," Arvore B ",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
			System.out.println(exibirArvoreInOrdem());
		}

	}
}
