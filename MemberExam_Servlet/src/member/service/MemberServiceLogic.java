package member.service;

import java.util.List;

import member.domain.Member;
import member.store.MemberStoreLogic;

//mvc패턴
public class MemberServiceLogic {
	private MemberStoreLogic store;
	
	public MemberServiceLogic() {
		this.store = new MemberStoreLogic();
	}
	
	public boolean registerMember(Member member){
		return this.store.insert(member);
	}
	
	public List<Member> searchAll(){
		return this.store.selectAll();
	}
	
	public List<Member> searchByName(String name){
		return this.store.selectByName(name);
	}
	
	public Member checkById(int no){
		return this.store.checkById(no);
	}
	
	public boolean deleteMember(Member member){
		return this.store.delete(member);
	}
	
	public Member searchDetail(int no){
		return this.store.selectDetail(no);
	}
}
