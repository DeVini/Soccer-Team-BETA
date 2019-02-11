package br.com.project.util.all;

import java.text.Normalizer;
import java.util.List;

public class StringUtil {

	
	public static String separarPorVirgulas(List<String> string)
	{
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < string.size(); i++){
			
			sb.append(string.get(i));
			if(i != ( string.size() - 1) ){
				sb.append(",");
			}
		}		
		return sb.toString();
	}
	
	public static String removerAcentos(String str) {
	    return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
	
}
