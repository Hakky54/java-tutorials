package nl.altindag.server.repository;

import nl.altindag.server.model.SSLMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SSLMaterialRepository extends JpaRepository<SSLMaterial, Long> {

}
