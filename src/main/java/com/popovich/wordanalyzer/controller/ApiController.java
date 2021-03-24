package com.popovich.wordanalyzer.controller;

import com.popovich.wordanalyzer.service.dto.LetterCountDto;
import com.popovich.wordanalyzer.service.WordAnalysisService;
import com.popovich.wordanalyzer.service.exception.EmptyInputStringException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestController
@RequestMapping("/wordanalyzer")
public class ApiController {

    private final WordAnalysisService service;

    @Autowired
    public ApiController(WordAnalysisService service) {
        this.service = service;
    }

    @GetMapping("/analyze")
    public LetterCountDto analyze(@RequestParam(name = "word") String word) {
        return service.findMostCommondLetterAndCount(word);
    }
}
