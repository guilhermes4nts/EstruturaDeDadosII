package ArvoreBinariaDeBusca;

public class ArvoreBinariaBusca<T> implements IArvoreBinaria {
    // Criação da raiz.
    No raiz;
    // Criação de uma variável que vai auxiliar para saber se a arvore é completa.
    int quantidadeFolhas;

    // Inicialização de algumas variáveis no construtor.
    public ArvoreBinariaBusca(){
        raiz = null;
        quantidadeFolhas = 0;
    }


    // Metodo para inserir um no na arvore.
    // Se a arvore estiver vazia ele inicializa a raiz.
    // Caso contrario ele chama um metodo privado que faz a inserção desse no na arvore.
    // E ao fiz desse metodo a quantidade de fohas é incrementada em sua variável.
    @Override
    public void inserir(No no) throws Exception {
        if (estaVazia()){
            raiz = no;
            raiz.setPai(null);
        }else {
            inserirRec(raiz, no);
        }
        quantidadeFolhas++;
    }

    // Esse metodo privado temo como objetivo inserir um elemento em uma arvore ja inicializada.
    // Ele tem como parametro a raiz e o novo no a ser adicionado.
    // Nesse metodo é utilizado o Compare to para comparar os valores do no atual como o novo no, para
    // definir em qual lado ele será adicionado.
    private void inserirRec(No atual, No no){
        // Caso o dado do No a ser adicionado for menor que o dado do No atual
        // ele sera adicionado a esquerda.
        // Caso contrário ele será adicionado a direita.
        if (atual.compareTo(no) == 1) {
            if (atual.getFilhoEsq() == null){
                atual.setFilhoEsq(no);
                no.setPai(atual);
            }else{
                inserirRec(atual.getFilhoEsq(), no);
            }
        } else {
            if (atual.getFilhoDir() == null){
                atual.setFilhoDir(no);
                no.setPai(atual);
            }else {
                inserirRec(atual.getFilhoDir(), no);
            }
        }
    }

    // Metodo para remover um nó de uma arvore.
    // Nesse metodo há 3 casos de remoção: 1- remover um No que não tem filhos
    // 2- remover um No que tem pelo menos um filho, 3- remover um No com dois filhos.
    // E ao fim desse metodo é decrementado a quantidade de folhas da arvores.
    @Override
    public void remover( No no) throws NoInexistenteException, ArvoreVazia{
        // Aqui é encontrado todas as informações desse No para que saibamos em qual caso
        // ele será removido.
        No noEncontrado = visitar(no);
        No pai = noEncontrado.getPai();
        No filhoEsq = noEncontrado.getFilhoEsq();
        No filhoDir = noEncontrado.getFilhoDir();

        // Criando um contador para saber quantos filhos esse No tem.
        int cont = 0;
        if (filhoEsq != null){
            cont++;
        }
        if (filhoDir != null){
            cont++;
        }

        // Removendo No que não tem filhos da arvore.
        if (cont == 0){
            // Se entra nesse if significa que a arvore so tem um elemento que é a raiz
            // e para remove-la é só dizer que a raiz é null
            if (noEncontrado == raiz){
                raiz = null;
            }else {
                // Verificando qual é a posição que esse No é em relação a seu pai
                // para poder remover ele.
                // Se cair nesse if quer dizer que o filho esquerdo é o no a ser removido
                if (pai.getFilhoEsq() == noEncontrado){
                    pai.setFilhoEsq(null);
                }else{
                    pai.setFilhoDir(null);
                }
            }


        // Removendo No que tem pelo menos 1 filho da arvore.
        }else if (cont == 1){
            //Verifica se o no como um filho a ser removido é a raiz.
            if (raiz == noEncontrado){
                if (filhoEsq != null){
                    raiz = filhoEsq;
                }else{
                    raiz = filhoDir;
                }
            }else {
                if (filhoEsq != null){
                    pai.setFilhoEsq(filhoEsq);
                }else {
                    pai.setFilhoDir(filhoDir);
                }
            }
        // Removendo No que tem 2 filhos da arvore.
        }else {
            // Buscando qual é o maior elemento da subarvore esquerda.
            // e substituindo o dado do No a ser removido pelo maior dado
            // da subarvore a esquerda e depois removendo-o
            No noAtual = noEncontrado.getFilhoEsq();
            while (noAtual.getFilhoDir() != null) {
                noAtual = noAtual.getFilhoDir();
            }
            No aux = noAtual;
            remover(noAtual);
            noEncontrado.setDado(aux.getDado());
        }

        quantidadeFolhas--;
    }

