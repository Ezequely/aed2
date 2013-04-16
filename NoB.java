


//-------------------------------------------------------------------------------------//
//Classe que implementa a estrutura do no.
// - usa-se listas duplamente encadeadas para representar as páginas
// - cada página tem um cabeçalho cuja chave é o númerode nós presentes
//   naquela página que ela encabeça.
public class NoB {

        
	public int chave;
	public NoB pagina;
	public NoB proximo;
	public NoB anterior;
	
	//construtor padrao
	protected NoB(int chave, NoB pagina, NoB proximo, NoB anterior) {
		this.chave = chave;
		this.pagina = pagina;
		this.proximo = proximo;
		this.anterior = anterior;
	}
}
