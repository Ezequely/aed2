public class NoB {
        int chave;
        NoB pai;
        NoB esquerda;
        NoB direita;

        public NoB(int chave) {
                super();
                this.chave = chave;
        }

        public NoB(int chave, NoB esquerda, NoB direita) {
                super();
                this.chave = chave;
                this.esquerda = esquerda;
                this.direita = direita;
        }

}
