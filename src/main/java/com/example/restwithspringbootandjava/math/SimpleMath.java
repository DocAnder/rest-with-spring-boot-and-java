package com.example.restwithspringbootandjava.math;

import com.example.restwithspringbootandjava.converters.NumberConverter;
import com.example.restwithspringbootandjava.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class SimpleMath {

    public Double sum (Double numberOne, Double numberTwo){

        return numberOne + numberTwo;
    }


    public Double subtraction (Double numberOne, Double numberTwo) {

        return numberOne - numberTwo;
    }


    public Double multiplication (Double numberOne, Double numberTwo) {
        return numberOne * numberTwo;
    }

    public Double division (Double numberOne, Double numberTwo) {
        return numberOne / numberTwo;
    }

    public Double mean (Double numberOne, Double numberTwo) {

        return (numberOne + numberTwo) / 2;
    }


    public Double sqrt (Double numberOne) {

        return Math.sqrt(numberOne);
    }




}
