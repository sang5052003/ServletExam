package util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;

//객체를 여러개 보내기 위한
//리스트때문에 만듬(XMLServlet의)
public class Wrapper<T> {
	private List<T> items;
	
	public Wrapper() {
		this.items = new ArrayList<>();
	}
	
	public Wrapper(List<T> items){
		this.items = items;
	}
	
	@XmlAnyElement(lax = true)
	public List<T> getItems(){
		return this.items;
	}
}
