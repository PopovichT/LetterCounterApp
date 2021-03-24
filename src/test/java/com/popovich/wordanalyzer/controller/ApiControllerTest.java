package com.popovich.wordanalyzer.controller;

import com.popovich.wordanalyzer.service.dto.LetterCountDto;
import com.popovich.wordanalyzer.service.WordAnalysisService;
import com.popovich.wordanalyzer.service.exception.EmptyInputStringException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApiController.class)
class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WordAnalysisService service;

    @Test
    void testValidInputProcessedCorrectly() throws Exception {
        mockWordAnalysisServiceOutput(new LetterCountDto('a', 2));

        mockMvc.perform(get("/wordanalyzer/analyze?word=application"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.letter").value("a"))
                .andExpect(jsonPath("$.count").value("2"))
                .andReturn();

        verifyWordAnalysisServiceCalledWithInput("application");
    }


    @Test
    void testInvalidInputReturnsCode4XX() throws Exception {
        when(service.findMostCommonLetterAndCount(any()))
                .thenThrow(new EmptyInputStringException());

        mockMvc.perform(get("/wordanalyzer/analyze?word="))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    private void mockWordAnalysisServiceOutput(LetterCountDto letterCountDto) {
        when(service.findMostCommonLetterAndCount(any()))
                .thenReturn(letterCountDto);
    }

    private void verifyWordAnalysisServiceCalledWithInput(String input) {
        verify(service).findMostCommonLetterAndCount(input);
    }
}
