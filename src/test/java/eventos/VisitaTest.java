package eventos;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import clasesDAO.ConfigFichaDAO;
import clasesDAO.DuenoDAO;
import clasesDAO.EventoDAO;
import clasesDAO.MascotaDAO;
import factory.FactoryDAO;
import model.ConfigFicha;
import model.Dueno;
import model.Mascota;
import model.Visita;

public class VisitaTest {
	private static Mascota mascota;
	private static ConfigFicha config;
	private static Dueno duenoMascota;
	private static Visita eventoV1;
	private Visita eventoV2;
	private static MascotaDAO mascotaJPA = FactoryDAO.getMascotaDAO();
	private static DuenoDAO duenoJPA = FactoryDAO.getDuenoDAO();
	private static ConfigFichaDAO configFichaJPA = FactoryDAO.getConfigFichaDAO();
	private static EventoDAO eventoJPA = FactoryDAO.getEventoDAO();
	
	@BeforeClass
	public static void beforeClass() {
		config = new ConfigFicha(false, false, false, false, false, false, false, false, false, false); 
		duenoMascota = new Dueno("pepe", "mujica", "elPepe@gmail.com", "1234", 22155620);	
		mascota = new Mascota("taton", "perro", "pitbull", "macho", "blanco", "ninguna", null , null, duenoMascota, config);
		eventoV1 = new Visita(LocalDate.now(), mascota, 22.2f , "pelea en la calle", "perdida de oreja derecha", "alejar del duenio");
		mascotaJPA.save(mascota);
	}
	@Test
	public void test() {
		ArrayList<Mascota> mascotas;
		
		mascotas = (ArrayList<Mascota>) mascotaJPA.getAll();
		assertEquals(1,mascotas.size());
		Mascota m1 = mascotas.get(0);
		assertEquals(1, m1.getHistorial().size());
		Visita e1 = (Visita) m1.getHistorial().get(0);
		assertEquals("pelea en la calle", e1.getMotivo());
		
		eventoV2 = new Visita(LocalDate.now(), mascota, 22.2f , "pelea en la calle", "perdida de nariz", "ninguna");
		mascotaJPA.save(mascota);
		assertEquals(2, m1.getHistorial().size());
		m1.borrarEvento(eventoV2);		;
		eventoJPA.delete(eventoV2);
		assertEquals(1, m1.getHistorial().size());
		
		e1.setMotivo("otro motivo");
		eventoJPA.update(e1);
		mascotas = (ArrayList<Mascota>) mascotaJPA.getAll();
		Visita e3 = (Visita) mascotas.get(0).getHistorial().get(0);
		assertTrue(e3.getMotivo().equals("otro motivo"));
		
		Visita e4 = (Visita) eventoJPA.getById(1);
		assertEquals("otro motivo", e1.getMotivo());
		
	}
	@AfterClass
	public static void AfterClass() {
		/*mascota.borrarEvento(eventoV1);
		eventoJPA.delete(eventoV1);	
		mascotaJPA.delete(mascota);	
		configFichaJPA.delete(config);
		duenoJPA.delete(duenoMascota);*/    
	}
}