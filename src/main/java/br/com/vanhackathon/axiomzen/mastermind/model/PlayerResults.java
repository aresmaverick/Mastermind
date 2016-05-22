package br.com.vanhackathon.axiomzen.mastermind.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PlayerResults {

	@XmlElement(name="user")
	private String user;
	
	@XmlElement(name="results")
	private List<Result> results;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}
}
