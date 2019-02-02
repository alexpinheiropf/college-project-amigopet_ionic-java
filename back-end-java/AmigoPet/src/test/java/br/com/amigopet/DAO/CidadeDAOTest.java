package br.com.amigopet.DAO;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.amigopet.dao.CidadeDAO;
import br.com.amigopet.dao.EstadoDAO;
import br.com.amigopet.domain.Cidade;
import br.com.amigopet.domain.Estado;

public class CidadeDAOTest {
	@Test
	public void salvar(){
		Long codigoEstado = 1L;
		
		EstadoDAO estadoDAO = new EstadoDAO();
		
		Estado estado = estadoDAO.buscar(codigoEstado);
		
		Cidade cidade = new Cidade();
		cidade.setNome("Carazinho");
		cidade.setEstado(estado);
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.salvar(cidade);
		
	}
	
	@Test
	@Ignore
	public void listar(){
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> resultado = cidadeDAO.listar();
		
		for(Cidade cidade : resultado){
			System.out.println("Código da Cidade"  + cidade.getCodigo());
			System.out.println("Nome da cidade " + cidade.getNome());
			System.out.println("Codigo do Estado " + cidade.getEstado().getCodigo());
			System.out.println("Sigla do Estado " + cidade.getEstado().getSigla());
			System.out.println("Nome do Estado " + cidade.getEstado().getNome());
			System.out.println();
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
	Long codigo = 1L;
		
	CidadeDAO cidadeDAO = new CidadeDAO();
	Cidade cidade = cidadeDAO.buscar(codigo);
	
	System.out.println("Código da Cidade"  + cidade.getCodigo());
	System.out.println("Nome da cidade " + cidade.getNome());
	System.out.println("Codigo do Estado " + cidade.getEstado().getCodigo());
	System.out.println("Sigla do Estado " + cidade.getEstado().getSigla());
	System.out.println("Nome do Estado " + cidade.getEstado().getNome());
	System.out.println();
	}
	
	@Test
	@Ignore
	public void excluir(){
		Long codigo = 4L;
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);
		
		cidadeDAO.excluir(cidade);
		
		System.out.println("Cidade Excluida");
		System.out.println("Código da Cidade"  + cidade.getCodigo());
		System.out.println("Nome da cidade " + cidade.getNome());
		System.out.println("Codigo do Estado " + cidade.getEstado().getCodigo());
		System.out.println("Sigla do Estado " + cidade.getEstado().getSigla());
		System.out.println("Nome do Estado " + cidade.getEstado().getNome());
		System.out.println();
	}
	
	@Test
	@Ignore
	public void editar(){
		Long codigoCidade = 3L;
		Long codigoEstado = 7L;
		
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigoEstado);
		
		System.out.println("Código do Estado: " + estado.getCodigo());
		System.out.println("Sigla do Estado: " + estado.getSigla());
		System.out.println("Nome do Estado: " + estado.getNome());
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigoCidade);
		
		System.out.println("CIdade A ser editada");
		System.out.println("Código da Cidade"  + cidade.getCodigo());
		System.out.println("Nome da cidade " + cidade.getNome());
		System.out.println("Codigo do Estado " + cidade.getEstado().getCodigo());
		System.out.println("Sigla do Estado " + cidade.getEstado().getSigla());
		System.out.println("Nome do Estado " + cidade.getEstado().getNome());
		System.out.println();
		
		cidade.setNome("Guarapuava");
		cidade.setEstado(estado);
		
		cidadeDAO.editar(cidade);
		
		System.out.println("CIdade A ser editada");
		System.out.println("Código da Cidade"  + cidade.getCodigo());
		System.out.println("Nome da cidade " + cidade.getNome());
		System.out.println("Codigo do Estado " + cidade.getEstado().getCodigo());
		System.out.println("Sigla do Estado " + cidade.getEstado().getSigla());
		System.out.println("Nome do Estado " + cidade.getEstado().getNome());
		System.out.println();
		
	}
}
