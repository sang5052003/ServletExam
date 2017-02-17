package com.kosta.example.person.store.factory;

public class JDBCUtils {
	
	public static void clearResource(AutoCloseable...autoCloseables){
		
		for(AutoCloseable auto : autoCloseables){
			if(auto == null) continue;
			
			try {
				auto.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
