package tn.esprit.tpfoyer.sercice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.sercice.entity.Universite;

@Repository
public interface UniversiteRepository extends JpaRepository<Universite, Long>
{


}
