package fr.dauphine.miageif.OperationBancaire.OperationBancaire.Controller;

import fr.dauphine.miageif.OperationBancaire.OperationBancaire.Model.OperationBancaire;
import fr.dauphine.miageif.OperationBancaire.OperationBancaire.Repository.OperationBancaireRepository;
//import org.json.JSONException;
//import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class OperationController {

   
    @Autowired
    private OperationBancaireRepository repository;
    
    
    // POST
    @PostMapping(value = "/operation-bancaire")
    public ResponseEntity<OperationBancaire> createOperationBancaire(@RequestBody OperationBancaire OperationBancaire){
        return ResponseEntity.ok().body(this.repository.save(OperationBancaire));
    }

    //  "http://localhost:8088/operation-bancaire"
    @GetMapping("/operation-bancaire")
    public ResponseEntity<List<OperationBancaire>> getAllOperationBancaire(){
        try {
            List<OperationBancaire> OperationBancaires = new ArrayList<OperationBancaire>();
            repository.findAll().forEach(OperationBancaires::add);

            if (OperationBancaires.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(OperationBancaires, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  "http://localhost:8088/operation-bancaire/id/1234"
    @GetMapping("/operation-bancaire/id/{id_transaction}")
    public ResponseEntity<OperationBancaire> getOperationBancaireById(@PathVariable Long id_transaction) throws RestClientException, IOException {
        Optional<OperationBancaire> OperationBancaire = repository.findById(id_transaction);

        if (OperationBancaire.isPresent()) {
            return new ResponseEntity<>(OperationBancaire.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //  "http://localhost:8088/operation-bancaire/montant/1000"
    @GetMapping("/operation-bancaire/montant/{montant}")
    public ResponseEntity<List<OperationBancaire>> getOperationBancaireByMontant(@PathVariable double montant) throws RestClientException, IOException {
        try{
            List<OperationBancaire> OperationBancaires = repository.findByMontant(montant);

            if (OperationBancaires.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(OperationBancaires, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  "http://localhost:8088/operation-bancaire/type/VIREMENT"
    @GetMapping("/operation-bancaire/type/{type}")
    public ResponseEntity<List<OperationBancaire>> getOperationBancaireBytype(@PathVariable String type) throws RestClientException, IOException {
        try{
            List<OperationBancaire> OperationBancaires = repository.findByType(type);

            if (OperationBancaires.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(OperationBancaires, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // "http://localhost:8088/operation-bancaire/date/2022-06-21"
    @GetMapping("/operation-bancaire/date/{date}")
    public ResponseEntity<List<OperationBancaire>> getOperationBancaireByDate(@PathVariable String date) throws RestClientException, IOException {
        try{
            List<OperationBancaire> OperationBancaires = repository.findByDate(date);

            if (OperationBancaires.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(OperationBancaires, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // "http://localhost:8088/operation-bancaire/source/EUR/dest/USD"
    @GetMapping("/operation-bancaire/source/{source}/dest/{dest}")
    public ResponseEntity<List<OperationBancaire>> getOperationBancaireBySourceAndDest(@PathVariable String source, @PathVariable String dest) throws RestClientException, IOException {
        try{
            List<OperationBancaire> OperationBancaires = repository.findBySourceAndDest(source, dest);

            if (OperationBancaires.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(OperationBancaires, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





    //  "http://localhost:8088/operation-bancaire/source/FR7630004000031234567890143/dest/FR7610011000201234567890188/date/2022-06-23"
    @GetMapping("/operation-bancaire/source/{source}/dest/{dest}/date/{date}")
    public ResponseEntity<List<OperationBancaire>> getOperationBancaireBySourceAndDestAndDate(@PathVariable String source, @PathVariable String dest, @PathVariable String date) throws RestClientException, IOException {
        try{
            List<OperationBancaire> OperationBancaires = repository.findBySourceAndDestAndDate(source, dest, date);

            if (OperationBancaires.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(OperationBancaires, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static HttpEntity<?> getHeaders() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }

    // DELETE
    //  "http://localhost:8088/operation-bancaire/id/1243"
    @DeleteMapping("/operation-bancaire/id/{id}")
    public ResponseEntity<HttpStatus> deleteOperationBancaire(@PathVariable long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT
    @PutMapping("/operation-bancaire/id/{id}")
    public ResponseEntity<OperationBancaire> updateOperationBancaire(@PathVariable long id, @RequestBody OperationBancaire OperationBancaire) {

        Optional<OperationBancaire> OperationBancaireData = repository.findById(id);

        if (OperationBancaireData.isPresent()) {
            OperationBancaire _OperationBancaire = OperationBancaireData.get();
            _OperationBancaire.setDestination(OperationBancaire.getDest());
            _OperationBancaire.setSource(OperationBancaire.getSource());
            _OperationBancaire.setDate(OperationBancaire.getDate());
            _OperationBancaire.setMontant(OperationBancaire.getMontant());
            _OperationBancaire.setType(OperationBancaire.getType());
            return new ResponseEntity<>(repository.save(_OperationBancaire), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    // "http://localhost:8088/operation-bancaire/id/1/montant/4000"
    @PutMapping("/operation-bancaire/id/{id}/montant/{montant}")
    public ResponseEntity<OperationBancaire> updateMontantForOperationBancaire(@PathVariable long id, @PathVariable double montant) {
        Optional<OperationBancaire> OperationBancaireData = repository.findById(id);

        if (OperationBancaireData.isPresent()) {
            OperationBancaire _OperationBancaire = OperationBancaireData.get();
            _OperationBancaire.setMontant(montant);
            return new ResponseEntity<>(repository.save(_OperationBancaire), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
