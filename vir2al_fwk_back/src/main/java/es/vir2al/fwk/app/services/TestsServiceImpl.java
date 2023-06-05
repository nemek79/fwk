package es.vir2al.fwk.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.vir2al.fwk.app.domain.TestVO;
import es.vir2al.fwk.app.repositories.TestsDAO;
import es.vir2al.fwk.fwk.exceptions.BaseException;
import es.vir2al.fwk.fwk.utils.constants.ResponseConstants;

@Service("testsService")
public class TestsServiceImpl implements TestsService {

    @Autowired
    private TestsDAO testsDAO;

    @Override
    public TestVO getTestById(Integer id) throws BaseException {
        
        return this.testsDAO.getTestById(id);

    }

    @Override
    public List<TestVO> getTests() throws BaseException {
        
        return this.testsDAO.getTests();

    }

    @Override
    public TestVO createTest(TestVO data) throws BaseException {
        
        if (data.getId() != null)
            throw new BaseException(ResponseConstants.INPUT_DATA_ERROR, "El objeto a crear no puede tener un id");

        data.setId(this.testsDAO.createTest(data));

        return data;
    }

    @Override
    public void updateTest(Integer id, TestVO data) throws BaseException {
        
        TestVO testBD = null;

        if (id == null || id <= 0) 
            throw new BaseException(ResponseConstants.INPUT_DATA_ERROR, "El identificador no es correcto");

        testBD = this.testsDAO.getTestById(id);

        if (testBD == null)
            throw new BaseException(ResponseConstants.NODATA_FOUND_ERROR, "El identificador no corresponde a ningún objeto en el sistema");

        testBD.setStrKey(data.getStrKey());
        testBD.setIntKey(data.getIntKey());

        this.testsDAO.updateTest(id, testBD);
    }

    @Override
    public void deleteTestById(Integer id) throws BaseException {
        
        if (id == null || id <= 0) 
            throw new BaseException(ResponseConstants.INPUT_DATA_ERROR, "El identificador no es correcto");

        this.testsDAO.deleteTest(id);

    }

    @Override
    public void createTestsMasivo() throws BaseException {
        
        final Integer numCreated = 1000;
        final Integer firstNumber = 101;
        TestVO test = null;

        for (int i = firstNumber; i < firstNumber + numCreated; i++) {
            
            test = new TestVO(null, "KEY_"+i, i);

            this.testsDAO.createTest(test);

        }

    }
    
}
