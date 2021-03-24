package com.popovich.wordanalyzer.service;

import com.popovich.wordanalyzer.service.dto.LetterCountDto;
import com.popovich.wordanalyzer.service.exception.EmptyInputStringException;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class WordAnalysisService {

    public LetterCountDto findMostCommondLetterAndCount(String input) {

        if ("".equals(input)) {
            throw new EmptyInputStringException();
        }

        var letterToLetterCount = getLetterCounts(input);
        var maxCount = getBiggestMapValue(letterToLetterCount);

        //Get set of letters which have most count in word for word "dooggs" this will return a set {o,g}
        var maxCountLetters = getLettersWithMaxOccuranceCount(maxCount, letterToLetterCount);

        var lastMaxCountLetter = getLastMaxCountLetter(input, maxCountLetters);
        return new LetterCountDto(lastMaxCountLetter, maxCount);
    }
    private Character getLastMaxCountLetter(String input, Set<Character> maxCountLetters){
        var inputArr = input.toCharArray();
        Character lastLetterWithMaxCount = null;

        //We go from the end of the word to its start in order to
        //find the first letter from maxCountLetters to match
        for (int i = inputArr.length - 1; i >= 0; i--) {

            var letter = inputArr[i];
            if (maxCountLetters.contains(letter)) {
                lastLetterWithMaxCount = letter;
                break;
            }
        }
        return  lastLetterWithMaxCount;
    }


    private Integer getBiggestMapValue(Map<Character, Integer> map) {
        return Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getValue();
    }

    private Map<Character, Integer> getLetterCounts(String input) {
        var characterCounts = new HashMap<Character, Integer>();
        for (char letter : input.toCharArray()) {
            characterCounts.merge(letter, 1, Integer::sum);
        }
        return characterCounts;
    }

    private Set<Character> getLettersWithMaxOccuranceCount(int maxCount, Map<Character, Integer> characterCounts) {
        var maxCountLetters = new HashSet<Character>();

        for (Map.Entry<Character, Integer> entry : characterCounts.entrySet()) {
            if (entry.getValue().equals(maxCount)) {
                maxCountLetters.add(entry.getKey());
            }
        }

        return maxCountLetters;
    }
}

