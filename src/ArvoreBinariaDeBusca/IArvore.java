package ArvoreBinariaDeBusca;

// Interface da Arvore Binária de Busca
public interface IArvore<T> extends Comparable<T>{

  void inserir(No<T> no) throws Exception;
  void remover(No no) throws NoInexistenteException, ArvoreVazia;
  No buscar(int dado) throws NoInexistenteException, ArvoreVazia;

  No visitar(No no) throws NoInexistenteException, ArvoreVazia;
  
  boolean estaVazia();
  boolean ehCompleta();
  int altura();
  
  void imprimirArvore() throws ArvoreVazia;
}