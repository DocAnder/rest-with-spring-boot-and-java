package com.example.restwithspringbootandjava;

import com.example.restwithspringbootandjava.converters.NumberConverter;
import com.example.restwithspringbootandjava.exceptions.UnsupportedMathOperationException;
import com.example.restwithspringbootandjava.math.SimpleMath;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;


//Anotation RestController combina as anotations Controller e ResponseBody, simplificando a implementação da
//controller em aplicações rest, permitindo que sejam retornados objetos e seus dados, que serão armazenados na
//resposta Http (em json, xlm, etc...)
@RestController
public class MathController {

    private static  final String template = "Hello!, %s!";
    private final AtomicLong counter = new AtomicLong();

    private SimpleMath math = new SimpleMath();

    //As PathVariable são obrigatóris e irão gerar uma excessão caso não sejam informadas.
    //Alem de anotar no metodo, preciso anotar no mepeamento a espera das variaveis
    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    //Anotation PathVariable permite recuperar na Url as variaveis informadas, no caso, numberOne e numberTwo
    public Double sum (
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception{

        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)){
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }


        return math.sum(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }



    @RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double subtraction (
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception{

        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)){
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return math.subtraction(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }


    @RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double multiplication (
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception{

        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)){
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return math.multiplication(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/division/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double division (
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception{

        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)){
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return math.division(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/mean/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double mean (
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception{

        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)){
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return math.mean(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }


    @RequestMapping(value = "/sqrt/{numberOne}", method = RequestMethod.GET)
    public Double sqrt (
            @PathVariable(value = "numberOne") String numberOne) throws Exception{

        if(!NumberConverter.isNumeric(numberOne)){
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return math.sqrt(NumberConverter.convertToDouble(numberOne));
    }








}
