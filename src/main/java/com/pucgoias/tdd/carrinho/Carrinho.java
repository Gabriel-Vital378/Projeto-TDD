package com.pucgoias.tdd.carrinho;

public class Carrinho {

    private double total = 0.0;

    public double calcularTotal() {
        return total;
    }

    public void adicionarItem(Produto produto, int quantidade) throws EstoqueInsuficienteException {
        if (quantidade > produto.getEstoque()) {
            throw new EstoqueInsuficienteException(
                    "Quantidade solicitada maior que o estoque disponivel.");
        }
        total += produto.getPreco() * quantidade;
    }
}
