package fr.dauphine.miageif.OperationBancaire.OperationBancaire.Model;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class OperationChangeTest {
    OperationBancaire op;

    @Before
    public void setUp() {
        op = new OperationBancaire();
    }

    @Test
    public void testGetSetID(){
        op.setId(1234L);
        assertEquals(Long.valueOf(1234L), op.getId());
    }

    @Test
    public void testGetSetSource(){
        op.setSource("FR7630004000031234567890143");
        assertEquals("FR7630004000031234567890143", op.getSource());
    }



    @Test
    public void testGetSetDate(){
        op.setDate("2022-06-21");
        assertEquals("2022-06-21", op.getDate());
    }

    @Test
    public void testEquals(){
        op.setId(1243L);
        op.setSource("FR7630004000031234567890143");
        op.setDestination("FR7610011000201234567890188");
        op.setMontant(500.0);
        op.setDate("2021-06-21");
        op.setType("VIREMENT");
        OperationBancaire op2 = new OperationBancaire(1243L,"VIREMENT","FR7610011000201234567890188", "FR7610011000201234567890188",  500.0, "2022-06-23");
        assertTrue(op.equals(op2));
    }
}
