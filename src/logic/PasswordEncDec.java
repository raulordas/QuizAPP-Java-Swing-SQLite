package logic;

public class PasswordEncDec {
	private static final char[] alphabetA = {'A', 'a', 'B', 'b', 'C', 
			'c', 'D', 'd', 'E', 'e', 'F', 'f', 'G', 'g', 'H', 'h', 'I',
			'i', 'J', 'j', 'K', 'k', 'L', 'l', 'M', 'm', 'N', 'n', 'O',
			'o', 'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's', 'T', 't',  'U', 'u',
			'V', 'v', 'W', 'w', 'X', 'x', 'Y', 'y', 'Z', 'z'};
	
	public PasswordEncDec() {}
	
	public String EncryptPassword(String password) {
		String passwordEncrypted = password;
		
		for (int i = 0; i < alphabetA.length; i++) {
			
			for (int j = 0; j < password.length(); j++) {	
				
				if(alphabetA[i] == password.charAt(j)) {
					passwordEncrypted = passwordEncrypted.replace(passwordEncrypted.charAt(j), alphabetA[(alphabetA.length - 1) - i]);
				}
			}
		}
		return passwordEncrypted;		
	}
	
	public String DecryptPassword(String password) {
		String passwordDecrypted = password;
		
		for (int i = 0; i < alphabetA.length; i++) {
			
			for (int j = 0; j < password.length(); j++) {	
				
				if(alphabetA[i] == password.charAt(j)) {
					passwordDecrypted = passwordDecrypted.replace(passwordDecrypted.charAt(j), alphabetA[0 + ((alphabetA.length - 1) - i)]);
				}
			}
		}
		return passwordDecrypted;		
	}
}
