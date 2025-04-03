import java.text.DecimalFormat;

import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;

public class Util {

    private Fornecedor[] fornecedor = new Fornecedor[5];
    private Produto[] produto = new Produto[10];
    private int indexProduto = 0;
    private int indexFornecedor = 0;

    public void menu() {
        int opcao;
        int resposta;
        String m = "1. Cadastro produto\n" +
                "2. Pesquisar produto\n" +
                "3. Pesquisar fornecedor\n" +
                "4. Finalizar";

        while (true) {
            opcao = parseInt(showInputDialog(m));
            if (opcao == 4) {
                resposta = showConfirmDialog(null, "Tem certeza que deseja finalizar?");
                if (resposta == YES_OPTION) {
                    break;
                }
            }
            if (opcao < 1 || opcao > 4) {
                showMessageDialog(null, "Opção inválida");
            } else {
                switch (opcao) {
                    case 1:
                        cadastrarProduto();
                        break;
                    case 2:
                        pesquisarProduto();
                        break;
                    case 3:
                        pesquisar();
                        break;
                }
            }
        }
    }

    private void pesquisar() {
        Fornecedor fornecedor = pesquisarFornecedor();
        if(fornecedor != null) {
            String aux = "Fornecedor: " + fornecedor.getNome() + "\n";
            aux += "CNPJ: " + fornecedor.getCnpj();
            showMessageDialog(null, aux);
        }
    }

    private void cadastrarProduto() {
        Fornecedor fornecedor = pesquisarFornecedor();
        String nome;
        double valor;
        int qtdEstoque;

        if (fornecedor == null) {
            fornecedor = cadastrarFornecedor();
        }

        nome = showInputDialog("Produto");
        valor = parseDouble(showInputDialog("Valor unitário"));
        qtdEstoque = parseInt(showInputDialog("Quantidade em estoque"));
        produto[indexProduto] = new Produto(nome, valor, qtdEstoque, fornecedor);
        indexProduto++;

    }

    private Fornecedor pesquisarFornecedor() {
        long cnpj = parseLong(showInputDialog("CNPJ"));
        for (int i = 0; i < indexFornecedor; i++) {
            if (fornecedor[i].getCnpj() == cnpj) {
                return fornecedor[i];
            }
        }
        showMessageDialog(null, cnpj + " não encontrado");
        return null;
    }

    private Fornecedor cadastrarFornecedor() {
        String nome;
        long cnpj;
        Fornecedor f = null;
        if (indexFornecedor < fornecedor.length) {
            nome = showInputDialog("Fornecedor");
            cnpj = parseLong(showInputDialog("CNPJ"));
            f = new Fornecedor(nome, cnpj);
            fornecedor[indexFornecedor] = f;
            indexFornecedor++;
        }
        return f;
    }

    private void pesquisarProduto() {
        DecimalFormat df = new DecimalFormat("0.00");
        String nome = showInputDialog("Nome do produto");
        String aux = "";

        for(int i = 0; i < indexProduto; i++) {
            if(produto[i].getNome().equalsIgnoreCase(nome)) {
                aux += "Nome do produto: " + nome + "\n";
                aux += "Preço unitário: R$ " + df.format(produto[i].getValor()) + "\n";
                aux += "Quantidade em estoque: " + produto[i].getQtdEstoque() + "\n";
                aux += "Fornecedor: " + produto[i].getFornecedor().getNome();
                break;
            }
        }
        showMessageDialog(null, aux);
    }

}
