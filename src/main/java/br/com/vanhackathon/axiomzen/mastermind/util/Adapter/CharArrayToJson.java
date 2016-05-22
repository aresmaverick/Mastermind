package br.com.vanhackathon.axiomzen.mastermind.util.Adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class CharArrayToJson extends XmlAdapter<String, char[]>{

	@Override
	public char[] unmarshal(String v) throws Exception {
		return v.toCharArray();
	}

	@Override
	public String marshal(char[] v) throws Exception {
		return new String(v);
	}

}
