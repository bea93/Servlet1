package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import datamodel.dao.DepartamentoDAO;
import datamodel.dao.EmpleadoDAO;
import datamodel.entities.annotations.Departamento;
import datamodel.entities.annotations.Empleado;
import datamodel.utils.HibernateUtil;

/**
 * Servlet implementation class MostrarEmpleadosServ
 */
@WebServlet("/MostrarEmpleados")
public class MostrarEmpleadosServ extends HttpServlet {
	static SessionFactory sessionFactory;
	private static Logger logger = LogManager.getLogger(MostrarEmpleadosServ.class);
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MostrarEmpleadosServ() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			PrintWriter out;
			out = response.getWriter();
			response.setContentType("text/html");
			
			tx = session.beginTransaction();
			
			EmpleadoDAO empleado = new EmpleadoDAO();     	
            List<Empleado> resultadoempleado = empleado.getEmpleados(session);
            
			out.println("<html>");
			out.println("<head><title>Empleados </title></head>");
			out.println("<body>");
			
	        //Desplegar los datos del registro actual en el navegador
	        out.println(" </FONT> </b> <br> </center> ");
	        out.println("<br> ");
	        out.println("<table align='center' width='90%' border='1' cellpadding='0' cellspacing='0'>  ");
	        out.println("<tr> ");
		        out.println("<td> ");
		        out.println("<table width='100%' align='center' border='0' cellpadding='0' cellspacing='0'> ");
		        out.println(" <tr bgcolor='#CCFF66' >");
			        //Columnas de la tabla de la BD
			        out.println("<td align='center'> <b> Código </b> </td>  ");
			        out.println("<td align='center'> <b> Nombre </b> </td> ");
			        out.println("<td align='center'> <b> Primer Apellido </b> </td>  ");
			        out.println("<td align='center'> <b> Segundo Apellido </b> </td>  ");
			        out.println("<td align='center'> <b> Código Departamento </b> </td>  ");
			        out.println("<td align='center'> <b> Dirección </b> </td>  ");
			        out.println("<td align='center'> <b> Fecha de nacimiento </b> </td>  ");
			        out.println("<td align='center'> <b> Lugar de nacimiento </b> </td>  ");
			        out.println("<td align='center'> <b> Télefono </b> </td>  ");
			        out.println("<td align='center'> <b> Puesto </b> </td>  ");
		        out.println(" </tr>");
	
    		for(Empleado e : resultadoempleado) {		
    			out.println("<tr>");
    			out.println("<td align='center'>"+ e.getCodigo() +"</td>");	
    			out.println("<td align='center'>"+ e.getNombre() +"</td>");
    			out.println("<td align='center'>"+ e.getApellido1() +"</td>");
    			out.println("<td align='center'>"+ e.getApellido2() +"</td>");
    			out.println("<td align='center'>"+ e.getCodDepartamento() +"</td>");
    			out.println("<td align='center'>"+ e.getDireccion() +"</td>");
    			out.println("<td align='center'>"+ e.getFechaNacimiento() +"</td>");
    			out.println("<td align='center'>"+ e.getLugarNacimiento() +"</td>");
    			out.println("<td align='center'>"+ e.getPuesto() +"</td>");
    			out.println("<td align='center'>"+ e.getTelefono() +"</td>");
    			out.println("</tr>");
    		}
    		
    		out.println("</body></html>");
    		
    		logger.info("Se muestra la tabla rellenada con los datos de la BD");
			
		}catch (Exception e) {
			  if (tx != null) {
			    tx.rollback();
			  }
			  //logger.error(String.format("%1$s: Error al insertar los registros.", methodName), e);
			  
		}finally {
			if (session != null) {
					session.close();
					logger.info("Cerramos la sesion del servlet mostrardepartamento");
			}
		} 
		
		//Cerramos la sesion
		session.close();
		
		//logger.info(String.format("%1$s: >>>>>> Se acaba la ejecución del servlet departamento.", methodName));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
