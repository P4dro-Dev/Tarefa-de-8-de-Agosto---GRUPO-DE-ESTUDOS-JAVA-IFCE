// Questões 01 e 02: Classe Conta com atributos e métodos
public class Conta {
    // Repare que, para acessar o atributo saldo nas subclasses, 
    // trocamos o modificador de visibilidade para protected.
    protected double saldo;

    // Métodos para depositar, sacar e pegar o saldo
    public void deposita(double valor) {
        this.saldo += valor;
    }

    public void saca(double valor) {
        this.saldo -= valor;
    }

    public double getSaldo() {
        return this.saldo;
    }

    // Questão 02: Método que atualiza a conta de acordo com uma taxa percentual
    public void atualiza(double taxa) {
        this.saldo += this.saldo * taxa;
    }
}

// Questão 03: Subclasse ContaCorrente
// A ContaCorrente deve atualizar-se com o dobro da taxa e descontar R$0,10 por depósito.
public class ContaCorrente extends Conta {
    // Questão 03: Reescreve o método atualiza, dobrando a taxa.
    // Questão 08: Usando a palavra-chave 'super' para reutilizar o código da classe-mãe.
    @Override
    public void atualiza(double taxa) {
        super.atualiza(taxa * 2);
    }

    // Questão 03: Reescreve o método deposita, descontando uma taxa de 10 centavos.
    @Override
    public void deposita(double valor) {
        this.saldo += valor - 0.10;
    }
}

// Questão 03: Subclasse ContaPoupanca
// A ContaPoupanca deve atualizar-se com o triplo da taxa.
public class ContaPoupanca extends Conta {
    // Questão 03: Reescreve o método atualiza, triplicando a taxa.
    // Questão 08: Usando a palavra-chave 'super' para reutilizar o código da classe-mãe.
    @Override
    public void atualiza(double taxa) {
        super.atualiza(taxa * 3);
    }
}