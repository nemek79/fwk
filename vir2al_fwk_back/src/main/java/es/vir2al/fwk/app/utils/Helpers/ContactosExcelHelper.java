package es.vir2al.fwk.app.utils.Helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es.vir2al.fwk.app.domain.ContactoVO;
import es.vir2al.fwk.app.domain.PaisVO;
import es.vir2al.fwk.fwk.exceptions.BaseException;
import es.vir2al.fwk.fwk.utils.BaseExcelHelper;
import es.vir2al.fwk.fwk.utils.TimeUtils;
import es.vir2al.fwk.fwk.utils.constants.GeneralConstants;
import es.vir2al.fwk.fwk.utils.constants.ResponseConstants;

public class ContactosExcelHelper extends BaseExcelHelper {

    private final static String[] HEADERs = { "Id", "Nombre", "Apellidos", "Pais Nacimiento", "Pais Residencia", "Fecha Nacimiento" };
    private final static String SHEET = "Contactos";
    
    public ContactosExcelHelper() {

        super(HEADERs, SHEET);

    }

    @Override
    public List<ContactoVO> excelToContactos(InputStream is) throws BaseException {

        try {

            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(ContactosExcelHelper.SHEET);

            Iterator<Row> rows = sheet.iterator();

            Integer id = null;

            PaisVO pais = null;

            List<ContactoVO> contactos = new ArrayList<ContactoVO>();

            int rowNumber = 0;

            while (rows.hasNext()) {

                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                ContactoVO contacto = new ContactoVO();

                int cellIdx = 0;
            
                while (cellsInRow.hasNext()) {
                    
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
            
                        // Id
                        case 0:
                            id = (int)currentCell.getNumericCellValue(); 
                            contacto.setId(id);
                            break;

                        // Nombre
                        case 1:
                            contacto.setNombre(currentCell.getStringCellValue());
                            break;

                        // Apellidos
                        case 2:
                            contacto.setApellidos(currentCell.getStringCellValue());
                            break;

                        // Pais de nacimiento
                        case 3:
                            pais = new PaisVO();
                            pais.setNombre(currentCell.getStringCellValue().trim());
                            contacto.setPaisNacimiento(pais);
                            break;

                        // Pais de residencia
                        case 4:
                            pais = new PaisVO();
                            pais.setNombre(currentCell.getStringCellValue().trim());
                            contacto.setPaisResidencia(pais);
                            break;

                        // Fecha de nacimiento
                        case 5:
                            Date dateIn = currentCell.getDateCellValue();
                            contacto.setFechaNacimiento(dateIn);
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                contactos.add(contacto);
            }

            workbook.close();

            return contactos;

        } catch (IOException e) {

            throw new BaseException(ResponseConstants.PARSE_DATA_ERROR,"fail to parse Excel file: " + e.getMessage());

        }
    }
    
}
