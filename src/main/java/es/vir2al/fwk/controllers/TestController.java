package es.vir2al.fwk.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.vir2al.fwk.models.Demo;
import es.vir2al.fwk.services.demo.DemoService;

@RestController
@RequestMapping("/api/test")
public class TestController {

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
			
	@Autowired
	private DemoService demoSRV;
	
	@GetMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> test() {
		
		logger.info("TEST!");
		
		this.createDemo();
		
		return new ResponseEntity<>("OK",HttpStatus.OK);
		
	}
	
	private void createDemo() {
		
		Demo demo = new Demo();
		
		demo.setId(1L);
		demo.setDescripcion("Prueba");
		
		try {
			this.demoSRV.create(demo);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	
}
