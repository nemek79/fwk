package es.vir2al.fwk.app.repositories.hibernate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.vir2al.fwk.app.entities.Pais;

@Repository
public interface PaisesRepository extends JpaRepository<Pais,Integer> {
    
}
