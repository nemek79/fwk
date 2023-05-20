package es.vir2al.fwk.app.repositories;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import es.vir2al.fwk.app.domain.ContactoVO;
import es.vir2al.fwk.app.domain.requests.ContactoRequest;
import es.vir2al.fwk.fwk.domain.requests.NavigationInfoRequest;

@Repository("contactosDAO")
public interface ContactosDAO {
    
    public ContactoVO getContactoById(
            @Param("id") Integer id
        );

    public List<ContactoVO> getContactos(
            @Param("criteria") ContactoRequest criteria, 
            @Param("navigation") NavigationInfoRequest navigation, 
            @Param("rb") RowBounds rb
        );

    public Integer getContactosCount(
            @Param("criteria") ContactoRequest criteria
        );

    public Integer createContacto(
            @Param("criteria") ContactoVO data
        );

    public Integer updateContacto(
            @Param("id") Integer id,
            @Param("criteria") ContactoVO data
        );

}
