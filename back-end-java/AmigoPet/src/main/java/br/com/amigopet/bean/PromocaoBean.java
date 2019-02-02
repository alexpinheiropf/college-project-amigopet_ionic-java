package br.com.amigopet.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.amigopet.dao.ServicoDAO;
import br.com.amigopet.dao.promocaoDAO;
import br.com.amigopet.domain.Promocao;
import br.com.amigopet.domain.Servico;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PromocaoBean implements Serializable {
	private Promocao promocao;
	private List<Promocao> promocoes;
	private Servico servico;
	private List<Servico> servicos;

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

	public Promocao getPromocao() {
		return promocao;
	}

	public void setPromocao(Promocao promocao) {
		this.promocao = promocao;
	}

	public List<Promocao> getPromocoes() {
		return promocoes;
	}

	public void setPromocoes(List<Promocao> promocoes) {
		this.promocoes = promocoes;
	}

	@PostConstruct
	public void listar() {
		try {
			promocaoDAO promocaoDAO = new promocaoDAO();
			promocoes = promocaoDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as Promoções");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			promocao = new Promocao();
			
			ServicoDAO servicoDAO = new ServicoDAO();
			servicos = servicoDAO.listar("nome");
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar uma nova Promoção");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			promocaoDAO promocaoDAO = new promocaoDAO();
			promocaoDAO.merge(promocao);
			
			promocao = new Promocao();
			
			ServicoDAO servicoDAO = new ServicoDAO();
			servicos = servicoDAO.listar();
			
			promocoes = promocaoDAO.listar();
			
			Messages.addGlobalInfo("Promoção Salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar uma nova Promoção");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			promocao = (Promocao) evento.getComponent().getAttributes()
					.get("promocaoSelecionado");
			promocaoDAO promocaoDAO = new promocaoDAO();
			promocaoDAO.excluir(promocao);
			
			promocoes = promocaoDAO.listar();

			Messages.addGlobalInfo("Promoção removida com sucesso");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar remover a Promoção");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			promocao = (Promocao) evento.getComponent().getAttributes()
					.get("promocaoSelecionado");
			ServicoDAO servicoDAO = new ServicoDAO();
			servicos = servicoDAO.listar();
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar uma Promoção");
			erro.printStackTrace();
		}

	}

}
