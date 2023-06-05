package es.vir2al.fwk.app.services;

import java.util.List;

import es.vir2al.fwk.app.domain.TestVO;
import es.vir2al.fwk.fwk.exceptions.BaseException;

public interface TestsService {
    
    public TestVO getTestById(Integer id) throws BaseException;
    
    public List<TestVO> getTests() throws BaseException;

    public TestVO createTest(TestVO data) throws BaseException; 

    public void updateTest(Integer id,TestVO data) throws BaseException;

    public void deleteTestById(Integer id) throws BaseException;

    public void createTestsMasivo() throws BaseException;
}
