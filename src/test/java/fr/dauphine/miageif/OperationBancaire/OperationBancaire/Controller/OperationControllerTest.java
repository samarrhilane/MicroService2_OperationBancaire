package fr.dauphine.miageif.OperationBancaire.OperationBancaire.Controller;
import fr.dauphine.miageif.OperationBancaire.OperationBancaire.Model.OperationBancaire;
import fr.dauphine.miageif.OperationBancaire.OperationBancaire.OperationBancaireApplication;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import org.junit.Before;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OperationBancaireApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WebAppConfiguration
public class OperationControllerTest {

    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    @Order(1)
    public void getAllOperationBancaire() throws Exception {
        String uri = "/operation-bancaire";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        OperationBancaire[] OperationBancaires= mapFromJson(content, OperationBancaire[].class);
        assertTrue(OperationBancaires.length > 0);
    }

    @Test
    @Order(2)
    public void getOperationBancaireById() throws Exception {
        String uri = "/operation-bancaire/id/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    @Order(4)
    public void getOperationBancaireByDate() throws Exception {
        String uri = "/operation-bancaire/date/2022-06-21";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        OperationBancaire[] OperationBancaires= mapFromJson(content, OperationBancaire[].class);
        assertEquals(3, OperationBancaires.length);
    }


/*
    @Test
    @Order(6)
    public void getOperationBancaireBySourceAndDest() throws Exception {
        String uri = "/operation-bancaire/source/EUR/dest/USD";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        OperationBancaire[] OperationBancaires= mapFromJson(content, OperationBancaire[].class);
        assertEquals(4, OperationBancaires.length);
    }
*/
    @Test
    @Order(7)
    public void getOperationBancaireBySourceAndDestAndDate() throws Exception {
        String uri = "/operation-bancaire/source/FR7630004000031234567890143/dest/FR7610011000201234567890188/date/2022-06-23";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        OperationBancaire[] OperationBancaires= mapFromJson(content, OperationBancaire[].class);
        assertEquals(2, OperationBancaires.length);
    }




/*
    @Test
    @Order(15)
    public void updateMontantForOperationBancaire() throws Exception {
        String uri = "/operation-bancaire/id/1237/montant/200.0";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        OperationBancaire _OperationBancaire = mapFromJson(content, OperationBancaire.class);
        assertEquals(200, status);
        assertEquals(200.0, _OperationBancaire.getMontant());
    }
*/

    @Test
    @Order(16)
    public void updateDateForOperationBancaire() throws Exception {
        String uri = "/operation-bancaire/id/2/date/2022-06-23";


        OperationBancaire OperationBancaire = new OperationBancaire("VIREMENT","FR7630004000031234567890143", "FR7610011000201234567890188", 101.0, "gratuit");

        String inputJson = mapToJson(OperationBancaire);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        OperationBancaire _OperationBancaire = mapFromJson(content, OperationBancaire.class);
        OperationBancaire.setId(2L);
        assertEquals(200, status);
        assertEquals(OperationBancaire, _OperationBancaire);
    }

    @Test
    @AfterAll
    public void deleteOperationBancaireById() throws Exception {
        String uri = "/operation-bancaire/id/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);
    }


}
