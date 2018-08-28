package model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "BLACKLIST")
public class BlacklistBean {
	private Integer memberid;
	private Integer blackid;

	@Override
	public String toString() {
		return "BlacklistBean [memberid=" + memberid + ", blackid=" + blackid + "]";
	}

	public Integer getMemberid() {
		return memberid;
	}

	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}

	public Integer getBlackid() {
		return blackid;
	}

	public void setBlackid(Integer blackid) {
		this.blackid = blackid;
	}
}
