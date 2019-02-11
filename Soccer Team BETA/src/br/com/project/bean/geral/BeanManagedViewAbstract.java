package br.com.project.bean.geral;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.persistence.Table;

import org.hibernate.Query;
import org.hibernate.cfg.Configuration;

import org.springframework.stereotype.Component;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.annotation.IdentificaCampoPesquisa;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.model.Usuario;
import br.com.project.model.enums.CondicaoPesquisa;
import br.com.project.report.util.BeanReportView;
import br.com.project.util.all.BeanViewAbstract;
import br.com.project.util.all.StringUtil;

@Component
public abstract class BeanManagedViewAbstract extends BeanReportView{


	private static final long serialVersionUID = 1L;
	
	
	protected abstract Class<?> getClassImplement();
	
	protected abstract InterfaceCrud<?> getController();
	
	public ObjetoCampoConsulta objetoCampoConsultaSelecionado;
	
	public List<SelectItem> listaCampoPesquisa;
	
	public List<SelectItem> listaCondicaoPesquisa;
	
	
	
	public CondicaoPesquisa condicao;
	
	public String valorPesquisa;
	
	public String getValorPesquisa() {
		return valorPesquisa;
	}
	
	public void setValorPesquisa(String valorPesquisa) {
		this.valorPesquisa = valorPesquisa;
	}
	
	public abstract String condicaoAndParaPesquisa() throws Exception;
	
	public ObjetoCampoConsulta getObjetoCampoConsultaSelecionado() {
		return objetoCampoConsultaSelecionado;
	}

	public void setObjetoCampoConsultaSelecionado(ObjetoCampoConsulta objetoCampoConsultaSelecionado) {
		
		if(objetoCampoConsultaSelecionado != null){
			for(Field field : getClassImplement().getDeclaredFields()){
				if(field.isAnnotationPresent(IdentificaCampoPesquisa.class)){
					if(objetoCampoConsultaSelecionado.getCampoBanco().equalsIgnoreCase(field.getName())){
						String descricaoCampo = field.getAnnotation(IdentificaCampoPesquisa.class).descricaoCampo();
						objetoCampoConsultaSelecionado.setDescricao(descricaoCampo);
						objetoCampoConsultaSelecionado.setTipoClass(field.getType().getCanonicalName());
						objetoCampoConsultaSelecionado.setPrincipal(field.getAnnotation(IdentificaCampoPesquisa.class).principal());
						
						break;
					}
				}
			}
		}
		
		this.objetoCampoConsultaSelecionado = objetoCampoConsultaSelecionado;
		
	}
	

	
	public List<SelectItem> getListaCampoPesquisa() {
	
		listaCampoPesquisa = new ArrayList<SelectItem>();
		List<ObjetoCampoConsulta> listTemp = new ArrayList<ObjetoCampoConsulta>();
		
		for(Field field : getClassImplement().getDeclaredFields()){
			if(field.isAnnotationPresent(IdentificaCampoPesquisa.class)){
				String descricaoCampo = field.getAnnotation(IdentificaCampoPesquisa.class).descricaoCampo();
				String descricaoCampoPesquisa = field.getAnnotation(IdentificaCampoPesquisa.class).campoConsulta();
				int isPrincipal = field.getAnnotation(IdentificaCampoPesquisa.class).principal();
				
				ObjetoCampoConsulta objetoConsulta = new ObjetoCampoConsulta();
				objetoConsulta.setDescricao(descricaoCampo);
				objetoConsulta.setCampoBanco(descricaoCampoPesquisa);
				objetoConsulta.setTipoClass(field.getType().getCanonicalName());
				objetoConsulta.setPrincipal(isPrincipal );
				listTemp.add(objetoConsulta);
			}
			
		}
		
		orderReverse(listTemp);
		
		for(ObjetoCampoConsulta objeto : listTemp){
			listaCampoPesquisa.add(new SelectItem(objeto));
		}
		return listaCampoPesquisa;
	}
	
	public List<SelectItem> getListaCondicaoPesquisa() {
		
		listaCondicaoPesquisa = new ArrayList<SelectItem>();
		for(CondicaoPesquisa condicao : CondicaoPesquisa.values()){
			listaCondicaoPesquisa.add(new SelectItem(condicao, condicao.toString()));
		}
		return listaCondicaoPesquisa;
	}

	public CondicaoPesquisa getCondicao() {
		return condicao;
	}
	
	public void setCondicao(CondicaoPesquisa condicao) {
		this.condicao = condicao;
	}
	
	private void orderReverse(List<ObjetoCampoConsulta> listTemp) {
		
		Collections.sort(listTemp, new Comparator<ObjetoCampoConsulta>(){

			@Override
			public int compare(ObjetoCampoConsulta obj1, ObjetoCampoConsulta obj2) {
				
				return obj1.getPrincipal().compareTo(obj2.getPrincipal());
			}
		});
	}

	public String getSqlLazyQuery() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select entity from ");
		sql.append(getQueryConsulta(getClassImplement().getSimpleName()));
		sql.append("order by entity.");
		sql.append(objetoCampoConsultaSelecionado.getCampoBanco());
		return sql.toString();
	}


	
	private String getQueryConsulta(String tableName) throws Exception {
		
		valorPesquisa = StringUtil.removerAcentos(valorPesquisa);

		StringBuilder sql = new StringBuilder();
		
		sql.append(tableName);

		sql.append(" entity where ");

		//sql.append(" retira_acentos(upper(cast(entity.");
		sql.append(" entity.");

		sql.append(objetoCampoConsultaSelecionado.getCampoBanco());

		//sql.append(" as character(100) ))) ");

		if (condicao.name().equals(

		CondicaoPesquisa.IGUAL_A.name())) {

		//sql.append(" = retira_acentos(upper('");
		sql.append(" = '");
		sql.append(valorPesquisa);
		sql.append("'");
		//sql.append("'))");

		} else if (condicao.name().equals(

		CondicaoPesquisa.CONTEM.name())) {

		//sql.append(" like retira_acentos(upper('%");
		sql.append(" like '%");

		sql.append(valorPesquisa);
		sql.append("%'");
		//sql.append("%'))");

		} else if (condicao.name().equals(

		CondicaoPesquisa.INICIA.name())) {

		//sql.append(" like retira_acentos(upper('");
		sql.append(" like '");
		sql.append(valorPesquisa);
		sql.append("%'");
		//sql.append("%'))");

		} else if (condicao.name().equals(

		CondicaoPesquisa.TERMINA_COM.name())) {

		//sql.append(" like retira_acentos(upper('%");
		sql.append(" like '%");

		sql.append(valorPesquisa);
		sql.append("'");
		//sql.append("'))");

		}

		sql.append(" ");
		sql.append(condicaoAndParaPesquisa());
		sql.append(" ");
		
		return sql.toString();

	}

	protected int totalRegistroConsulta() throws Exception {

		String sql = (" select count(*) from " + getQueryConsulta(getClassImplement().getAnnotation(Table.class).name())).toLowerCase();
		return getController().getJdbcTemplate().queryForInt(sql);

	}

}