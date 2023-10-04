package ArvoreBinariaDeBusca;

// Interface da Arvore Binaria de Busca
public interface IArvoreBinaria extends IArvore {
  
  void preOrdem(No no) throws ArvoreVazia;
  void inOrdem(No no) throws ArvoreVazia;
  void posOrdem(No no) throws ArvoreVazia;
}