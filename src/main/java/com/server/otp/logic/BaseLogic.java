package com.server.otp.logic;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseLogic {

    @Autowired
    ServiceFactory serviceFactory;

    public <M,D,C> C map(D dto, Class classe){
        ModelMapper modelMapper = new ModelMapper();
        C mapped = (C) modelMapper.map(dto, classe);
        return  mapped;
    }
}
