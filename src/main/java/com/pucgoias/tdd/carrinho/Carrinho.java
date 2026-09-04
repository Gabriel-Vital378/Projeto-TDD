package com.pucgoias.tdd.carrinho;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private final List<ItemCarrinho> itens = new ArrayList<>();
    private Cupom cupomAplicado;

    public double calcularTotal() {
        double totalSemDesconto = calcularSubtotalItens();
        return aplicarDesconto(totalSemDesconto);
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

    public void aplicarCupom(Cupom cupom) throws CupomJaAplicadoException {
        if (cupomJaAplicado(cupom)) {
            throw new CupomJaAplicadoException("Este cupom ja foi aplicado.");
        }
        this.cupomAplicado = cupom;
    }

    public void finalizarCompra() throws CarrinhoVazioException {
        if (itens.isEmpty()) {
            throw new CarrinhoVazioException("Nao e possivel finalizar um carrinho vazio.");
        }
    }

    private double calcularSubtotalItens() {
        double subtotal = 0.0;
        for (ItemCarrinho item : itens) {
            subtotal += item.getSubtotal();
        }
        return subtotal;
    }

    private double aplicarDesconto(double total) {
        if (cupomAplicado == null) {
            return total;
        }
        return total - (total * cupomAplicado.getPercentualDesconto());
    }

    private boolean cupomJaAplicado(Cupom cupom) {
        return cupomAplicado != null && cupomAplicado.getCodigo().equals(cupom.getCodigo());
    }
}
