package org.j4guanatos.spring.boot.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**************************************************************************
 * Mapper.java
 *
 * @since Jul 8, 2016
 **************************************************************************/
public interface Mapper<D, M> {

	static final Logger logger = LoggerFactory.getLogger(Mapper.class);

	public M toModel(D dto);

	public D toDto(M model);

}
