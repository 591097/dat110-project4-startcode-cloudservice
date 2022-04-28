package no.hvl.dat110.ac.restservice;

public class AccessCode {

	private int[] accesscode = {1,2}; // default access code
	
	public AccessCode() {
		
	}

	public int[] getAccesscode() {
		return accesscode;
	}

	public void setAccesscode(int[] accesscode) {
		this.accesscode = accesscode;
	}
	
	public String toString() {
		return "[ " + accesscode[0] + " " + accesscode[1] + " ]";
	}
	
	
	

}
