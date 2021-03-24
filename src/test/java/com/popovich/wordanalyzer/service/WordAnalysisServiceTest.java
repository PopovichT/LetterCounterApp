package com.popovich.wordanalyzer.service;

import com.popovich.wordanalyzer.service.dto.LetterCountDto;
import com.popovich.wordanalyzer.service.exception.EmptyInputStringException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class WordAnalysisServiceTest {

    private WordAnalysisService service = new WordAnalysisService();

    @Test
    void testEmptyInputThrowsException() {
        assertThrows(EmptyInputStringException.class, () ->
            service.findMostCommonLetterAndCount("")
        );
    }

    @Test
    public void testCaseOfSingleMostCommonLetterProcessedCorrectly() {
        assertEquals(toDto('a', 1), service.findMostCommonLetterAndCount("a"));
        assertEquals(toDto('b', 2), service.findMostCommonLetterAndCount("bb"));
        assertEquals(toDto('c', 2), service.findMostCommonLetterAndCount("coach"));
        assertEquals(toDto('o', 2), service.findMostCommonLetterAndCount("kongo"));
        assertEquals(toDto('b', 2), service.findMostCommonLetterAndCount("lobby"));
        assertEquals(toDto('a', 3), service.findMostCommonLetterAndCount("lavanda"));
    }

    @Test
    public void testCaseOfSeveralMostCommonLettersProcessedCorrectly() {
        assertEquals(toDto('b', 1), service.findMostCommonLetterAndCount( "ab"));
        assertEquals(toDto('b', 2 ), service.findMostCommonLetterAndCount( "loobby"));
        assertEquals(toDto('r', 2 ), service.findMostCommonLetterAndCount( "repository"));
        assertEquals(toDto('u', 3 ), service.findMostCommonLetterAndCount( "chupachupsplum"));
    }

    private LetterCountDto toDto(Character c, Integer n) {
        return new LetterCountDto(c,n);
    }
}

