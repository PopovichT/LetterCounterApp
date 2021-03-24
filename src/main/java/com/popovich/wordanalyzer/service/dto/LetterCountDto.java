package com.popovich.wordanalyzer.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LetterCountDto {

    private Character letter;
    private Integer count;

    @Override
    public String toString(){
        return (letter.toString() +" "+ count.toString());
    }



}
