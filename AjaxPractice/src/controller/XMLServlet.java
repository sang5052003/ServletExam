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

/**
 * Servlet implementation class XMLServlet
 */
@WebServlet("/customer.do")
public class XMLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/xml;charset=utf-8");
		
		Customer cus = new Customer();
		cus.setId("123");
		cus.setName("kim");
		cus.setPassword("1111");
		
		Customer cus2 = new Customer();
		cus2.setId("123");
		cus2.setName("kim");
		cus2.setPassword("1111");
		
		OutputStream out = response.getOutputStream();
		
		List<Customer> list = new ArrayList<>();
		list.add(cus);
		list.add(cus2);
		
		try {
			JAXBContext context = JAXBContext.newInstance(Wrapper.class, Customer.class);
			
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			QName qname = new QName("customers");
			
			Wrapper<Customer> wrapper = new Wrapper<>(list);
			
			JAXBElement<Wrapper> element = new JAXBElement<Wrapper>(qname, Wrapper.class, wrapper);
			
			m.marshal(element, out);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
//		try {
//			JAXBContext context = JAXBContext.newInstance(Customer.class);
//			
//			Marshaller m = context.createMarshaller();
//			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//			m.marshal(cus, out);
//			
//		} catch (JAXBException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
