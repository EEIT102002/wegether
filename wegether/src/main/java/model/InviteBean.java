package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="INVITE")
public class InviteBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id ;
	private Integer memberid;
	private Integer invitedid;
	private Integer activityid;
	
	
	@Override
	public String toString() {
		return "InviteBean [id=" + id + ", memberid=" + memberid + ", invitedid=" + invitedid + ", activityid="
				+ activityid + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMemberid() {
		return memberid;
	}
	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}
	public Integer getInvitedid() {
		return invitedid;
	}
	public void setInvitedid(Integer invitedid) {
		this.invitedid = invitedid;
	}
	public Integer getActivityid() {
		return activityid;
	}
	public void setActivityid(Integer activityid) {
		this.activityid = activityid;
	}

}


//CREATE TABLE	invite	(	--7.推薦活動	
//id	Int	PRIMARY KEY IDENTITY,	--推薦活動id	
//memberid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--主邀會員id	
//invitedid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--被邀會員id	
//activityid	Int	NOT NULL  FOREIGN KEY REFERENCES activity(id), --活動id	
//constraint inv_uq unique (memberid, invitedid, activityid)
//)				