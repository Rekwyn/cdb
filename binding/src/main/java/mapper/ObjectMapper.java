package mapper;

import exception.MapperException;
import exception.ValidatorException;

public interface ObjectMapper<O, D> {

	/**
	 * Convert Dto Into Object.
	 * 
	 * @param dto The Dto Object
	 * @return The Business Object
	 * @throws ValidatorException
	 */
	public O mapDtoToObject(D dto) throws MapperException, ValidatorException;

	/**
	 * Convert Object Into Dto.
	 * 
	 * @param obj The Business Object
	 * @return The Dto Object
	 */
	public D mapObjectToDto(O obj);
}
