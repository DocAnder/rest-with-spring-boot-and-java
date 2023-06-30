package com.example.restwithspringbootandjava.converters;

public class NumberConverter {

    public static Double convertToDouble(String stringNumber) {
        if(stringNumber == null) return 0D;
        String number = stringNumber.replaceAll(",", ".");
        if(isNumeric(number)) return Double.parseDouble(number);
        return 0D;
    }

    public static boolean isNumeric(String stringNumber) {
        if(stringNumber == null) return false;
        String number = stringNumber.replaceAll(",", ".");
        //Essa regex verificar varias coisas sobre o numero.
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }


}
