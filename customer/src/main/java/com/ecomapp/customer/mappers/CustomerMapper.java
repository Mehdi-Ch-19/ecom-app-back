package com.ecomapp.customer.mappers;

import com.ecomapp.customer.Dto.CustomerDto;
import com.ecomapp.customer.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper mapper = Mappers.getMapper(CustomerMapper.class);
    CustomerDto todto (Customer customer);

}
