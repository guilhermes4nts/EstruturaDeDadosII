package Arvore;

public class Arvore {
    No raiz;
    public Arvore(No raiz){
        raiz = null;
    }
    public void inserir(int dado){

        raiz = inserirDado(raiz,dado);
    }

    public No inserirDado(No raiz, int dado){

        if (raiz == null){
            raiz.dado = dado;
        } else {
            if (dado < raiz.dado) {
                raiz.esquerda = inserirDado(raiz.esquerda, dado);
            } else if (dado > raiz.dado) {
                raiz.direita = inserirDado(raiz.direita, dado);
            }
        }

        return raiz;
    }

    public void preOrdem(No raiz){
        if(raiz != null){
            System.out.println(raiz.dado + " ");
            preOrdem(raiz.esquerda);
            preOrdem(raiz.direita);
        }
    }
    public void posOrdem(No raiz){
        if(raiz != null){
            posOrdem(raiz.esquerda);
            System.out.println(raiz.dado + " ");
            posOrdem(raiz.direita);
        }
    }
    public void ordemSimetrica(No raiz){
        if(raiz != null){
            ordemSimetrica(raiz.esquerda);
            ordemSimetrica(raiz.direita);
            System.out.println(raiz.dado + " ");
        }
    }
}
