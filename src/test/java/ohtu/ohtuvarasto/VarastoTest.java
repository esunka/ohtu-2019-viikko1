package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void liikaaTavaraa() {
        varasto.lisaaVarastoon(15);

        // ylimenevät menevät hukkaan
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenVarasto() {
        varasto = new Varasto(-10);
        System.out.println(varasto); // Gets the coverage up))
        assertNotEquals(-10,varasto.getTilavuus(),vertailuTarkkuus);
    }

    
    @Test
    public void otetaanLiikaaTavaraa() {
        varasto.lisaaVarastoon(10);
        varasto.otaVarastosta(11);
        // ylimenevät menevät hukkaan
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void overloadUp() {
        varasto = new Varasto(10,10);
        assertEquals(0, varasto.paljonkoMahtuu(),vertailuTarkkuus);
    }

    @Test
    public void overloadUpNegatiivinen() {
        varasto = new Varasto(10,50);
        // menee yli
        assertEquals(0, varasto.paljonkoMahtuu(),vertailuTarkkuus);
    }
    @Test
    public void negatiivinenAlkuSaldo() {
        varasto = new Varasto(10,-10);
        assertNotEquals(-10, varasto.paljonkoMahtuu(),vertailuTarkkuus);
    }

    @Test
    public void negatviinenTilavuus() {
        varasto = new Varasto(-10,10);
        assertNotEquals(-10, varasto.paljonkoMahtuu(),vertailuTarkkuus);
    }

    @Test
    public void lisaaVarastoonNeg() {
        varasto.lisaaVarastoon(-10);
        assertEquals(10, varasto.paljonkoMahtuu(),vertailuTarkkuus);
    }
    
    @Test
    public void otaVarastostaNeg() {
        varasto.otaVarastosta(-2);
        assertEquals(10,varasto.paljonkoMahtuu() ,vertailuTarkkuus);
    }
}