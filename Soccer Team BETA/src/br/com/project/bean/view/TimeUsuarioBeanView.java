package br.com.project.bean.view;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.geral.controller.TimeController;
import br.com.project.geral.controller.UsuarioController;
import br.com.project.model.Time;
import br.com.project.model.Usuario;
import br.com.project.model.enums.Permissao;
import br.com.project.util.all.EmailUtil;

@Controller
@Scope(value = "session")
@ManagedBean(name = "timeUsuarioBeanView")
public class TimeUsuarioBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private Time time = new Time();
	private Usuario usuario = new Usuario();
	private Date data;
	private UploadedFile file;
	private String passwordConf;

	// private Usuario userLogado = this.getUserLogadoSession();

	@Autowired
	private UsuarioController usuarioController;
	@Autowired
	private TimeController timeController;
	@Autowired
	private ContextoBean contextoBean;

	public void cadastrarTime() {

		try {

			if (!EmailUtil.validar(usuario.getEmail())) {
				FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Endereço de email invalido", "Por favor, confirme a senha correta!"));
			} else {

				if (usuario.getPassword().equals(passwordConf)) {

					boolean exception = false;

					if (usuarioController.valid(usuario.getUsername())
							&& this.usuario.getId_Usuario() <= 0) {

						FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(
								FacesMessage.SEVERITY_ERROR, "Esse nome de usuario já está em uso", ""));
						exception = true;
					}

					if (usuarioController.validEmail(usuario.getEmail())
							&& this.usuario.getId_Usuario() <= 0) {

						FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(
								FacesMessage.SEVERITY_ERROR, "Esse endereço do email já está em uso", ""));

						exception = true;

					}
					
					if (timeController.validarNome(time.getNome())
							&& this.usuario.getId_Usuario() <= 0) {

						FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(
								FacesMessage.SEVERITY_ERROR, "Esse Nome de time já está em uso", ""));

						exception = true;

					}
					
					if(!exception)
					{
						usuario.setPermissao(Permissao.ADMIN);
						time.setDtFundacao(data);
						time = (Time) timeController.merge(time);
						usuario.setTime(time);
						usuarioController.save(usuario);
						usuarioController.boasVindas(usuario);
						FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Cadastrado com Sucesso", "Time: " + time.getNome() + " cadastrado com sucesso!"));
					}

				} else {
					FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Senhas Diferentes", "Por favor, confirme a senha correta!"));
				}
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("msg",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Não foi possivel cadastrar", "erro"));
		}
	}

	public Usuario getUserLogadoSession() {
		Usuario user = new Usuario();
		try {
			user = contextoBean.getEntidadeLogada();
		} catch (Exception e) {
			user = null;
		}
		return user;
	}

	public String save() throws Exception {

		if (!EmailUtil.validar(getUserLogadoSession().getEmail())) {
			FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Endereço de email invalido", "Por favor, confirme a senha correta!"));
		} else {
			if (getUserLogadoSession().getPassword().equals(passwordConf)) {
				
				boolean exception = false;

				if (usuarioController.valid(usuario.getUsername())
						&& this.usuario.getId_Usuario() <= 0) {

					FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Esse nome de usuario já está em uso", ""));
					exception = true;
				}

				if (usuarioController.validEmail(usuario.getEmail())
						&& this.usuario.getId_Usuario() <= 0) {

					FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Esse endereço do email já está em uso", ""));

					exception = true;

				}
				
			
				
				if(!exception)
				{
				
					usuarioController.merge(getUserLogadoSession());
					usuarioController.dadosAtualizados(getUserLogadoSession());
					FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Salvo com Sucesso", "Time: " + time.getNome() + " cadastrado com sucesso!"));
				}

			} else {
				FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Senhas Diferentes", "Por favor, confirme a senha correta!"));
			}
		}

		return "";
	}

	public String getPasswordConf() {
		return passwordConf;
	}

	public void setPasswordConf(String passwordConf) {
		this.passwordConf = passwordConf;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UploadedFile getFile() {
		return file;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	protected Class<?> getClassImplement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected InterfaceCrud<?> getController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}