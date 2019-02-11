package br.com.project.bean.view;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.hibernate.HibernateException;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.UsuarioController;
import br.com.project.model.Usuario;
import br.com.project.model.enums.Permissao;
import br.com.project.util.all.DateUtil;
import br.com.project.util.all.EmailUtil;

@Controller
@ViewScoped
@ManagedBean(name = "usuarioBeanView")
public class UsuarioBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private final String URL = "/cadastro/cad_users.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_users.jsf?faces-redirect=true";

	@Autowired
	private ContextoBean contextoBean;
	@Autowired
	private UsuarioController usuarioController;
	
	private String passwordConf;

	private CarregamentoLazyListForObject<Usuario> lazy = new CarregamentoLazyListForObject<Usuario>();

	private Usuario usuarioSelecionado = new Usuario();

	private List<Usuario> list = new ArrayList<Usuario>();
	
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("users_report");
		super.setNomeRelatorioSaida("users_report");
		super.setListDataBeanCollectionReport(usuarioController.getUsers(contextoBean.getEntidadeLogada().getTime()));
		return super.getArquivoReport();
	}

	public String save() throws Exception  {
	
		System.out.println(usuarioSelecionado.getEmail());
			
			if (!EmailUtil.validar(usuarioSelecionado.getEmail())) {
				addMsg("Endereço de email invalido");
			} else {
				
				if (usuarioSelecionado.getPassword().equals(passwordConf) || this.usuarioSelecionado.getId_Usuario() > 0 ) {
				
					boolean exception = false;
					
					if(usuarioController.valid(usuarioSelecionado.getUsername()) && this.usuarioSelecionado.getId_Usuario() <= 0){
						
						FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Esse nome de usuario já está em uso", ""));
						exception = true;
					}
					
					if(usuarioController.validEmail(usuarioSelecionado.getEmail()) && this.usuarioSelecionado.getId_Usuario() <= 0){
						
						FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Esse endereço do email já está em uso", ""));
						
						exception = true;
					
					}
						
					if(!exception){
							//FacesContext.getCurrentInstance().getExternalContext().redirect("cad_users.jsf");
							System.out.println("vai salvar");
							usuarioSelecionado.setTime(contextoBean.getEntidadeLogada().getTime());
							usuarioController.merge(usuarioSelecionado);
							
							if(usuarioSelecionado.getId_Usuario() > 0){
								usuarioController.dadosAtualizados(usuarioSelecionado);
							}else{
								usuarioController.boasVindas(usuarioSelecionado);
							}
							novo();
							sucesso();
							FacesContext.getCurrentInstance().getExternalContext().redirect("cad_users.jsf");
					}
						
					
				
										
					
				} else {
				
					addMsg("As Senhas não conferem!");
				}
			}
		 usuarioSelecionado = new Usuario();
		
		return 	"";
	}
	
	public List<Usuario> getList() throws HibernateException, Exception {
		list =  usuarioController.getUsers(contextoBean.getEntidadeLogada().getTime());
		//list.remove(contextoBean.getEntidadeLogada());
		return list;
	}
	
	public String getUltimoAcesso(Usuario user)throws Exception{
		return (user.getUltimoacesso() != null)?DateUtil.formatDateString(user.getUltimoacesso()):"Ainda não logou!" ;
	}
	
	public CarregamentoLazyListForObject<Usuario> getLazy() throws Exception {
		//list =  usuarioController.getUsers(contextoBean.getEntidadeLogada().getTime());
		//list.remove(contextoBean.getEntidadeLogada());
		//list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
		lazy.remove(contextoBean.getEntidadeLogada());
		return lazy;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	@PostConstruct
	public List<SelectItem> getPermissoesList() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<Permissao> permissoes = Permissao.getPermissoes();
		for (Permissao permissao : permissoes) {
			list.add(new SelectItem(permissao, permissao.toString()));
		}
		return list;
	}

	public String nomeTime() throws Exception {
		return contextoBean.getEntidadeLogada().getTime().getNome();
	}
	
	public String getPasswordConf() {
		return passwordConf;
	}
	
	public void setPasswordConf(String passwordConf) {
		this.passwordConf = passwordConf;
	}

	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		return URL;
	}
	
	@Override
	public void setarVariaveisNulas() throws Exception {
		lazy.clean();
		this.passwordConf = "";
		this.usuarioSelecionado = new Usuario();
	}
	@Override
	public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();	
		return urlFind;
	}
	@Override
	public void excluir() throws Exception {
		this.usuarioSelecionado = (Usuario) usuarioController.getSession().get(Usuario.class, usuarioSelecionado.getId_Usuario());
		usuarioController.delete(usuarioSelecionado);
		lazy.remove(usuarioSelecionado);
		sucesso();
	}
	
	@Override
	public String editar() throws Exception {
		lazy.clean();
		
		return URL;
	}
	
	@Override
	protected Class<Usuario> getClassImplement() {
		
		return Usuario.class;
	}
	
	@Override
	protected InterfaceCrud<Usuario> getController() {
	
		return usuarioController;
	}
	
	
	@Override
	public void consultarEntidade() throws Exception {
		 usuarioSelecionado = new Usuario();
		
		 lazy.clean();
		 //"select entity from Usuario entity where entity.id  like '%1%' order by entity.id"
		 lazy.setTotalRegistroConsulta(super.totalRegistroConsulta(),super.getSqlLazyQuery());
		
		 
	}
	
	public boolean hibilitarBotoes(Usuario user) throws Exception{
		
		return contextoBean.getEntidadeLogada().equals(user);
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" and time = ");
		sql.append(contextoBean.getEntidadeLogada().getTime().getId());
		//sql.append(" and id_Usuario != ");
		//sql.append(contextoBean.getEntidadeLogada().getId_Usuario());
		return sql.toString();
	}
}