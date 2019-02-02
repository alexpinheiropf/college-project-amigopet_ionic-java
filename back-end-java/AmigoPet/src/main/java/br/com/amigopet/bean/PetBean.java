package br.com.amigopet.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.amigopet.dao.PetDAO;
import br.com.amigopet.domain.Pet;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PetBean implements Serializable {
	private Pet pet;
	private List<Pet> pets;

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

	@PostConstruct
	public void listar() {
		try {
			PetDAO petDAO = new PetDAO();
			pets = petDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os Pets");
			erro.printStackTrace();
		}
	}

	public void novo() {
		pet = new Pet();
	}

	public void salvar() {
		try {
			PetDAO petDAO = new PetDAO();
			petDAO.merge(pet);

			pet = new Pet();
			pets = petDAO.listar();

			Messages.addGlobalInfo("Pet salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Pet");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			pet = (Pet) evento.getComponent().getAttributes()
					.get("petSelecionado");
			PetDAO petDAO = new PetDAO();
			petDAO.excluir(pet);

			pets = petDAO.listar();

			Messages.addGlobalInfo("Pet removido com sucesso");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar remover o Pet");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		pet = (Pet) evento.getComponent().getAttributes().get("petSelecionado");
	}

}
