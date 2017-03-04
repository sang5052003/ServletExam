package yorizori.web.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import yorizori.domain.User;

/**
 * Application Lifecycle Listener implementation class UserAccessLoginListener
 *
 */
//로그 찍는용으로 사용(IO로 로그파일 만들기 가능)
@WebListener
public class UserAccessLoginListener implements HttpSessionAttributeListener {

	private static final String SESSION_ATTRIBUTE_NAME = "loginUser";
	
	//session에 setAttribute하면 호출됨
    public void attributeAdded(HttpSessionBindingEvent se)  {
    	
    	if(SESSION_ATTRIBUTE_NAME.equals(se.getName())){
    		User user = (User)se.getValue();
    		System.out.println(user.getName() + " is login.");
    	}
    	
    }


    //session에 invalidate하면 호출
    public void attributeRemoved(HttpSessionBindingEvent se)  {
    	
    	if(SESSION_ATTRIBUTE_NAME.equals(se.getName())){
    		User user = (User)se.getValue();
    		System.out.println(user.getName() + " is logout.");
    	}
    }


    public void attributeReplaced(HttpSessionBindingEvent se)  { 
         // TODO Auto-generated method stub
    }
	
}
