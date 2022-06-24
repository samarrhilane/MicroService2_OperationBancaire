package fr.dauphine.miageif.OperationBancaire.OperationBancaire.Repository;

import fr.dauphine.miageif.OperationBancaire.OperationBancaire.Model.OperationBancaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface OperationBancaireRepository extends JpaRepository<OperationBancaire, Long> {
    List<OperationBancaire> findByMontant(double montant);

    List<OperationBancaire> findByDate(String date);


    List<OperationBancaire> findBySourceAndDest(String source, String dest);

    List<OperationBancaire> findBySourceAndDestAndDate(String source, String dest, String date);

    List<OperationBancaire> findByType(String type);

    List<OperationBancaire> findByTypeAndDate(String type, String date);

    List<OperationBancaire> findByTypeAndSourceAndDest(String type, String source, String dest);

    List<OperationBancaire> findByTypeAndSourceAndDestAndDate(String type, String source, String dest, String date);
}
