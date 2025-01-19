/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.acme.mapper;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.acme.model.Order;
import org.acme.model.OrderDTO;

import io.quarkus.security.User;

import org.mapstruct.factory.Mappers;

/**
 * @author Filip Hrisafov
 */
@Mapper
public interface UserMapper {

	UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

	/*
	 * User toUser(UrderDTO urderDTO);
	 * 
	 * @InheritInverseConfiguration UrderDTO fromUser(User user);
	 */
}
