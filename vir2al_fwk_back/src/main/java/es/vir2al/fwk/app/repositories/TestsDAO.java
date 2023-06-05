package es.vir2al.fwk.app.repositories;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import es.vir2al.fwk.app.domain.TestVO;

@Repository("testsDAO")
public interface TestsDAO {
    
    public TestVO getTestById(
        @Param("id") Integer id
    );

    public List<TestVO> getTests(
    );

    public Integer createTest(
            @Param("criteria") TestVO data
        );

    public Integer updateTest(
            @Param("id") Integer id,
            @Param("criteria") TestVO data
        );


    public Integer deleteTest(
            @Param("id") Integer id
        );
}
