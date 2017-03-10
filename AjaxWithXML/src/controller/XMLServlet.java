package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import domain.Customer;
import util.Wrapper;

@WebServlet("/getCustomer.do")
public class XMLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//작성할 내용의 타입이 xml이다
		response.setContentType("text/xml;charset=utf-8");
		
		//마샬링한 데이터를 받을 스트림버퍼
		OutputStream out = response.getOutputStream();
		
		//원래는 쿼리 날려서 customer가져 와야 한다
		Customer cus = new Customer();
		cus.setId("1234");
		cus.setPassword("0000");
		cus.setName("진강사");
		cus.setAddress("서울시 금천구");
		
		Customer cus2 = new Customer();
		cus2.setId("1234");
		cus2.setPassword("0000");
		cus2.setName("진강사");
		cus2.setAddress("서울시 금천구");
		
		//Wrapper클래스 필요
		List<Customer> list = new ArrayList<>();
		list.add(cus);
		list.add(cus2);
		
		try {
			JAXBContext context = JAXBContext.newInstance(Wrapper.class, Customer.class);
			
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			//Customer 도메인의 <customer>위에 <customers>
			QName qname = new QName("customers"); //이름지정
			
			Wrapper<Customer> wrapper = new Wrapper<>(list);
			
			JAXBElement<Wrapper> element =
					new JAXBElement<Wrapper>(qname, Wrapper.class, wrapper);
			
			m.marshal(element, out);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		//xml로 만들어서(마샬링) 보내준다(to 브라우저, 클라)
		
		//cus하나만 넣기
//		try {
//			//이것(Customer.class)으로 xml을 만들겠다는 의미
//			JAXBContext context = JAXBContext.newInstance(Customer.class);
//			
//			//interface(javax.xml.bind)
//			Marshaller m = context.createMarshaller();
//			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//			m.marshal(cus, out); //cus를 out(stream)으로 보내라
//			
//		} catch (JAXBException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

}
