package br.com.vanhackathon.axiomzen.mastermind.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.vanhackathon.axiomzen.mastermind.util.Adapter.CharArrayToJson;

@XmlRootElement
public class Result {

	@XmlElement(name="exact")
	private Integer exact;

	@XmlElement(name="near")
	private Integer near;
	
	@XmlElement(name="guess")
	@XmlJavaTypeAdapter(CharArrayToJson.class)
	private char[] guess;
	
	public Integer getExact() {
		return exact;
	}
	public void setExact(Integer exact) {
		this.exact = exact;
	}
	public Integer getNear() {
		return near;
	}
	public void setNear(Integer near) {
		this.near = near;
	}
	public String getGuess() {
		return new String(guess);
	}
	public void setGuess(char[] guess) {
		this.guess = guess;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Result) {
			Result objResult = (Result) obj;
			
			if (objResult.getExact() == this.getExact() &&
					objResult.getGuess() .toString().equals(this.getGuess().toString()) &&
					objResult.getNear() == this.getNear()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	public String partialPrint() {
		return "{\"exact\":" + this.exact + ",\"near\":" + this.near + "}";
//		return "{exact:" + this.exact + ",near:" + this.near + "}";
	}
}
