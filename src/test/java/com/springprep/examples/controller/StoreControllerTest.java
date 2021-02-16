package com.springprep.examples.controller;

import com.springprep.examples.entity.Pen;
import com.springprep.examples.exception.DataNotFoundException;
import com.springprep.examples.service.StoreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StoreController.class)
class StoreControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private StoreService storeService;

    @Test
    void PostPenReturnsBadRequestWhenInputIsEmpty() throws Exception {

        /*String request="{\n" +
                "  \"Color\": \"Red\",\n" +
                "  \"Price\": 12\n" +
                "}";*/
        ResultActions result = mockMvc.perform(
                post("/api/store").contentType(MediaType.APPLICATION_JSON)
                                  .content(""));

        result.andExpect(status().isBadRequest());

    }
    @Test
    public void PostPenReturnsCreatedStatusWhenInputIsValid() throws Exception {

        String request="{\n" +
                "  \"color\": \"Red\",\n" +
                "  \"price\": 12\n" +
                "}";
        Pen penInput=new Pen();
        penInput.setId(0);
        penInput.setColor("Red");
        penInput.setPrice(12);


        Pen penOutput=new Pen();
        penOutput.setId(1);
        penOutput.setColor("Red");
        penOutput.setPrice(12);



        when(storeService.addPen(penInput)).thenReturn(penOutput);

        ResultActions result = mockMvc.perform(
                post("/api/store").contentType(MediaType.APPLICATION_JSON)
                                  .content(request));

        result.andExpect(status().isCreated());

    }
    @Test
    public void PostPenReturnsURIWhenInputIsValid() throws Exception {

        String request="{\n" +
                "  \"color\": \"Red\",\n" +
                "  \"price\": 12\n" +
                "}";

        Pen penInput=new Pen();
        penInput.setId(0);
        penInput.setColor("Red");
        penInput.setPrice(12);

        Pen penOutput=new Pen();
        penOutput.setId(1);
        penOutput.setColor("Red");
        penOutput.setPrice(12);


        when(storeService.addPen(penInput)).thenReturn(penOutput);

        ResultActions result = mockMvc.perform(
                post("/api/store").contentType(MediaType.APPLICATION_JSON)
                                  .content(request));

        result.andExpect(status().isCreated());

        result.andExpect(header().exists("Location"));
        result.andExpect(header().string("Location","/api/store/1"));
    }

    @Test
    public void GetPenReturnsNotFoundWhenIdDoesntMatch() throws Exception {

        when(storeService.getPen(anyInt())).thenReturn(null);
        ResultActions result = mockMvc.perform(
                get("/api/store/1").contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());

    }
    @Test
    void GetPenReturnsPenWhenIdExists() throws Exception {
        Pen penOutput=new Pen();
        penOutput.setId(1);
        penOutput.setColor("Red");
        penOutput.setPrice(12);

        when(storeService.getPen(anyInt())).thenReturn(penOutput);

        ResultActions result = mockMvc.perform(
                get("/api/store/1").contentType(MediaType.APPLICATION_JSON));

        ;
        result.andExpect(status().isOk());
        String response="{\n" +
                "  \"id\": 1,\n" +
                "  \"color\": \"Red\",\n" +
                "  \"price\": 12\n" +
                "}";
        result.andExpect(content().json(response,false));

    }

    @Test
    void getAllPenWorksForEmptyList() throws Exception {

        when(storeService.getPens()).thenReturn(Collections.emptyList());

        ResultActions result = mockMvc.perform(
                get("/api/store/").contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNoContent());
    }
    @Test
    void getAllPenWorks() throws Exception {

        List<Pen> expectedPens=new ArrayList<>();
        expectedPens.add(new Pen(1,"Red",12));
        expectedPens.add(new Pen(2,"Blue",12));

        when(storeService.getPens()).thenReturn(expectedPens);

        ResultActions result = mockMvc.perform(
                get("/api/store/").contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

    @Test
    void UpdatePenReturnsNotFound() throws Exception {
        Pen penInput=new Pen();
        penInput.setId(1);
        penInput.setColor("Red");
        penInput.setPrice(12);
        when(storeService.updatePen(penInput)).thenThrow(new DataNotFoundException("Record Not Found in Database"));


        String request="{\n" +
                "  \"color\": \"Blue\",\n" +
                "  \"price\": 12\n" +
                "}";

        ResultActions result = mockMvc.perform(
                get("/api/store/1").contentType(MediaType.APPLICATION_JSON).content(request));

        result.andExpect(status().isNotFound());
    }

    @Test
    void UpdatePenReturnsOkWithNewData() throws Exception {
        Pen penInput=new Pen();
        penInput.setColor("Blue");
        penInput.setPrice(12);

        Pen penOutput=new Pen();
        penOutput.setId(1);
        penOutput.setColor("Blue");
        penOutput.setPrice(12);

        String expectedResponse="{\n" +
                "  \"id\": 1,\n" +
                "  \"color\": \"Blue\",\n" +
                "  \"price\": 12\n" +
                "}";

        when(storeService.updatePen(penInput)).thenReturn(penOutput);


        String request="{\n" +
                "  \"color\": \"Blue\",\n" +
                "  \"price\": 12\n" +
                "}";

        ResultActions result = mockMvc.perform(
                put("/api/store/1").contentType(MediaType.APPLICATION_JSON).content(request));

        result.andExpect(status().isOk());

        result.andExpect(content().json(expectedResponse));
    }

    @Test
    void DeletePenReturnNotFound() throws Exception {
        Pen penOutput=new Pen();
        penOutput.setId(1);
        penOutput.setColor("Red");
        penOutput.setPrice(12);

        when(storeService.deletePen(anyInt())).thenThrow(new DataNotFoundException("Record Not Found in Database"));

        ResultActions result = mockMvc.perform(
                delete("/api/store/1").contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());


    }
}