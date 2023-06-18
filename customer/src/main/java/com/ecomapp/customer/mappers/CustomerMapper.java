package com.ecomapp.customer.mappers;

import com.ecomapp.customer.Dto.CustomerDto;
import com.ecomapp.customer.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CustomerMapper {
    CustomerMapper mapper = Mappers.getMapper(CustomerMapper.class);
    @Mapping(target = "adresse",source = ".")
    CustomerDto todto (Customer customer);

}
