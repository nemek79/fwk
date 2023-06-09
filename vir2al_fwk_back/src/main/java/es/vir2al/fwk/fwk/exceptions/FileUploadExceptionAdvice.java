package es.vir2al.fwk.fwk.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import es.vir2al.fwk.fwk.domain.responses.BaseResponse;
import es.vir2al.fwk.fwk.utils.constants.ResponseConstants;

@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<?> handleMaxSizeException(MaxUploadSizeExceededException exc) {

        BaseResponse response = new BaseResponse();

        response.setCode(ResponseConstants.FILE_TOO_LONG);

        return new ResponseEntity<>(response,HttpStatus.EXPECTATION_FAILED);
    
    }

}
