package br.com.project.util.all;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public static String getDateAtualReportName(){
		DateFormat df = new SimpleDateFormat("ddMMyyyy");
		return df.format(Calendar.getInstance().getTime());
	}
	
	public static String fortmatDateSql(Date data){
		StringBuffer sb = new StringBuffer();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		sb.append("'").append(df.format(data)).append("'");
		return sb.toString();
	}
	
	public static String fortmatDateSqlSimple(Date data){
		StringBuffer sb = new StringBuffer();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		sb.append(df.format(data));
		return sb.toString();
	}
	
	public static String formatDateString(Date data){
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return formato.format(data);
	}
}