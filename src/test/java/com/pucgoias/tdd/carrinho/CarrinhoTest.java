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

    // Item 1
    @Test
    void carrinhoVazioTemTotalZero() {
        assertEquals(0.0, carrinho.calcularTotal());
    }

    // Item 2
    @Test
    void deveAumentarTotalAoAdicionarItem() throws Exception {
        carrinho.adicionarItem(produto, 3);
        assertEquals(7.50, carrinho.calcularTotal(), 0.0001);
    }

    // Item 3
    @Test
    void deveLancarExcecaoQuandoQuantidadeMaiorQueEstoque() {
        Produto produtoComPoucoEstoque = new Produto("Caneta", 2.50, 5);
        assertThrows(EstoqueInsuficienteException.class,
                () -> carrinho.adicionarItem(produtoComPoucoEstoque, 6));
    }

    // Item 4
    @Test
    void deveReduzirTotalAoRemoverItem() throws Exception {
        carrinho.adicionarItem(produto, 3);
        carrinho.removerItem(produto);
        assertEquals(0.0, carrinho.calcularTotal(), 0.0001);
    }

    // Item 5
    @Test
    void deveAplicarCupomDeDescontoAoTotal() throws Exception {
        Produto produtoDezReais = new Produto("Caneta", 10.00, 10);
        carrinho.adicionarItem(produtoDezReais, 2);
        Cupom cupom = new Cupom("DESCONTO10", 0.10);
        carrinho.aplicarCupom(cupom);
        assertEquals(18.00, carrinho.calcularTotal(), 0.0001);
    }

    // Item 6
    @Test
    void deveLancarExcecaoAoAplicarMesmoCupomDuasVezes() throws Exception {
        Produto produtoDezReais = new Produto("Caneta", 10.00, 10);
        carrinho.adicionarItem(produtoDezReais, 2);
        Cupom cupom = new Cupom("DESCONTO10", 0.10);
        carrinho.aplicarCupom(cupom);
        assertThrows(CupomJaAplicadoException.class,
                () -> carrinho.aplicarCupom(cupom));
    }

    // Item 7
    @Test
    void deveLancarExcecaoAoFinalizarCarrinhoVazio() {
        assertThrows(CarrinhoVazioException.class, () -> carrinho.finalizarCompra());
    }
}
