package com.pucgoias.tdd.carrinho;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CarrinhoTest {

    private Carrinho carrinho;
    private Produto produto;

    @BeforeEach
    void setUp() {
        carrinho = new Carrinho();
        produto = new Produto("Caneta", 2.50, 10);
    }

    @Test
    void carrinhoVazioTemTotalZero() {
        assertEquals(0.0, carrinho.calcularTotal());
    }

    @Test
    void deveAumentarTotalAoAdicionarItem() throws Exception {
        carrinho.adicionarItem(produto, 3);
        assertEquals(7.50, carrinho.calcularTotal(), 0.0001);
    }

    @Test
    void deveLancarExcecaoQuandoQuantidadeMaiorQueEstoque() {
        Produto produtoComPoucoEstoque = new Produto("Caneta", 2.50, 5);
        assertThrows(EstoqueInsuficienteException.class,
                () -> carrinho.adicionarItem(produtoComPoucoEstoque, 6));
    }

    @Test
    void deveReduzirTotalAoRemoverItem() throws Exception {
        carrinho.adicionarItem(produto, 3);
        carrinho.removerItem(produto);
        assertEquals(0.0, carrinho.calcularTotal(), 0.0001);
    }
}
