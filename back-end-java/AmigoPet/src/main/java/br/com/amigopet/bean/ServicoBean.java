package br.com.amigopet.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.amigopet.dao.PetshopDAO;
import br.com.amigopet.dao.ServicoDAO;
import br.com.amigopet.domain.Petshop;
import br.com.amigopet.domain.Servico;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ServicoBean implements Serializable  {
	private Servico servico;
	private List<Servico> servicos;
	private List<Petshop> petshops;
	
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	public List<Servico> getServicos() {
		return servicos;
	}
	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
	public List<Petshop> getPetshops() {
		return petshops;
	}
	public void setPetshops(List<Petshop> petshops) {
		this.petshops = petshops;
	}
	
	@PostConstruct
	public void listar() {
		try {
			ServicoDAO servicoDAO = new ServicoDAO();
			servicos = servicoDAO.listar();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os serviços");
			erro.printStackTrace();
		}
	}
	
	public void novo() {
		try {
			servico = new Servico();
			
			PetshopDAO petshopDAO = new PetshopDAO();
			petshops = petshopDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar um novo serviço");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		try {
			
			servico = (Servico) evento.getComponent().getAttributes().get("servicoSelecionado");
			
			PetshopDAO petshopDAO = new PetshopDAO();
			petshops = petshopDAO.listar();
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um serviço");
			erro.printStackTrace();
		}	
	}
	
	public void salvar() {
		try {
			ServicoDAO servicoDAO = new ServicoDAO();
			servicoDAO.merge(servico);
			
			servico = new Servico();
			
			PetshopDAO petshopDAO = new PetshopDAO();
			petshops = petshopDAO.listar();
			
			servicos = servicoDAO.listar();

			Messages.addGlobalInfo("Serviço salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o serviço");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			servico = (Servico) evento.getComponent().getAttributes().get("servicoSelecionado");
			
			ServicoDAO servicoDAO = new ServicoDAO();
			servicoDAO.excluir(servico);
			
			servicos = servicoDAO.listar();
			
			Messages.addGlobalInfo("Serviço removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o serviço");
			erro.printStackTrace();
		}
	}
	
}
