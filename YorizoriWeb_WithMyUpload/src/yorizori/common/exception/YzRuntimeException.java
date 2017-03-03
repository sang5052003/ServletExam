package yorizori.common.exception;

public class YzRuntimeException extends RuntimeException{

	//
	private static final long serialVersionUID = -6259362625181255085L;

	//에러페이지에서 확인버튼을 누르면 여기에 url을 등록
	//error페이지 -> 여러 페이지로 가기 위해서
	private String redirectURL;
	
	public YzRuntimeException(String message) {
		super(message);
	}
	
	public String getRedirectURL(){
		return this.redirectURL;
	}
	
	public void setRedirectURL(String redirectURL){
		this.redirectURL = redirectURL;
	}
	
}
