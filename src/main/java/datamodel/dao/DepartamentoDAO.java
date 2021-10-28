package datamodel.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.query.Query;

import datamodel.entities.annotations.Departamento;

public class DepartamentoDAO {
	public static void insertarDepartamento(Session s, Departamento departamento) {
		s.save(departamento);
	}

	public static Departamento getDepartamento(Session s, int id) {
		return s.get(Departamento.class, id);
	}

	public static void updateDepartamento(Session s, Departamento departamento) {
		s.update(departamento);
	}

	public static void deleteDepartamento(Session s, Departamento departamento) {
		s.delete(departamento);
	}

	public List<Departamento> getDepartamentos(Session s) {

		Query<Departamento> miConsulta = s.createQuery("from Departamento", Departamento.class);
		List<Departamento> departamentos = miConsulta.getResultList();

		return departamentos;
	}
}
