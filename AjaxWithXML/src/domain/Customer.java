package domain;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

//
//<customer>
//	<id>... </id>
//	<name>... </name>
//	<address>... </address>
//</customer>
//password는 보이면 안되므로 안보냄
@XmlRootElement(name="customer")
@XmlType(propOrder = {"id", "name", "address"}) //get을 통해서 위처럼 만들어줌
public class Customer {
	
	private String id;
	private String password;
	private String name;
	private String address;
	
	public Customer() {}
	
	//@XmlAttribute //@XmlType(propOrder = {"id", "name", "address"})로 하거나 이걸로 하거나..
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	//패스워드는 xml로 만들어서 가지 마라
	@XmlTransient //객체직렬화에서 나온 개념(Transient키워드를 붙이면 객체직렬화에서도 빼준다)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
