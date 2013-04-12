import java.util.NoSuchElementException;

public class ArvoreB {
        private NoB raiz;

        public ArvoreB() {
                raiz = null;
        }

        public void percursoEmOrdem(NoB node) {
                if (node != null) {
                        percursoEmOrdem(node.esquerda);
                        System.out.print(node.chave + " ");
                        percursoEmOrdem(node.direita);
                }
        }

        public void inserir(int valor) {
                NoB y = null; // 
                NoB x = raiz; // 
                while (x != null) {
                        y = x;
                        if (valor < x.chave) {
                                x = x.esquerda;
                        } else {
                                x = x.direita;
                        }
                }
                NoB z = new NoB(valor);
                z.pai = y;
                if (y == null) {
                        raiz = z;
                } else {
                        {
                                if (valor < y.chave) {
                                        y.esquerda = z;
                                } else {
                                        y.direita = z;
                                }
                        }

                }

        }

        public boolean procurarChave(NoB node, int valor) {
                if (node == null)
                        return false;
                if (node.chave == valor)
                        return true;
                if (valor < node.chave) {
                        return procurarChave(node.esquerda, valor);
                } else
                        return procurarChave(node.direita, valor);

        }

        public boolean procurarChave(int valor) {
                NoB x = raiz;
                if (x == null)
                        return false;
                while (valor != x.chave) {
                        if (valor < x.chave) {
                                x = x.esquerda;
                        } else
                                x = x.direita;
                        if (x == null)
                                return false;

                }
                return true;

        }

        public int valorMinimo() {
                NoB node = raiz;
                while (node.esquerda != null) {
                        node = node.esquerda;
                }
                if (node != null)
                        return node.chave;
                else
                        throw new NoSuchElementException();
        }

        public int valorMaximo() {
                NoB node = raiz;
                while (node.direita != null) {
                        node = node.direita;
                }
                if (node != null)
                        return node.chave;
                else
                        throw new NoSuchElementException();
        }

        public static void main(String[] args) {
                ArvoreB arvore = new ArvoreB();
                arvore.inserir(5);
                arvore.inserir(8);
                arvore.inserir(2);
                arvore.inserir(3);
                arvore.inserir(1);
                arvore.inserir(100);
                arvore.inserir(4);
                arvore.inserir(999);
                arvore.inserir(400);
                arvore.inserir(401);
                arvore.inserir(-1000);
                arvore.percursoEmOrdem(arvore.raiz);
                System.out.println();
                System.out.println(arvore.procurarChave(arvore.raiz, -1));
                System.out.println(arvore.procurarChave(arvore.raiz, 1));
                System.out.println(arvore.procurarChave(arvore.raiz, 101));
                System.out.println(arvore.procurarChave(arvore.raiz, 100));
                System.out.println("----------------------------");
                System.out.println(arvore.procurarChave(-1));
                System.out.println(arvore.procurarChave(3));
                System.out.println(arvore.procurarChave(1));
                System.out.println(arvore.procurarChave(101));
                System.out.println(arvore.procurarChave(100));
                System.out.println(arvore.procurarChave(4));
                System.out.println(arvore.procurarChave(7));

                System.out.println("min:" + arvore.valorMinimo());
                System.out.println("max:" + arvore.valorMaximo());
        }
}
