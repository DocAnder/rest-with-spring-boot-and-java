package com.example.restwithspringbootandjava.mapper;


import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class DozerMapper {


    //Inicialização do mapper conforme documentação
    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();


    /*Metodo que irá receber um objeto de origem e a classe do objeto de destino, após,
    * realizar a transferencia dos dados usando o metodo do mapper de origin -> destination*/
    public static <O,D> D parseObject(O origin, Class<D> destination){
        return mapper.map(origin, destination);
    }

    /*Metodo que também irá mapear os dados de origen para destination, mais recebendo uma lista.*/
    public static <O,D> List<D> parseListObjects(List<O> origin, Class<D> destination){
        List<D> destinationObjetcs = new ArrayList<D>();
        for (O o :
                origin) {
            destinationObjetcs.add(mapper.map(o, destination));
        }
        return destinationObjetcs;
    }



}
