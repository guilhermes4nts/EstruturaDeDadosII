package ArvoreBinariaDeBusca;

public class main {
    public static void main(String[] args) throws Exception {
        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();

        No raiz = new No(10);
        No oito = new No(8);
        arvore.inserir(raiz);
        arvore.inserir(oito);
        arvore.inserir(new No(9));
        arvore.inserir(new No(6));
        arvore.inserir(new No(7));
        arvore.inserir(new No(20));
        arvore.inserir(new No(15));
        arvore.inserir(new No(16));
        arvore.inserir(new No(12));
        arvore.inserir(new No(13));

        arvore.preOrdem(raiz);
        System.out.println(" ");
        arvore.percorrerEmLargura(raiz);
        System.out.println(" ");
        arvore.remover(oito);
        arvore.imprimirArvore();
        System.out.println(" ");
        arvore.percorrerEmLargura(raiz);
        System.out.println(" ");
        arvore.inserir(new No(8));
        arvore.imprimirArvore();
        System.out.println(" ");
        arvore.percorrerEmLargura(raiz);



    }
}
