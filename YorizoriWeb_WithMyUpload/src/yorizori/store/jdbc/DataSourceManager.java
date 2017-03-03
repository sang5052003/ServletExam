package yorizori.store.jdbc;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class DataSourceManager {
	private static DataSourceManager instance = new DataSourceManager();
	
	private DataSource dataSource;
	
	private DataSourceManager() {
		
		//커넥션 풀 lib
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		ds.setUsername("scott");
		ds.setPassword("tiger");
		
		//커넥션풀 설정
		ds.setMaxTotal(50); 		//최대 커넥션 갯수
		ds.setMaxIdle(5);			//idle상태(db를 사용하지 않는 상태)에 pool이 소유한 최대 커넥션 갯수
		ds.setInitialSize(5); 		//pool의 초기 커넥션 갯수
		
		ds.setMaxWaitMillis(1000);  //커넥션이 존재하지 않을 때 커넥션에 획득에 대기하는 시간
		//1. 새로운 커넥션 확장
		//2. user를 대기시킴(새로운 커넥션이 돌아 올때 까지)
		//현재는 default 셋팅으로..(default셋팅이 뭔지는 find..)
		
		this.dataSource = ds;
	}
	
	public static DataSource getDataSource(){
		return instance.dataSource;
	}
}
