package br.com.vanhackathon.axiomzen.mastermind.model.colors;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum(char.class)
public enum Colors {
	@XmlEnumValue("R") R('R'), 
	@XmlEnumValue("G") G('G'), 
	@XmlEnumValue("B") B('B'), 
	@XmlEnumValue("Y") Y('Y'), 
	@XmlEnumValue("O") O('O'), 
	@XmlEnumValue("P") P('P'), 
	@XmlEnumValue("C") C('C'), 
	@XmlEnumValue("M") M('M');
	
	private char colorLetter;
	
	Colors(char colorLetter) {
		this.colorLetter = colorLetter;
	} 
	
	public char getColor() {
		return this.colorLetter;
	}
}
