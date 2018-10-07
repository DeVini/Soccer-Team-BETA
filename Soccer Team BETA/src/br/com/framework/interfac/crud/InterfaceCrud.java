package br.com.framework.interfac.crud;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public interface InterfaceCrud<T> extends Serializable {

	// salvar dados
	void save(T obj) throws Exception;

	void persist(T obj) throws Exception;

	// salva ou atualiza
	void saveOrUpdate(T obj) throws Exception;

	// realiza o update/atualização de dados
	void update(T obj) throws Exception;

	// realiza o delete de dados
	void delete(T obj) throws Exception;

	// salvar e retornar objeto
	T merge(T obj) throws Exception;

	List<T> findList(Class<T> entidade) throws Exception;

	Object findByID(Class<T> entidade, Long id) throws Exception;

	T findByPorID(Class<T> entidade, Long id) throws Exception;

	List<T> findListByQueryDinamica(String s) throws Exception;

	// executar update com HQL
	void executeUpdateQueryDinamica(String s) throws Exception;

	// executar update com SQL
	void executeUpdateSQLDinamica(String s) throws Exception;

	// limpa a sessão do Hibernate
	void clearSession() throws Exception;

	// Retira um objeto da sessão do hibernate
	void evict(Object obj) throws Exception;

	Session getSession() throws Exception;

	List<?> getListSQLDinamica(String s) throws Exception;
	

	// JDBC do Spring
	JdbcTemplate getJdbcTemplate();

	SimpleJdbcTemplate getSimpleJdbcTemplate();

	SimpleJdbcInsert getSimpleJdbcInsert();

	Long totalRegistro(String table) throws Exception;

	Query obterQuery(String query) throws Exception;

	// Carregamento dinamico com jsf e primefaces
	List<T> findListByQueryDinamica(String query, int iniciaNoRegistro, int maximoResultado) throws Exception;

}