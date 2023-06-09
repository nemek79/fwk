package es.vir2al.fwk.fwk.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import es.vir2al.fwk.fwk.exceptions.BaseException;
import es.vir2al.fwk.fwk.utils.constants.ResponseConstants;

@Service("filesService")
public class FilesServiceImpl implements FilesService {

    private final Path root = Paths.get("uploads");

    @Override
    public void init() throws BaseException {

        try {

            Files.createDirectories(root);
        
        } catch (IOException e) {
            
            throw new BaseException(ResponseConstants.IO_ERROR, e.getMessage());
        
        }

    }

    @Override
    public void save(MultipartFile file)  throws BaseException {

        try {

            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));

        } catch (Exception e) {

            if (e instanceof FileAlreadyExistsException) {

              throw new BaseException(ResponseConstants.OBJECT_ALREADY_EXISTS, "El fichero ya existe.");
            
            }
      
            throw new BaseException(ResponseConstants.UNEXPECTED_ERROR, e.getMessage());
        
        }

    }

    @Override
    public Resource load(String filename)  throws BaseException {

        try {
            
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
      
            if (resource.exists() || resource.isReadable()) {

              return resource;
            
            } else {
              
                throw new BaseException(ResponseConstants.IO_ERROR, "No se puede leer el fichero.");
            
            }

        } catch (MalformedURLException e) {
            
            throw new BaseException(ResponseConstants.IO_ERROR, e.getMessage());
          
        }

    }

    @Override
    public void deleteAll()  throws BaseException {
        
        FileSystemUtils.deleteRecursively(root.toFile());

    }

    @Override
    public Stream<Path> loadAll()  throws BaseException {

        try {

            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
          
        } catch (IOException e) {
        
            throw new BaseException(ResponseConstants.IO_ERROR, "No se pueden leer los ficheros.");
        
        }

    }
    
}
