package com.pucgoias.tdd.carrinho;

public class Carrinho {

    private double total = 0.0;

    public double calcularTotal() {
        return total;
    }

    public void adicionarItem(Produto produto, int quantidade) {
        total += produto.getPreco() * quantidade;
    }
}
