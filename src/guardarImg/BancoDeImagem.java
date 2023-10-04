package guardarImg;

public class BancoDeImagem {

    public static void main(String[] args) {
        //declarando a matriz
        int[][] matriz = {{1, 0, 1}, {0, 1, 0}, {0, 0, 1} , {1,0,0,}};
        //imprimindo a matriz
        for (int i = 0; i < matriz.length; i++){
            for (int j = 0; j < matriz[0].length; j++){
                System.out.print(matriz[i][j]);
            }
            System.out.println();
        }

        BancoDeImagem obj = new BancoDeImagem(matriz.length, matriz[0].length);


        System.out.println("Consultando a lista");
        No aux = obj.guardarImg(matriz);
        while (aux != null) {
            System.out.println("Linha: " + aux.linha + ", Coluna: " + aux.coluna);
            aux = aux.prox;
        }
    }
    No inicio;
    No fim;
    public BancoDeImagem(int altura, int largura){
        inicio = null;
        fim = null;

    }

    public No guardarImg(int[][] img){

        int altura = img.length;
        int largura = img[0].length;
        for (int i = 0; i < altura ; i++){
            for (int j = 0; j < largura; j++){
                if (img[i][j] == 1){
                    No novo = new No();
                    novo.linha = i;
                    novo.coluna = j;
                    if (inicio == null){
                        inicio = novo;
                        fim = novo;
                    }else{
                        fim.prox = novo;
                        fim = novo;
                    }
                }
            }
        }

        return inicio;

    }


}


