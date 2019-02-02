package br.com.amigopet.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.amigopet.dao.PessoaDAO;
import br.com.amigopet.dao.PetshopDAO;
import br.com.amigopet.domain.Cidade;
import br.com.amigopet.domain.Pessoa;
import br.com.amigopet.domain.Petshop;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PetshopBean implements Serializable {
	private Petshop petshop;
	private List<Petshop> petshops;
	private List<Pessoa> pessoas;
	private List<Cidade> cidades;

	public List<Petshop> getPetshops() {
		return petshops;
	}

	public void setPetshops(List<Petshop> petshops) {
		this.petshops = petshops;
	}

	public Petshop getPetshop() {
		return petshop;
	}

	public void setPetshop(Petshop petshop) {
		this.petshop = petshop;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	@PostConstruct
	public void listar() {
		try {
			PetshopDAO petshopDAO = new PetshopDAO();
			petshops = petshopDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar os petshops");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			petshop = new Petshop();

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar um novo Petshop");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			PetshopDAO petshopDAO = new PetshopDAO();
			petshopDAO.merge(petshop);

			petshop = new Petshop();

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();

			petshops = petshopDAO.listar();

			Messages.addGlobalInfo("Petshop Salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar um novo Petshop");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			petshop = (Petshop) evento.getComponent().getAttributes()
					.get("petshopSelecionado");

			PetshopDAO petshopDAO = new PetshopDAO();
			petshopDAO.excluir(petshop);

			petshops = petshopDAO.listar();

			Messages.addGlobalInfo("Petshop removido com sucesso");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar remover o petshop");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {

			petshop = (Petshop) evento.getComponent().getAttributes()
					.get("petshopSelecionado");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um Petshop");
			erro.printStackTrace();
		}

	}

}
