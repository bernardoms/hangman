package psweb.hangman;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@ManagedBean
@SessionScoped
@Configuration
@PropertySource("classpath:resource/hangman.properties")
public class HangmanBean extends _Bean
{    
	//
	// Atributos
	//
	private Hangman hangman;
	//private List<Word> listEasyWords = new ArrayList<>();
	//private List<Word> listMediumWords = new ArrayList<>();
	//private List<Word> listHardWords = new ArrayList<>();
	private List<String> hangmanImageList = new ArrayList<>();
	
	//Audio player
	AudioPlayer player = new AudioPlayer();
	
	//
	// Campos do Formulário
	//
	private String letter = "";
	private String difficult = "";
	private String hangmanImage = "";
	//
	// Construtor
	//
	public HangmanBean()
	{
		this.hangman = new Hangman();
		this.difficult = "Medium";
		this.loadImages();
		//The first image is int the last index
		this.hangmanImage = this.hangmanImageList.get(hangmanImageList.size() - 1);
		//The default difficult is medium, so we reset with the list of medium words
		this.hangman.reset(this.getMediumListWord());
		
	}
	
	//
	// Operações
	//
	public void guess() throws IOException
	{
		char chr = letter.toCharArray()[0];
		hangman.input(chr);
		this.hangmanImage = this.getHangmanImageList().get(getChances());
		if(this.isGameLose()){
			//get audio from resource path
			player.playSound(getClass().getClassLoader().getResource("choque.wav").getPath());
		}
		letter = "";
	}
	
	public void reset()
	{
		letter = "";
		if(this.difficult.equals("Easy")) {
			this.hangman.reset(this.getEasyListWord());
		}else if(this.difficult.equals("Medium")) {
			this.hangman.reset(this.getMediumListWord());
		}else {
			this.hangman.reset(this.getHardListWord());
		}
		this.hangmanImage = this.hangmanImageList.get(hangmanImageList.size() - 1);
		
	}
	
	//
	// Métodos de Acesso
	//
	public String getWord()
	{
		return hangman.getWordAsString();
	}
	
	public String getAnswerAsString() {
		return hangman.getAnswerAsString();
	}
	
	public Integer getChances()
	{
		return hangman.getChances();
	}
	
	public String getAttempts()
	{
		return hangman.getInputHistory().toString();
	}
	
	public boolean isGameOver()
	{
		return hangman.isGameOver();
	}
	
	public boolean isGameWin()
	{
		return hangman.isComplete();
	}
	
	public boolean isGameLose()
	{
		return hangman.getChances()==0;
	}
	
	public String getLetter() {
		return letter;
	}
	
	public void setLetter(String letter) {
		this.letter = letter;
	}
	
	public String getDifficult() {
		return difficult;
	}

	public void setDifficult(String difficult) {
		this.difficult = difficult;
	}

	public List<String> getHangmanImageList() {
		return hangmanImageList;
	}

	public void setHangmanImageList(List<String> hangmanImageList) {
		this.hangmanImageList = hangmanImageList;
	}
	
	public void loadImages() {
		for(int i = 0 ; i < 7 ; i++) {
			this.getHangmanImageList().add("images\\hangman" + i + ".png");
		}
	}

	public String getHangmanImage() {
		return hangmanImage;
	}

	public void setHangmanImage(String hangmanImage) {
		this.hangmanImage = hangmanImage;
	}
}  



