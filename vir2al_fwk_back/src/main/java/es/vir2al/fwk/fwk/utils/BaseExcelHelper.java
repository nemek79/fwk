package es.vir2al.fwk.fwk.utils;

import java.io.InputStream;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import es.vir2al.fwk.app.domain.ContactoVO;
import es.vir2al.fwk.fwk.exceptions.BaseException;

public abstract class BaseExcelHelper {
    
    private final String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    protected String[] HEADERs = null;
    protected String SHEET = null;

    public BaseExcelHelper(String[] headers, String sheet) {

        this.HEADERs = headers;
        this.SHEET = sheet;

    }

    public boolean hasExcelFormat(MultipartFile file) {

        if (!this.TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;

    }

    public abstract List<ContactoVO> excelToContactos(InputStream is) throws BaseException;

}
