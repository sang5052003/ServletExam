package lecture.service;

import java.util.List;

import lecture.domain.Lecture;
import lecture.store.LectureStore;
import lecture.store.LectureStoreLogic;

/**
 * LectureServiceLogic
 * 
 * @since 2016. 3. 14.
 * @author 김은영 (eykim@nextree.co.kr)
 */
public class LectureServiceLogic implements LectureService {

	private LectureStore store;

	public LectureServiceLogic() {
		//
		
		this.store = new LectureStoreLogic();
	}

	@Override
	public void register(Lecture lecture) {
		//
		String lectureId = this.nextId();
		lecture.setId(lectureId);
		this.store.create(lecture);
	}

	private String nextId() {
		//
		List<Lecture> allLectures = this.findAll();
		if (allLectures.isEmpty()) {
			return "0";
		}

		int maxId = Integer.MIN_VALUE;
		for (Lecture lecture : allLectures) {
			int lectureId = Integer.parseInt(lecture.getId());
			if (maxId < lectureId) {
				maxId = lectureId;
			}
		}

		return String.valueOf(maxId + 1);
	}

	@Override
	public Lecture find(String lectureId) {
		
		Lecture lecture = this.store.read(lectureId);
		if (lecture != null) {
			return lecture;
		}
		throw new RuntimeException("저장된 강좌가 없습니다.");
	}

	@Override
	public void modify(Lecture lecture) {
		//
		this.store.update(lecture);
	}

	@Override
	public void remove(String lectureId) {
		//
		this.store.delete(lectureId);
	}

	@Override
	public List<Lecture> findAll() {
		//
		return this.store.readAll();
	}

}
