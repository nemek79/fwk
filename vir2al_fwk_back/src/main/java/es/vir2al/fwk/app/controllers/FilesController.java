package es.vir2al.fwk.app.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import es.vir2al.fwk.app.domain.ContactoVO;
import es.vir2al.fwk.fwk.domain.FileVO;
import es.vir2al.fwk.fwk.domain.responses.BaseResponse;
import es.vir2al.fwk.fwk.domain.responses.DataResponse;
import es.vir2al.fwk.fwk.exceptions.BaseException;
import es.vir2al.fwk.fwk.services.ExcelService;
import es.vir2al.fwk.fwk.services.FilesService;
import es.vir2al.fwk.fwk.utils.constants.ResponseConstants;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api/files")
public class FilesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaisesController.class);

    @Autowired
    FilesService filesService;

    @Autowired
    ExcelService<ContactoVO> excelService;


    @GetMapping(value="/{filename:.+}",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename) {
      
        Resource file;

        try {
            
            file = filesService.load(filename);


            return ResponseEntity.ok()
             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);

        } catch (BaseException e) {

            e.printStackTrace();
            BaseResponse response = new BaseResponse();
            response.setCode(ResponseConstants.UNEXPECTED_ERROR);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }

    }
    
    @PostMapping("/init")
    public ResponseEntity<?> init() {

        LOGGER.debug("INICIO init()");

        BaseResponse response = new BaseResponse();

        try {

            this.filesService.init();
        
        } catch (BaseException e) {
            
            e.printStackTrace();
            response.setCode(ResponseConstants.UNEXPECTED_ERROR);

        }

        LOGGER.debug("FINAL init()");

        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<?> deleteAll() {

        LOGGER.debug("INICIO deleteAll()");

        BaseResponse response = new BaseResponse();

        try {

            this.filesService.deleteAll();
        
        } catch (BaseException e) {
            
            e.printStackTrace();
            response.setCode(ResponseConstants.UNEXPECTED_ERROR);

        }

        LOGGER.debug("FINAL deleteAll()");

        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
      
        BaseResponse response = new BaseResponse();

        try {
            
            this.filesService.save(file);
  
        } catch (Exception e) {

            LOGGER.error(e.getMessage());
			response.setCode(ResponseConstants.UNEXPECTED_ERROR);

        }

        LOGGER.debug("FINAL uploadFile()");

        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @PostMapping("/import/excel")
    public ResponseEntity<?> importExcel(@RequestParam("file") MultipartFile file) {
      
        BaseResponse response = new BaseResponse();

        try {
            
           this.excelService.save(file);
  
        } catch (Exception e) {

            LOGGER.error(e.getMessage());
			response.setCode(ResponseConstants.UNEXPECTED_ERROR);

        }

        LOGGER.debug("FINAL importExcel()");

        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @GetMapping("")
    public ResponseEntity<?> getListFiles() {

        List<FileVO> fileInfos;
        DataResponse<List<FileVO>> response = new DataResponse<List<FileVO>>();

        try {

            fileInfos = filesService.loadAll().map(path -> {
                
                String filename = path.getFileName().toString();
                String url = MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();
    
                return new FileVO(filename, url);
            }).collect(Collectors.toList());

            response.setData(fileInfos);

        } catch (BaseException e) {

            e.printStackTrace();
            response.setCode(e.getCode());

        }
  
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


}
