package es.vir2al.fwk.respositories.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.vir2al.fwk.models.Demo;

@Repository
public interface DemoDAO extends JpaRepository<Demo,Long> {

}
