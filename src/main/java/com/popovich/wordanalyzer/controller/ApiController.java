package com.popovich.wordanalyzer.controller;

import com.popovich.wordanalyzer.service.dto.LetterCountDto;
import com.popovich.wordanalyzer.service.WordAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



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
