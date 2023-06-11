package es.vir2al.fwk.app.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import es.vir2al.fwk.app.domain.ContactoVO;
import es.vir2al.fwk.app.utils.Helpers.ContactosExcelHelper;
import es.vir2al.fwk.fwk.exceptions.BaseException;
import es.vir2al.fwk.fwk.services.ExcelService;
import es.vir2al.fwk.fwk.utils.constants.ResponseConstants;

@Service("excelService")
public class ExcelServiceImpl implements ExcelService<ContactoVO> {

    @Autowired
    private ContactosService contactosService;

    @Override
    public void save(MultipartFile file) throws BaseException {

        List<ContactoVO> contactos = null;
        ContactosExcelHelper excelHelper = new ContactosExcelHelper();

        try {

            contactos = excelHelper.excelToContactos(file.getInputStream());

            for (ContactoVO contactoVO : contactos) {
                this.contactosService.explode(contactoVO);
                this.contactosService.createContacto(contactoVO);
            }
        
        } catch (IOException e) {

            e.printStackTrace();
            throw new BaseException(ResponseConstants.IO_ERROR, "No se puede guardar la información del fichero excel.");

        }

    }
    
}
