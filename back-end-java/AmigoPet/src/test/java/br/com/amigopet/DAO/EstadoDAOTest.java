package br.com.amigopet.DAO;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.amigopet.dao.EstadoDAO;
import br.com.amigopet.domain.Estado;

public class EstadoDAOTest {
	@Test
	public void salvar() {
		Estado estado = new Estado();
		estado.setNome("Rio de Janeio");
		estado.setSigla("RJ");

		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);
	}

	@Test
	@Ignore
	public void listar() {
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> resultado = estadoDAO.listar();

		System.out.println("Total de Registros Encontrados: "
				+ resultado.size());

		for (Estado estado : resultado) {
			System.out.println(estado.getSigla() + " - " + estado.getNome());
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 1L;

		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);

		if (estado == null) {
			System.out.println("Nenhum registro encontrado");
		} else {

			System.out.println(estado.getCodigo() + " - " + estado.getSigla()
					+ " - " + estado.getNome());

		}
	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 4L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);
		
		if (estado == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			estadoDAO.excluir(estado);
			System.out.println("Registro removido: ");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla()
					+ " - " + estado.getNome());

		}
	}
	
	@Test
	@Ignore
	public void editar(){
		Long codigo = 3L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);
		
		if (estado == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			estado.setSigla("MG");
			estadoDAO.editar(estado);
			System.out.println("Registro Atualizado: ");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla()
					+ " - " + estado.getNome());

		}
	}

}
