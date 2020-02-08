package es.vir2al.fwk.services.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.vir2al.fwk.models.Demo;
import es.vir2al.fwk.respositories.demo.DemoDAO;

@Repository
public class DemoServiceImpl implements DemoService {

	@Autowired
	private DemoDAO demoDAO;
	
	@Override
	public void create(Demo demo) throws Exception {
		
		this.demoDAO.save(demo);
			
	}

}