    // Nesse metodo é utilizado um valor a ser buscado e retorna o No correspondente a esse valor
    @Override
    public No buscar(int dado) throws NoInexistenteException, ArvoreVazia{
        // Se a raiz for nula é lancada uma excessão de ArvoreVazia
        if (raiz == null){
            throw new ArvoreVazia();
        }else{
            // Faz a busca desse valor atravez de um metodo auxiliar e recussivo buscarRec()
            No aux = buscarRec(raiz, dado);
            // Caso o No retornado for null quer dizer que esse valor não foi encontrado na
            // arvore e é lancada uma excessão de NoInexistente.
            // Caso contrario é retornado o No pertencente a esse valor
            if (aux == null){
                throw new NoInexistenteException();
            }else {
                return aux;
            }
        }
    }

    // Metodo auxiliar do metodo buscar()
    private No buscarRec(No atual, int dado) {
        // Caso o No atual seja null ou o dado do No atual seja igual a dado, o No Atual é retornado
        if (atual == null || atual.getDado() == dado) {
            return atual;
        }

        if (dado <= atual.getDado()) {
            return buscarRec(atual.getFilhoEsq(), dado);
        } else {
            return buscarRec(atual.getFilhoDir(), dado);
        }
    }

    // Esse método tem como função buscar e retornar um No na arvore, isso é feito atraves
    // de um metodo axiliar e recussivo visitarRec
    @Override
    public No visitar(No no) throws NoInexistenteException, ArvoreVazia {
        // Se a arvore estiver vazia lança uma excessão ArvoreVazia()
        if (raiz == null){
            throw new ArvoreVazia();
        }else{
            // Faz a buscar do No
            No aux = visitarRec(raiz, no);
            // Caso o retorno do metodo for nulo quer dizer que esse No não existe na arvore
            // Caso o retorno não for nulo é retornado esse No
            if (aux == null) {
                throw new NoInexistenteException();
            }else{
                return aux;
            }
        }
    }

    // Metodo auxiliar do metodo visitar()
    private No visitarRec(No atual, No no){
        // Aqui é feito uma comparação do No atual(que inicialmente é a raiz) com o no a ser visitado.
        // Se o no for null ou for igual a atual ele é retornado.
        if (atual == no || atual == null){
            return no;
        }
        else {
            if(atual.compareTo(no) == 1) {
                return visitarRec(atual.getFilhoEsq(), no);
            } else
                return visitarRec(atual.getFilhoDir(), no);
        }
    }

    @Override
    public boolean estaVazia() {
        return raiz == null;
    }

    @Override
    public boolean ehCompleta(){
        return (quantidadeFolhas == (Math.pow(2, altura() +1) - 1));
    }

    // Calculando a altura atravez de um método privado e recussivo calcularAltura().
    @Override
    public int altura() {
        return calcularAltura(raiz);
    }

    private int calcularAltura(No raiz){
        if (raiz == null) {
            return -1; // A altura de uma árvore vazia é -1.
        } else {
            int alturaEsquerda = calcularAltura(raiz.getFilhoEsq());
            int alturaDireita = calcularAltura(raiz.getFilhoDir());

            // A altura da árvore é o máximo entre a altura da subárvore esquerda e direita mais 1.
            return Math.max(alturaEsquerda, alturaDireita) + 1;
        }
    }

//    Imprimi os valores da arvore a partir do metodo preOrdem()
    @Override
    public void imprimirArvore() throws ArvoreVazia {
        preOrdem(raiz);
    }

//    Esse metodo imprimi os valores da arvore seguindo a Pre Ordem
    @Override
    public void preOrdem(No raiz) throws ArvoreVazia {
        if(raiz != null) {
            System.out.print(raiz.getDado() + " | ");
            preOrdem(raiz.getFilhoEsq());
            preOrdem(raiz.getFilhoDir());
        }
    }

//    Esse metodo imprimi os valores da arvore seguindo a Ordem Simetrica
    @Override
    public void inOrdem(No raiz) throws ArvoreVazia {
        if(raiz != null) {
            inOrdem(raiz.getFilhoEsq());
            System.out.print(raiz.getDado() + " | ");
            inOrdem(raiz.getFilhoDir());
        }
    }
//    Esse metodo imprimi os valores da arvore seguindo a Pos Ordem
    @Override
    public void posOrdem(No raiz) throws ArvoreVazia {
        if (raiz != null){
            posOrdem(raiz.getFilhoEsq());
            posOrdem(raiz.getFilhoDir());
            System.out.print(raiz.getDado() + " | ");
        }
    }

//    Esse metodo só esta aqui pq se eu remover ele o codigo da erro :|
    @Override
    public int compareTo(Object o) {
        return 0;
    }

}
