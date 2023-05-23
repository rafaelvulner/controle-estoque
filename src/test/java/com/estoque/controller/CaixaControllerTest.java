package com.estoque.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.estoque.domain.FormaPagamentoEnum;
import com.estoque.domain.dtos.ResumoDeVendasDTO;
import com.estoque.service.CaixaService;
import com.sun.security.auth.UserPrincipal;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CaixaController.class})
@ExtendWith(SpringExtension.class)
class CaixaControllerTest {
    @Autowired
    private CaixaController caixaController;

    @MockBean
    private CaixaService caixaService;

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/consultarLancamentosPorFormaDePagamento");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("formaPagamento",
                String.valueOf(FormaPagamentoEnum.DEBITO));
        MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"dataDaVenda\":null,\"total\":null,\"formaPagamento\":null}"));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento2() throws Exception {
        LocalDate dataDaVenda = LocalDate.of(1970, 1, 1);
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO(dataDaVenda, BigDecimal.valueOf(42L), FormaPagamentoEnum.DEBITO));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/consultarLancamentosPorFormaDePagamento");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("formaPagamento",
                String.valueOf(FormaPagamentoEnum.DEBITO));
        MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"dataDaVenda\":[1970,1,1],\"total\":42,\"formaPagamento\":\"DEBITO\"}"));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento3() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any())).thenReturn(null);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/consultarLancamentosPorFormaDePagamento");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("formaPagamento",
                String.valueOf(FormaPagamentoEnum.DEBITO));
        MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento4() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/consultarLancamentosPorFormaDePagamento",
                "Uri Variables");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("formaPagamento",
                String.valueOf(FormaPagamentoEnum.DEBITO));
        MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"dataDaVenda\":null,\"total\":null,\"formaPagamento\":null}"));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento5() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/consultarLancamentosPorFormaDePagamento",
                "Uri Variables", "Uri Variables");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("formaPagamento",
                String.valueOf(FormaPagamentoEnum.DEBITO));
        MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"dataDaVenda\":null,\"total\":null,\"formaPagamento\":null}"));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento6() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/consultarLancamentosPorFormaDePagamento",
                "Uri Variables", "Uri Variables", "Uri Variables");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("formaPagamento",
                String.valueOf(FormaPagamentoEnum.DEBITO));
        MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"dataDaVenda\":null,\"total\":null,\"formaPagamento\":null}"));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento7() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/consultarLancamentosPorFormaDePagamento");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("formaPagamento", String.valueOf("?"));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento8() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/consultarLancamentosPorFormaDePagamento");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("formaPagamento", String.valueOf("Obj"));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento9() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/consultarLancamentosPorFormaDePagamento");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("formaPagamento", String.valueOf((Object) 42));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento10() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/consultarLancamentosPorFormaDePagamento");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("formaPagamento", String.valueOf((Object) 1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento11() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/consultarLancamentosPorFormaDePagamento");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("formaPagamento", String.valueOf((Object) 0));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

/*    *//** Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)} *//*@Test void testConsultarLancamentosPorFormaDePagamento12() throws Exception {when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any())).thenReturn(new ResumoDeVendasDTO());MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/consultarLancamentosPorFormaDePagamento"), requestBuilder = getResult.param("formaPagamento", FormaPagamentoEnum.DEBITO);ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(caixaController) .build() .perform(requestBuilder).andExpect(MockMvcResultMatchers.status().is(400)); }*/

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento13() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/consultarLancamentosPorFormaDePagamento");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("formaPagamento",
                String.valueOf((Object) Integer.MIN_VALUE));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento14() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/consultarLancamentosPorFormaDePagamento");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("formaPagamento", String.valueOf("42"));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento15() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/consultarLancamentosPorFormaDePagamento");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("formaPagamento", String.valueOf(""));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento16() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/consultarLancamentosPorFormaDePagamento")
                .param("formaPagamento", "https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento17() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/consultarLancamentosPorFormaDePagamento")
                .param("formaPagamento", "42");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento18() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/consultarLancamentosPorFormaDePagamento")
                .param("formaPagamento", "?");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento19() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/consultarLancamentosPorFormaDePagamento")
                .param("formaPagamento", "Values");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento20() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/consultarLancamentosPorFormaDePagamento")
                .param("formaPagamento", "");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento21() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/consultarLancamentosPorFormaDePagamento")
                .param("formaPagamento", "https://example.org/example", "42");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento22() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/consultarLancamentosPorFormaDePagamento")
                .param("formaPagamento", "https://example.org/example", "42", "https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento23() throws Exception {
        ResumoDeVendasDTO resumoDeVendasDTO = new ResumoDeVendasDTO();
        resumoDeVendasDTO.setDataDaVenda(LocalDate.of(1970, 1, 1));
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(resumoDeVendasDTO);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/consultarLancamentosPorFormaDePagamento");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("formaPagamento",
                String.valueOf(FormaPagamentoEnum.DEBITO));
        MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"dataDaVenda\":[1970,1,1],\"total\":null,\"formaPagamento\":null}"));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento24() throws Exception {
        ResumoDeVendasDTO resumoDeVendasDTO = new ResumoDeVendasDTO();
        resumoDeVendasDTO.setTotal(BigDecimal.valueOf(42L));
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(resumoDeVendasDTO);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/consultarLancamentosPorFormaDePagamento");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("formaPagamento",
                String.valueOf(FormaPagamentoEnum.DEBITO));
        MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"dataDaVenda\":null,\"total\":42,\"formaPagamento\":null}"));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento25() throws Exception {
        ResumoDeVendasDTO resumoDeVendasDTO = new ResumoDeVendasDTO();
        resumoDeVendasDTO.setFormaPagamento(FormaPagamentoEnum.DEBITO);
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(resumoDeVendasDTO);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/consultarLancamentosPorFormaDePagamento");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("formaPagamento",
                String.valueOf(FormaPagamentoEnum.DEBITO));
        MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"dataDaVenda\":null,\"total\":null,\"formaPagamento\":\"DEBITO\"}"));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento26() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/consultarLancamentosPorFormaDePagamento");
        getResult.secure(true);
        MockHttpServletRequestBuilder requestBuilder = getResult.param("formaPagamento",
                String.valueOf(FormaPagamentoEnum.DEBITO));
        MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"dataDaVenda\":null,\"total\":null,\"formaPagamento\":null}"));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento27() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/consultarLancamentosPorFormaDePagamento");
        getResult.content("AXAXAXAX".getBytes("UTF-8"));
        MockHttpServletRequestBuilder requestBuilder = getResult.param("formaPagamento",
                String.valueOf(FormaPagamentoEnum.DEBITO));
        MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"dataDaVenda\":null,\"total\":null,\"formaPagamento\":null}"));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento28() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/consultarLancamentosPorFormaDePagamento");
        getResult.content("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("formaPagamento",
                String.valueOf(FormaPagamentoEnum.DEBITO));
        MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"dataDaVenda\":null,\"total\":null,\"formaPagamento\":null}"));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento29() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/consultarLancamentosPorFormaDePagamento");
        getResult.contentType("text/plain");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("formaPagamento",
                String.valueOf(FormaPagamentoEnum.DEBITO));
        MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"dataDaVenda\":null,\"total\":null,\"formaPagamento\":null}"));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento30() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/consultarLancamentosPorFormaDePagamento");
        getResult.accept("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("formaPagamento",
                String.valueOf(FormaPagamentoEnum.DEBITO));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento31() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/consultarLancamentosPorFormaDePagamento");
        getResult.session(new MockHttpSession());
        MockHttpServletRequestBuilder requestBuilder = getResult.param("formaPagamento",
                String.valueOf(FormaPagamentoEnum.DEBITO));
        MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"dataDaVenda\":null,\"total\":null,\"formaPagamento\":null}"));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento32() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/consultarLancamentosPorFormaDePagamento");
        getResult.principal(new UserPrincipal("principal"));
        MockHttpServletRequestBuilder requestBuilder = getResult.param("formaPagamento",
                String.valueOf(FormaPagamentoEnum.DEBITO));
        MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"dataDaVenda\":null,\"total\":null,\"formaPagamento\":null}"));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento33() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/consultarLancamentosPorFormaDePagamento");
        getResult.characterEncoding("UTF-8");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("formaPagamento",
                String.valueOf(FormaPagamentoEnum.DEBITO));
        MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"dataDaVenda\":null,\"total\":null,\"formaPagamento\":null}"));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento34() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/consultarLancamentosPorFormaDePagamento");
        getResult.characterEncoding("Encoding");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("formaPagamento",
                String.valueOf(FormaPagamentoEnum.DEBITO));
        MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"dataDaVenda\":null,\"total\":null,\"formaPagamento\":null}"));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento35() throws Exception {
        when(caixaService.consultarLancamentosPorFormaDePagamento(Mockito.<FormaPagamentoEnum>any()))
                .thenReturn(new ResumoDeVendasDTO());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/consultarLancamentosPorFormaDePagamento");
        getResult.characterEncoding("42");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("formaPagamento",
                String.valueOf(FormaPagamentoEnum.DEBITO));
        MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"dataDaVenda\":null,\"total\":null,\"formaPagamento\":null}"));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosDiario()}
     */
    @Test
    void testConsultarLancamentosDiario() throws Exception {
        when(caixaService.consultarLancamentosDiario()).thenReturn(BigDecimal.valueOf(42L));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/consultarLancamentosDiario");
        MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("42"));
    }

    /**
     * Method under test: {@link CaixaController#consultarLancamentosPorData(LocalDate)}
     */
    @Test
    void testConsultarLancamentosPorData() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/consultarLancamentosPorData");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("data", String.valueOf((Object) null));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(caixaController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

