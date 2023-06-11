package es.vir2al.fwk.app.repositories;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.vir2al.fwk.app.domain.PaisVO;
import es.vir2al.fwk.app.domain.requests.PaisRequest;
import es.vir2al.fwk.fwk.domain.requests.NavigationInfoRequest;

@Repository("paisesDAO")
public interface PaisesDAO {
 
    public PaisVO getPaisById(
            @Param("id") Integer id
        );

    public List<PaisVO> getPaises(
            @Param("criteria") PaisRequest criteria, 
            @Param("navigation") NavigationInfoRequest navigation, 
            @Param("rb") RowBounds rb
        );

    public Integer getPaisesCount(
            @Param("criteria") PaisRequest criteria 
        );

    public List<PaisVO> getPaisesByNombre(
            @Param("data") String data
        );

}
