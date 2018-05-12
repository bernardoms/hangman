package psweb.hangman;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class Timer implements Serializable{
    
   /**
	 * 
	 */
	private static final long serialVersionUID = 5269583620825173954L;
private int number;
   public Timer() {
	   this.number = 0;
   }
   public int getNumber() {
       return number;
   }
   
   public void reset() {
       this.number = 0;
   }

   public void increment() {
       number++;
   }
}
