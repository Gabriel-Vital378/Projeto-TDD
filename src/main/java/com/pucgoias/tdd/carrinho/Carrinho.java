package com.pucgoias.tdd.carrinho;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private final List<ItemCarrinho> itens = new ArrayList<>();

    public double calcularTotal() {
        double total = 0.0;
        for (ItemCarrinho item : itens) {
            total += item.getSubtotal();
        }
        return total;
    }

    public void adicionarItem(Produto produto, int quantidade) throws EstoqueInsuficienteException {
        if (quantidade > produto.getEstoque()) {
            throw new EstoqueInsuficienteException(
                    "Quantidade solicitada maior que o estoque disponivel.");
        }
        itens.add(new ItemCarrinho(produto, quantidade));
    }

    public void removerItem(Produto produto) {
        itens.removeIf(item -> item.getProduto().equals(produto));
    }
}
