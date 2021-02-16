package com.springprep.examples.controller;

import com.springprep.examples.service.CandyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CandyController.class)
class CandyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CandyService candyService;

    @Test
    void ReturnsBadRequest_When_InputIsEmptyList() throws Exception {

        List<Integer> children = new ArrayList<Integer>();
        ResultActions result = mockMvc.perform(
                get("/api/candy").contentType(MediaType.APPLICATION_JSON)
                                 .header("children", children));
        result.andExpect(status().isBadRequest());

    }

    @Test
    void ReturnsStatusOk_When_InputIsValidList() throws Exception {

        List<Integer> children = new ArrayList<Integer>();
        children.add(1);
        children.add(2);
        children.add(2);
        children.add(1);


        ResultActions result = mockMvc.perform(
                get("/api/candy").contentType(MediaType.APPLICATION_JSON)
                                 .header("children", children));
        result.andExpect(status().isOk());

    }

    @Test
    void ReturnsValidCandiesCountInHeader_When_InputIsValidList() throws Exception {

        List<Integer> children = new ArrayList<Integer>();
        children.add(1);
        children.add(2);
        children.add(2);

        when(candyService.getCandies(children)).thenReturn(4);

        ResultActions result = mockMvc.perform(
                get("/api/candy").contentType(MediaType.APPLICATION_JSON)
                                 .header("children", children));
        result.andExpect(header().exists("Count"));
        result.andExpect(header().string("Count", "4"));

        verify(candyService).getCandies(children);

    }

    @Test
    void ReturnsValidCandiesCountInHeader_When_InputIsValidList2() throws Exception {

        List<Integer> children = new ArrayList<Integer>();
        children.add(1);
        children.add(2);
        children.add(3);
        children.add(4);
        children.add(3);
        children.add(2);
        children.add(1);

        when(candyService.getCandies(children)).thenReturn(10);

        ResultActions result = mockMvc.perform(
                get("/api/candy").contentType(MediaType.APPLICATION_JSON)
                                 .header("children", children));
        result.andExpect(header().exists("Count"));
        result.andExpect(header().string("Count", "10"));

        verify(candyService).getCandies(children);

    }
}