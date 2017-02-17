package member.store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import member.domain.Member;
import member.store.factory.ConnectionFactory;
import member.store.utils.JdbcUtils;

//mvc패턴, 유지보수를 위해서 for enterprise
//모델원방식이었으면 servlet에서 쿼리 날려버리면 된다(작은 프로젝트에서.. ex : 홈페이지)
public class MemberStoreLogic {
	
	private ConnectionFactory factory;
	
	public MemberStoreLogic() {
		this.factory = ConnectionFactory.getInstance();
	}
	
	public boolean insert(Member member){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					//(no, email, password, name, regDate)반드시 써줘라 컬럼 추가 삭제시 유지보수를 위해(컬럼 추가 삭제가 빈번하다)
					//마찬가지 이유로 select * 를 쓰지 않는다
					"insert into member_tb(no, email, password, name, regDate) values(member_seq.nextval,?,?,?,sysdate)"
					);
			
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, pstmt);
		}
				
		return count > 0;
	}
	
	public List<Member> selectAll(){
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Member> list = null;
		
		try {
			conn = this.factory.createConnection();
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(
					"select no, email, password, name, regDate from member_tb"
					);
			
			list = new ArrayList<>();
			while(rs.next()){
				Member member = new Member();
				member.setNo(rs.getInt("no"));
				member.setEmail(rs.getString("email"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setRegDate(rs.getDate("regDate"));
				
				list.add(member);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, stmt, rs);
		}
		
		return list;
	}
	
	public List<Member> selectByName(String name){
		List<Member> list = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"select no, email, password, name, regDate from member_tb where name = ?"
					);
			
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rs.next()){
				
				Member member = new Member();
				member.setNo(rs.getInt("no"));
				member.setEmail(rs.getString("email"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setRegDate(rs.getDate("regDate"));
				
				list.add(member);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			JdbcUtils.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	public Member checkById(int no){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = null;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"select no, email, password, name, regDate from member_tb where no = ?"
					);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				member = new Member();
				
				member.setNo(rs.getInt("no"));
				member.setEmail(rs.getString("email"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setRegDate(rs.getDate("regDate"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, pstmt, rs);
		}
		
		return member;
	}
	
	public boolean delete(Member member){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"delete from member_tb where no = ?"
					);
			
			pstmt.setInt(1, member.getNo());
			
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, pstmt);
		}
		
		return count > 0;
	}
	
	public Member selectDetail(int no){
		Member member = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"select no, email, password, name, regDate from member_tb where no = ?"
					);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			
			if(rs.next()){
				
				member = new Member();
				member.setNo(rs.getInt("no"));
				member.setEmail(rs.getString("email"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setRegDate(rs.getDate("regDate"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			JdbcUtils.close(conn, pstmt, rs);
		}
		
		return member;
	}
	
}
