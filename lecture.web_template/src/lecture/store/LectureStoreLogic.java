package lecture.store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lecture.domain.Lecture;
import lecture.store.factory.ConnectionFactory;
import lecture.store.utils.JdbcUtils;

/**
 * LectureStoreLogic
 * 
 * @since 2016. 3. 14.
 * @author 김은영 (eykim@nextree.co.kr)
 */
public class LectureStoreLogic implements LectureStore {

	//private static Map<String, Lecture> lectureRepository = new HashMap<String, Lecture>();
	
	private ConnectionFactory factory;
	
	public LectureStoreLogic() {
		this.factory = ConnectionFactory.getInstance();
	}
	
	@Override
	public void create(Lecture lecture) {
		//
		//lectureRepository.put(lecture.getId(), lecture);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"insert into lecture_tb(id, lecturename, instructor, introduce) values(?,?,?,?)"
					);
			
			pstmt.setString(1, lecture.getId());
			pstmt.setString(2, lecture.getLectureName());
			pstmt.setString(3, lecture.getInstructor());
			pstmt.setString(4, lecture.getIntroduce());
			
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, pstmt);
		}
		
	}

	@Override
	public Lecture read(String lectureId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Lecture lecture = null;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"select id, lectureName, instructor, introduce from lecture_tb where id = ?"
					);
			
			pstmt.setString(1, lectureId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				lecture = new Lecture();
				lecture.setId(rs.getString("id"));
				lecture.setLectureName(rs.getString("lectureName"));
				lecture.setInstructor(rs.getString("instructor"));
				lecture.setIntroduce(rs.getString("introduce"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, pstmt, rs);
		}
		
		//
		return lecture;
	}

	@Override
	public void update(Lecture lecture) {
		//
		//lectureRepository.put(lecture.getId(), lecture);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"update lecture_tb set lectureName = ?, instructor = ?, introduce = ? where id = ?"
					);
			
			pstmt.setString(1, lecture.getLectureName());
			pstmt.setString(2, lecture.getInstructor());
			pstmt.setString(3, lecture.getIntroduce());
			pstmt.setString(4, lecture.getId());
			
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, pstmt);
		}
	}

	@Override
	public void delete(String lectureId) {
		//
		//lectureRepository.remove(lectureId);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"delete from lecture_tb where id = ?"
					);
			
			pstmt.setString(1, lectureId);
			
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, pstmt);
		}
	}

	@Override
	public List<Lecture> readAll() {
		//
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Lecture> list = null;
		Lecture lecture = null;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"select id, lecturename, instructor, introduce from lecture_tb"
					);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rs.next()){
				lecture = new Lecture();
				lecture.setId(rs.getString("id"));
				lecture.setLectureName(rs.getString("lectureName"));
				lecture.setInstructor(rs.getString("instructor"));
				lecture.setIntroduce(rs.getString("introduce"));
				
				list.add(lecture);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			JdbcUtils.close(conn, pstmt, rs);
		}
		
		return list;
	}

}
