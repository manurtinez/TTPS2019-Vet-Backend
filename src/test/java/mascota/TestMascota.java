package mascota;

import static org.junit.Assert.*;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import clasesDAO.ConfigFichaDAO;
import clasesDAO.DuenoDAO;
import clasesDAO.MascotaDAO;
import clasesDAO.VeterinarioDAO;
import factory.FactoryDAO;
import model.ConfigFicha;
import model.Desparasitacion;
import model.Dueno;
import model.Evento;
import model.Mascota;
import model.Veterinario;

public class TestMascota {
	
	ConfigFicha config = new ConfigFicha(false, false, false, false, false, false, false, false, false, false);
	
	Dueno du = new Dueno("seba", "pose", "hola@gmail.com", "1234", 1234);
	
	Veterinario vet = new Veterinario("manu", "martinez", "manu@gmail.com", "1234", 4456677,
			"mascotas Manu", "calle 1 678", 442312345);
	
	Mascota m = new Mascota("fufi", "perro", "caniche", "masculino", "blanco", "ninguna"
			, Calendar.getInstance().getTime() , new BufferedImage[2], du, config);

	
	Mascota m2 = new Mascota("fufi2", "perro", "caniche", "masculino", "negro", "ninguna",
		Calendar.getInstance().getTime(), new BufferedImage[2],du, config);
	
	Desparasitacion d = new Desparasitacion(LocalDate.now(), m, "cualquiera", "todo bien");
	
	MascotaDAO mascotajpa = FactoryDAO.getMascotaDAO();
	DuenoDAO duenojpa = FactoryDAO.getDuenoDAO();
	VeterinarioDAO veterinariojpa = FactoryDAO.getVeterinarioDAO();
	ConfigFichaDAO fichajpa = FactoryDAO.getConfigFichaDAO();
	
	@Before
	public void setUpBeforeClass() throws Exception {
		m.setVeterinario(vet);
		duenojpa.save(du);
	}

	@Test
	public void test() {
		List<Mascota> lista = mascotajpa.getAll();
		assertEquals(2, lista.size());
		Dueno due = duenojpa.getById(1);
		List<Mascota> listaDue = mascotajpa.getByDueno_id(due.getId());
		Assert.assertEquals(2, listaDue.size());
		List<Mascota> listaVet = mascotajpa.getByVet(2);
		assertEquals(1, listaVet.size());
		mascotajpa.delete(listaVet.get(0));
		lista = mascotajpa.getAll();
		//listaDue = mascotajpa.getByDueno_id(due.getId());
		//Assert.assertEquals(1, listaDue.size());
		assertEquals(1, lista.size());
	}

}
