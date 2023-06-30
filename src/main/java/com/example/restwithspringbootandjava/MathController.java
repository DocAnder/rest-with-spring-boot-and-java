package com.example.restwithspringbootandjava;

import com.example.restwithspringbootandjava.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;


//Anotation RestController combina as anotations Controller e ResponseBody, simplificando a implementação da
//controller em aplicações rest, permitindo que sejam retornados objetos e seus dados, que serão armazenados na
//resposta Http (em json, xlm, etc...)
@RestController
public class MathController {

    private static  final String template = "Hello!, %s!";
    private final AtomicLong counter = new AtomicLong();

    //As PathVariable são obrigatóris e irão gerar uma excessão caso não sejam informadas.
    //Alem de anotar no metodo, preciso anotar no mepeamento a espera das variaveis
    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    //Anotation PathVariable permite recuperar na Url as variaveis informadas, no caso, numberOne e numberTwo
    public Double sum (
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception{

        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }


        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    private Double convertToDouble(String stringNumber) {
        if(stringNumber == null) return 0D;
        String number = stringNumber.replaceAll(",", ".");
        if(isNumeric(number)) return Double.parseDouble(number);
        return 0D;
    }

    private boolean isNumeric(String stringNumber) {
        if(stringNumber == null) return false;
        String number = stringNumber.replaceAll(",", ".");
                                    //Essa regex verificar varias coisas sobre o numero.
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    //Testanto a att do nome no git


}
