package com.erp.autenticador;

import com.erp.autenticador.model.response.ModuloResponse;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ModuloControllerTeste {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void QuandoChamarListarModulosDeveRetornar200() throws Exception {
        String url = "/modulo";
        ObjectMapper mapper = new ObjectMapper();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        JavaType listType = mapper.getTypeFactory().constructCollectionType(List.class, ModuloResponse.class);
        List<ModuloResponse> responseList = mapper.readValue(content, listType);
        Assertions.assertNotNull(content);
        Assertions.assertEquals(responseList.size(), 2);
    }

    @Test
    public void QuandoChamarListarModulosComParametroDescricaoDeveRetornar200() throws Exception {
        String url = "/modulo";
        ObjectMapper mapper = new ObjectMapper();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url).param("descricao", "RH"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        JavaType listType = mapper.getTypeFactory().constructCollectionType(List.class, ModuloResponse.class);
        List<ModuloResponse> responseList = mapper.readValue(content, listType);
        Assertions.assertNotNull(content);
        Assertions.assertEquals(responseList.size(),1);
    }
    @Test
    public void QuandoChamarListarModulosEmpresaDeveRetornar200() throws Exception {
        String url = "/modulo";
        ObjectMapper mapper = new ObjectMapper();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url).param("empresaId", "bff7f9ef-ed6a-4681-9fcb-60b5dd55aa5a"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        JavaType listType = mapper.getTypeFactory().constructCollectionType(List.class, ModuloResponse.class);
        List<ModuloResponse> responseList = mapper.readValue(content, listType);
        Assertions.assertNotNull(content);
        Assertions.assertEquals(responseList.size(),0);
    }


}
