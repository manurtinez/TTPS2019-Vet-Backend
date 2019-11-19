package ttps.spring.DAOjpa;

import ttps.spring.DAO.ConfigFichaDAO;
import ttps.spring.model.ConfigFicha;


public class ConfigFichaDAOjpa extends GenericDAOjpa<ConfigFicha>
implements ConfigFichaDAO {

	public ConfigFichaDAOjpa() {
		super(ConfigFicha.class);
	}

}
