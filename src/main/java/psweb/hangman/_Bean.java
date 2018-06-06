package psweb.hangman;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
@Configuration

public abstract class _Bean 
{
	private String dicFolder;
	
	private List<Word> listEasyWords = new ArrayList<>();
	private List<Word> listMediumWords = new ArrayList<>();
	private List<Word> listHardWords = new ArrayList<>();
	
	public _Bean() {
		String line = "";
		Locale.setDefault(new Locale("pt","BR"));
		try {
			
			InputStream input= new FileInputStream(getClass().getClassLoader().getResource("dicionario.dic").getPath());
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			while((line = reader.readLine()) != null) {
				String[] splitWord = line.split("/");
				//TODO change if to Stream API 
				if(splitWord[0].length() < 5) {
					listEasyWords.add(new Word(splitWord[0]));
				}else if(splitWord[0].length() > 5 && splitWord[0].length() < 7) {
					listMediumWords.add(new Word(splitWord[0]));
				}else if(splitWord[0].length() > 7) {
					listHardWords.add(new Word(splitWord[0]));
				}
			}
			
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Object getJsfParam(String param){
		return getHttpServletRequest().getAttribute(param);
	}
	
	public HttpServletRequest getHttpServletRequest(){
		return (HttpServletRequest)getExternalContext().getRequest();
	}
	
	public HttpServletResponse getHttpServletResponse() {
		return (HttpServletResponse)getExternalContext().getResponse();
	}
	
	public HttpSession getHttpSession(){
		return getHttpServletRequest().getSession();
	}	
	
	public ExternalContext getExternalContext(){
		return getFacesContext().getExternalContext();
	}
	
	public ServletContext getServletContext(){
		return (ServletContext)getExternalContext().getContext();
	}
	
	public String getFileSeparator(){
		return System.getProperty("file.separator");
	}
	
	public FacesContext getFacesContext(){
		return FacesContext.getCurrentInstance();
	}
	
	public List<Word> getEasyListWord() {
		return this.listEasyWords;
	}
	
	public List<Word> getMediumListWord() {
		return this.listMediumWords;
	}
	
	public List<Word> getHardListWord() {
		return this.listHardWords;
	}
}