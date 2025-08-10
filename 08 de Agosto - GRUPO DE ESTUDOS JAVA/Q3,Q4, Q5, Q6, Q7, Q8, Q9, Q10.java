// Questões 06 e 07: Classe para atualizar contas e gerar um relatório
public class AtualizadorDeContas {
    private double saldoTotal = 0;
    private double selic;

    public AtualizadorDeContas(double selic) {
        this.selic = selic;
    }

    // Método para atualizar uma conta, imprimir saldos e somar ao saldoTotal
    public void roda(Conta c) {
        System.out.println("Saldo anterior: R$ " + c.getSaldo());
        c.atualiza(this.selic);
        System.out.println("Saldo final: R$ " + c.getSaldo());
        this.saldoTotal += c.getSaldo();
    }

    // Getter para o saldo total do banco
    public double getSaldoTotal() {
        return this.saldoTotal;
    }

    // Questão 09: Se precisássemos de uma nova classe ContaInvestimento, não 
    // precisaríamos alterar esta classe. Bastaria que ContaInvestimento 
    // herdasse de Conta e reescrevesse o método atualiza().
}

// Questão 10: Classe Banco para gerenciar várias contas
public class Banco {
    private Conta[] contas;
    private int numContas;

    public Banco(int capacidade) {
        this.contas = new Conta[capacidade];
        this.numContas = 0;
    }

    // Adiciona uma conta ao banco
    public void adiciona(Conta c) {
        if (this.numContas < this.contas.length) {
            this.contas[this.numContas] = c;
            this.numContas++;
        } else {
            System.out.println("Banco cheio! Não foi possível adicionar a conta.");
        }
    }

    // Retorna uma conta a partir de um índice
    public Conta pegaConta(int indice) {
        return this.contas[indice];
    }

    // Retorna o número total de contas no banco
    public int pegaTotalDeContas() {
        return this.numContas;
    }
}

// Questões 04, 05, 07 e 10: Classe principal para testar todas as funcionalidades
public class TestaContas {
    public static void main(String[] args) {
        // Questão 04: Instanciando e testando as classes
        System.out.println("--- Teste Simples (Questão 04) ---");
        Conta c = new Conta();
        ContaCorrente cc = new ContaCorrente();
        ContaPoupanca cp = new ContaPoupanca();

        c.deposita(1000);
        cc.deposita(1000);
        cp.deposita(1000);

        System.out.println("Saldo antes da atualização:");
        System.out.println("Conta comum: R$ " + c.getSaldo());
        System.out.println("Conta Corrente: R$ " + cc.getSaldo());
        System.out.println("Conta Poupança: R$ " + cp.getSaldo());

        c.atualiza(0.01);
        cc.atualiza(0.01);
        cp.atualiza(0.01);

        System.out.println("\nSaldo depois da atualização (taxa de 1%):");
        System.out.println("Conta comum: R$ " + c.getSaldo());
        System.out.println("Conta Corrente: R$ " + cc.getSaldo());
        System.out.println("Conta Poupança: R$ " + cp.getSaldo());
        
        // O que acontece?
        // Conta comum: R$ 1000 * 1.01 = R$ 1010
        // Conta Corrente: (R$ 1000 - R$ 0.10) * (1 + 0.01*2) = R$ 999.90 * 1.02 = R$ 1019.898
        // Conta Poupança: R$ 1000 * (1 + 0.01*3) = R$ 1000 * 1.03 = R$ 1030

        // Questão 05: Utilizando polimorfismo
        System.out.println("\n--- Teste de Polimorfismo (Questão 05) ---");
        // O código compila e roda perfeitamente. Uma referência de um tipo mais genérico (Conta)
        // pode apontar para um objeto de um tipo mais específico (ContaCorrente ou ContaPoupanca).
        // A JVM, em tempo de execução, invoca o método correto da classe específica (o override).
        Conta c2 = new Conta();
        Conta c3 = new ContaCorrente();
        Conta c4 = new ContaPoupanca();

        c2.deposita(100);
        c3.deposita(100);
        c4.deposita(100);
        
        System.out.println("Saldo c2 (Conta): R$ " + c2.getSaldo());
        System.out.println("Saldo c3 (ContaCorrente): R$ " + c3.getSaldo());
        System.out.println("Saldo c4 (ContaPoupanca): R$ " + c4.getSaldo());

        // Questões 07 e 10: Utilizando o AtualizadorDeContas e a classe Banco
        System.out.println("\n--- Teste com Banco e Atualizador de Contas (Questões 07 e 10) ---");
        
        // Criando as contas para o banco
        Conta conta1 = new Conta();
        Conta conta2 = new ContaCorrente();
        Conta conta3 = new ContaPoupanca();

        conta1.deposita(5000);
        conta2.deposita(5000);
        conta3.deposita(5000);

        // Criando o banco e adicionando as contas
        Banco banco = new Banco(3);
        banco.adiciona(conta1);
        banco.adiciona(conta2);
        banco.adiciona(conta3);

        // Criando o atualizador de contas com taxa de 1% (0.01)
        AtualizadorDeContas adc = new AtualizadorDeContas(0.01);
        
        // Percorrendo as contas do banco e atualizando-as
        for (int i = 0; i < banco.pegaTotalDeContas(); i++) {
            System.out.println("\nAtualizando conta de índice " + i);
            adc.roda(banco.pegaConta(i));
        }

        System.out.println("\nSaldo Total do Banco após atualização: R$ " + adc.getSaldoTotal());
    }
}