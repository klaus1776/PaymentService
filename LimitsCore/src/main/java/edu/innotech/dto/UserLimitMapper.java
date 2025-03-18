package edu.innotech.dto;

import org.modelmapper.ModelMapper;

public class UserLimitMapper {

    private ModelMapper modelMapper;

    public UserLimitMapper() {
        this.modelMapper = new ModelMapper();
    }

    public <D, T> D map(T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }
}
