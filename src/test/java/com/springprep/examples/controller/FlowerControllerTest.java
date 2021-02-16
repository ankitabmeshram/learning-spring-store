package com.springprep.examples.controller;

import com.springprep.examples.entity.Flower;
import com.springprep.examples.exception.DataNotFoundException;
import com.springprep.examples.service.FlowerService;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FlowerController.class)
class FlowerControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlowerService flowerService;

    @Test
    void ReturnsNotFound_When_FlowerIdDoesNotExist() throws Exception {

        int flowerId = 1;
        when(flowerService.getFlower(flowerId)).thenThrow(
                new DataNotFoundException("Record Not Found in Database"));

        ResultActions result = mockMvc.perform(get("/api/flower/" + flowerId));
        result.andExpect(status().isNotFound());


    }

    @Test
    void getUsingPathVariable_ReturnsOkAndFlowerDetails_When_FlowerIdExist() throws Exception {

        int flowerId = 1;
        String expectedResponse = "{\n" +
                "  \"id\": 1,\n" +
                "  \"name\":\"Rose\",\n" +
                "  \"color\": \"Red\",\n" +
                "  \"price\": 12\n" +
                "}\n";
        Flower flower = new Flower(1, "Rose", "Red", 12);

        when(flowerService.getFlower(flowerId)).thenReturn(flower);

        ResultActions result = mockMvc.perform(get("/api/flower/" + flowerId));

        result.andExpect(status().isOk());
        result.andExpect(content().json(expectedResponse, false));
        verify(flowerService).getFlower(flowerId);


    }

    @Test
    void GetUsingQueryParams_ReturnsOkAndFlowerDetails_When_FlowerIdExist() throws Exception {

        int flowerId = 1;
        String expectedResponse = "[{\n" +
                "  \"id\": 1,\n" +
                "  \"name\":\"Rose\",\n" +
                "  \"color\": \"Red\",\n" +
                "  \"price\": 12\n" +
                "}]\n";
        Flower flower = new Flower(1, "Rose", "Red", 12);

        when(flowerService.getFlower(flowerId)).thenReturn(flower);

        ResultActions result = mockMvc.perform(
                get("/api/flower?flowerId=" + flowerId));

        result.andExpect(status().isOk());
        result.andExpect(content().json(expectedResponse, false));
        verify(flowerService).getFlower(flowerId);


    }


    @Test
    void Get_ReturnsOkAndAllFlowerDetails() throws Exception {

        String expectedResponse = "[{\n" +
                "  \"id\": 1,\n" +
                "  \"name\":\"Rose\",\n" +
                "  \"color\": \"Red\",\n" +
                "  \"price\": 12\n" +
                "}]\n";
        Flower flower = new Flower(1, "Rose", "Red", 12);
        List<Flower> flowers = new ArrayList<Flower>();
        flowers.add(flower);
        when(flowerService.getFlowers()).thenReturn(flowers);

        ResultActions result = mockMvc.perform(get("/api/flower"));

        result.andExpect(status().isOk());
        result.andExpect(content().json(expectedResponse, false));
        verify(flowerService).getFlowers();


    }

    @Test
    void GetUsingHeaders_ReturnsOkAndFlowerDetails_When_FlowersWithGivenNameExist() throws Exception {

        int flowerId = 1;
        String expectedResponse = "[{\n" +
                "  \"id\": 1,\n" +
                "  \"name\":\"Rose\",\n" +
                "  \"color\": \"Red\",\n" +
                "  \"price\": 12\n" +
                "}]\n";
        Flower flower = new Flower(1, "Rose", "Red", 12);
        List<Flower> flowers = new ArrayList<Flower>();
        flowers.add(flower);
        when(flowerService.getFlowerByName("Rose")).thenReturn(flowers);

        ResultActions result = mockMvc.perform(
                get("/api/flower").header("FlowerName", "Rose"));

        result.andExpect(status().isOk());
        result.andExpect(content().json(expectedResponse, false));
        verify(flowerService).getFlowerByName("Rose");


    }


    @Test
    void AddWorks() throws Exception {

        String request = "{\n" +
                "  \"Name\": \"Rose\",\n" +
                "  \"Color\": \"Red\",\n" +
                "  \"Price\": 12\n" +
                "}";
        Flower flowerOutput = new Flower(1, "Rose", "Red", 12);
        int expectedId = flowerOutput.getId();

        when(flowerService.addFlower(any(Flower.class))).thenReturn(
                flowerOutput);
        ResultActions result = mockMvc.perform(
                post("/api/flower").contentType(MediaType.APPLICATION_JSON)
                                   .content(request));

        result.andExpect(status().isCreated());
        result.andExpect(
                header().string("Location", "/api/flower/" + expectedId));
    }

    @Test
    void DeleteWorks() throws Exception {

        String request = "{\n" +
                "  \"Name\": \"Rose\",\n" +
                "  \"Color\": \"Red\",\n" +
                "  \"Price\": 12\n" +
                "}";
        Flower flowerOutput = new Flower(1, "Rose", "Red", 12);
        int expectedId = flowerOutput.getId();

        int flowerId = 1;
        when(flowerService.delete(flowerId)).thenReturn(true);

        ResultActions result = mockMvc.perform(
                delete("/api/flower/{id}", flowerId));

        result.andExpect(status().isNoContent());
        verify(flowerService).delete(flowerId);
    }

    @Test
    void UpdateWorks() throws Exception {

        String request = "{\n" +
                "  \"Id\": 1,\n" +
                "  \"Name\": \"Rose\",\n" +
                "  \"Color\": \"Red\",\n" +
                "  \"Price\": 12\n" +
                "}";
        Flower flowerOutput = new Flower(1, "Rose1", "Red1", 13);
        int expectedId = flowerOutput.getId();

        int flowerId = 1;
        when(flowerService.update(any(Flower.class))).thenReturn(flowerOutput);

        ResultActions result = mockMvc.perform(
                put("/api/flower/").content(request).contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        verify(flowerService).update(any(Flower.class));
    }

}