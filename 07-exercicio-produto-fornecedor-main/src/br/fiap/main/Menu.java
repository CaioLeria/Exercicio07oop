package br.fiap.main;

import br.fiap.produto.*;
import br.fiap.fornecedor.*;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;

public class Menu {
    private Fornecedor[] fornecedor = new Fornecedor[3];
    private Produto[] produtos = new Produto[2 * fornecedor.length];
    private int indexProduto = 0;
    private int indexFornecedor = 0;

    public void menuPrincipal() {
        int opcao = 0;
        String mensagem = "1. Cadastrar produto\n2. Pesquisar produto\n3.Pesquisar fornecedor\n4.Finalizar";

        while (true) {
            opcao = parseInt(showInputDialog(mensagem));
            if (opcao == 4) {
                return;
            }
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
                default:
                    showMessageDialog(null, "opção inválida");
            }
        }
    }

    public void cadastrarProduto() {
        String nome;
        int qtdEstoque;
        double valorUnitario;
        Fornecedor fornecedor = pesquisarFornecedor();

        if(fornecedor==null){
          fornecedor = cadastrarFornecedor();
        }
        nome = showInputDialog("Nome do produto:\n ");
        qtdEstoque = parseInt(showInputDialog("Quantidade de produtos:\n "));
        valorUnitario = parseDouble(showInputDialog("Qual o preço do produto:\n "));

        produtos[indexProduto] = new Produto(nome,valorUnitario, qtdEstoque, fornecedor);
        indexProduto++;
    }
    public Fornecedor cadastrarFornecedor(){
        String nome = showInputDialog("Nome do fornecedor:");
        long cnpj= parseLong(showInputDialog("CNPJ de fornecedor:"));
        fornecedor[indexFornecedor]=new Fornecedor(nome,cnpj);
        indexFornecedor++;
        return fornecedor[indexFornecedor-1];
    }

    public void pesquisarProduto() {
        String produto = showInputDialog("Nome do produto: ");
        for (int i =0;i<indexProduto;i++){
            if(produto.equalsIgnoreCase(produtos[i].getNome())){
                showMessageDialog(null,"Produto: "+produtos[i].getNome()+"\n Valor: "
                        +produtos[i].getValor()+"\n Fornecedor "+produtos[i].getFornecedor().getNome());
            }else{
                showMessageDialog(null,"produto não encontrado");
            }
        }
    }
    public void pesquisar(){
        Fornecedor fornecedor = pesquisarFornecedor();
        if(fornecedor!= null){
            showMessageDialog(null, fornecedor.getCnpj()+"\n"+fornecedor.getNome());
        }
    }
    private Fornecedor pesquisarFornecedor() {
        long cnpj = parseLong(showInputDialog("CNPJ:"));
        for (int i = 0; i < indexFornecedor; i++) {
            if (fornecedor[i].getCnpj() == cnpj) {
                return fornecedor[i];
            }
            showMessageDialog(null, cnpj + " não encontrado");
        }
        return null;

    }
}
