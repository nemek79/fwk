package es.vir2al.fwk.fwk.services;

import org.springframework.web.multipart.MultipartFile;

import es.vir2al.fwk.fwk.exceptions.BaseException;

import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import java.nio.file.Path;

// https://www.bezkoder.com/spring-boot-file-upload/

public interface FilesService {
    
    public void init() throws BaseException;

    public void save(MultipartFile file) throws BaseException;
  
    public Resource load(String filename) throws BaseException;
  
    public void deleteAll() throws BaseException;
  
    public Stream<Path> loadAll() throws BaseException;

}
