package ArvoreBinariaDeBusca;

public interface INo<T> extends Comparable<INo>{

  void setDado(int dado);
  void setPai(No<T> no);
  void setFilhoEsq(No<T> no);
  void setFilhoDir(No<T> no);
  
  int getDado();
  No<T> getPai();
  No<T> getFilhoEsq();
  No<T> getFilhoDir();
  
}