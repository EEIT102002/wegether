package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "trackmember")
@IdClass(TrackmemberBean.class)
public class TrackmemberBean {

	 @Id 
	 private Integer memberid;
	 @Id 
	 private Integer fanid;
	public Integer getMemberid() {
		return memberid;
	}
	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}
	public Integer getFanid() {
		return fanid;
	}
	public void setFanid(Integer fanid) {
		this.fanid = fanid;
	}
	
	

}
