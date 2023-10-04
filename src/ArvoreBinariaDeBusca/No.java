package ArvoreBinariaDeBusca;

public class No<T> implements INo<T>{
    public int dado;
    public No<T> pai;
    private No<T> filhoEsq;
    private No<T> filhoDir;

    public No(int dado){
        this.dado = dado;
        this.pai = null;
        this.filhoEsq = null;
        this.filhoDir = null;
    }

    @Override
    public void setDado(int dado){
        this.dado = dado;
    }

    @Override
    public void setPai(No<T> no) {
        this.pai = (No<T>) no;
    }

    @Override
    public void setFilhoEsq(No no) {
        this.filhoEsq = (No<T>) no;
    }

    @Override
    public void setFilhoDir(No no) {
        this.filhoDir = (No<T>) no;
    }

    @Override
    public int getDado() {
        return this.dado;
    }

    @Override
    public No getPai() {
        return this.pai;
    }

    @Override
    public No getFilhoEsq() {
        return this.filhoEsq;
    }

    @Override
    public No getFilhoDir() {
        return this.filhoDir;
    }

    @Override
    public int compareTo(INo no) {
        if (this.dado >= no.getDado()){
            return 1;
        }else {
            return 0;
        }
    }
}
