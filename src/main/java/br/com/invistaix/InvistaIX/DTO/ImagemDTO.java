package br.com.invistaix.InvistaIX.DTO;

public class ImagemDTO {
	private String base64;
	
	public ImagemDTO() {
		
	}
	
	public ImagemDTO(String base64) {
		this.base64 = base64;
	}
	
	public String getBase64() {
		return base64;
	}
	
	public void setBase64(String base64) {
		this.base64 = base64;
	}

}
