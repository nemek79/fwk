package es.vir2al.fwk.fwk.exceptions;

import es.vir2al.fwk.fwk.utils.constants.ResponseConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class BaseException extends Exception {

    private Integer code;

    public BaseException() {
		
		this.code = ResponseConstants.NOT_DEFINED;
		
	}

	public BaseException(Integer code) {
		
		this.code = code;
		
	}

}
