package es.vir2al.fwk.fwk.services;

import org.springframework.web.multipart.MultipartFile;
import es.vir2al.fwk.fwk.exceptions.BaseException;

public interface ExcelService<T> {
    
    public void save(MultipartFile file) throws BaseException;

}
