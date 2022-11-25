package com.server.otp.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.server.otp.response.ApiResponse;
import com.server.otp.response.ResponseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest
@ContextConfiguration(classes = {ResponseManager.class})
public class AbstractTest {
    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    protected MockMvc mvc;

    protected String getBaseUrl(){
        return  "/otp";
    }


    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        return objectMapper.readValue(json, clazz);
    }

    protected void listTest(String uri)
    {
        /*MvcResult mvcResult = getQuery(uri);
        Assert.assertNotNull(mvcResult);
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        CustumApiResponse response = getResponse(mvcResult);
        System.out.println("T "+response.getData().size());
        assertTrue(response.getData().size() >= 0);*/
    }

    /*
    protected List listTests(String uri){
        MvcResult mvcResult = getQuery(uri);
        Assert.assertNotNull(mvcResult);
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        CustumApiResponse response = getResponse(mvcResult);
        System.out.println("T "+response.getData().size());
        assertTrue(response.getData().size() >= 0);
        return  response.getData();
    }*/

    protected  void  addTest(String uri, Object object) {
        try{
            MvcResult mvcResult = null;
            if(object instanceof HashMap){
                mvcResult = postQuery(uri,(HashMap) object);
            }else{
                String inputJson = mapToJson(object);
                mvcResult = postQuery(uri,inputJson);
            }

            assertNotNull(mvcResult);
            int status = mvcResult.getResponse().getStatus();
            assertEquals(200, status);
            ApiResponse response = getResponse(mvcResult);
            //assertEquals(getEncodedMessage(response.getMessage()), "Opération réalisée avec succès");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    protected  void  updateTest(String uri, Object object) {
        try{
            MvcResult mvcResult = null;
            if(object instanceof HashMap)
            {
                mvcResult = putQuery(uri,(HashMap) object);
            }
            else
            {
                String inputJson = mapToJson(object);
                mvcResult = postQuery(uri,inputJson);
            }
            assertNotNull(mvcResult);
            int status = mvcResult.getResponse().getStatus();
            assertEquals(200, status);
            ApiResponse response = getResponse(mvcResult);
            assertEquals(getEncodedMessage(response.getMessage()), "Opération réalisée avec succès");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    String getEncodedMessage(String message){
        String rawString = message;
        byte[] bytes = rawString.getBytes(StandardCharsets.ISO_8859_1);

        String utf8EncodedString = new String(bytes, StandardCharsets.UTF_8);
        return utf8EncodedString;
    }

    protected  void  deleteTest(String uri) {
        try{

            MvcResult mvcResult = null;
            mvcResult = deleteQuery(uri);
            assertNotNull(mvcResult);
            int status = mvcResult.getResponse().getStatus();
            assertEquals(200, status);
            ApiResponse response = getResponse(mvcResult);
            assertEquals(getEncodedMessage(response.getMessage()), "Opération réalisée avec succès");

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
    public MvcResult postQuery(String uri, Object inputJson) throws Exception {
        MvcResult mvcResult = null;
        try{
            mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson)).andExpect(status().isOk()).andReturn();
        }catch (Exception e){
            e.printStackTrace();
        }
        return mvcResult;
    }*/

    public MvcResult postQuery(String uri, String inputJson) throws Exception {
        MvcResult mvcResult = null;
        try{
            mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson)).andExpect(status().isOk()).andReturn();
        }catch (Exception e){
            e.printStackTrace();
        }
        return mvcResult;
    }

    public MvcResult postQuery(String uri, HashMap map) throws Exception {
        MvcResult mvcResult = null;

        RequestBuilder request = post(uri)
                .flashAttrs(map);
        try{
            mvcResult = mvc.perform(request).andReturn();
        }catch (Exception e){
            e.printStackTrace();
        }

        return mvcResult;
    }

    public MvcResult putQuery(String uri, HashMap map) throws Exception {
        MvcResult mvcResult = null;

        RequestBuilder request = put(uri)
                .flashAttrs(map);
        try{
            mvcResult = mvc.perform(request).andReturn();
        }catch (Exception e){
            e.printStackTrace();
        }

        return mvcResult;
    }

    public MvcResult deleteQuery(String uri) throws Exception {
        MvcResult mvcResult = null;

        RequestBuilder request = delete(uri);
        try{
            mvcResult = mvc.perform(request).andReturn();
        }catch (Exception e){
            e.printStackTrace();
        }

        return mvcResult;
    }


    public MvcResult getQuery(String uri)
    {
        MvcResult mvcResult = getQuery(uri,null);
        return mvcResult;
    }

    public MvcResult getQuery(String uri, String inputJson){
        MvcResult mvcResult = null;
        try{
            if(inputJson == null){
                inputJson = "";
            }
            mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).content(inputJson)
                    .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
        }catch (Exception e){

        }
        return mvcResult;
    }

    public ApiResponse getResponse(MvcResult mvcResult) {
        ApiResponse response = null;
        try
        {
           String content = mvcResult.getResponse().getContentAsString();
           response = mapFromJson(content,ApiResponse.class);
        }
        catch (Exception e){

        }
        return  response;
    }
}
