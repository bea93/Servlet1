package datamodel.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import datamodel.entities.annotations.Empleado;


public class EmpleadoDAO {
	public static void insertarEmpleado(Session s, Empleado empleado) {
		s.save(empleado);
	}

	public static Empleado getEmpleado(Session s, int id) {
		return s.get(Empleado.class, id);
	}

	public static void updateEmpleado(Session s, Empleado empleado) {
		s.update(empleado);
	}

	public static void deleteEmpleado(Session s, Empleado empleado) {
		s.delete(empleado);
	}

	public List<Empleado> getEmpleados(Session s) {

		Query<Empleado> miConsulta = s.createQuery("from Empleado", Empleado.class);
		List<Empleado> empleados = miConsulta.getResultList();

		return empleados;
	}
	
}
