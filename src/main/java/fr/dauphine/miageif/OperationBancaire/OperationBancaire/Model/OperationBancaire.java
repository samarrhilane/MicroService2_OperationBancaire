package fr.dauphine.miageif.OperationBancaire.OperationBancaire.Model;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

// Classe persistente representant  un "Operation Change"
@Entity
public class OperationBancaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_operation;

    @Column(name="type_operation")
    private String type;

    @Column(name="iban_source")
    private String source;

    @Column(name="iban_dest")
    private String dest;

    @Column(name="montant")
    private double montant;
    

    @Column(name="date")
    private String date;



    public OperationBancaire(){
    }

    public OperationBancaire(Long id_operation, String type, String source, String dest, double montant, String date){
        super();
        this.id_operation = id_operation;
        this.montant = montant;
        this.source = source;
        this.dest = dest;
        this.date = date;
        this.type = type;
    }

    public OperationBancaire( String type, String source, String dest, double montant, String date){
        super();
        this.montant = montant;
        this.source = source;
        this.dest = dest;
        this.date = date;
        this.type = type;
    }



    // GETTER
    public Long getId() {
        return id_operation;
    }

    public double getMontant() {
        return montant;
    }

    public String getSource() {
        return source;
    }

    public String getDest() {
        return dest;
    }


    public String getDate() { return date; }

    public String getType() { return type; }

    // SETTER


    public void setId(Long id_operation) { this.id_operation = id_operation; }

    public void setMontant(double montant) { this.montant = montant; }

    public void setSource(String source) { this.source = source; }

    public void setDestination(String dest) { this.dest = dest; }


    public void setDate(String date) { this.date = date; }

    public void setType(String type) { this.type = type; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationBancaire that = (OperationBancaire) o;
        return Objects.equals(id_operation, that.id_operation) && Objects.equals(source, that.source) && Objects.equals(dest, that.dest) && Objects.equals(montant, that.montant)  && Objects.equals(date, that.date) && Objects.equals(type, that.type);
    }

}
