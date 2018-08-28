package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FRIEND")
public class FriendBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer memberid;
	private Integer memberidf;
	private Integer state;
	
	@Override
	public String toString() {
		return "FriendBean [id=" + id + ", memberid=" + memberid + ", memberidf=" + memberidf + ", state=" + state
				+ "]";
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
	public Integer getMemberidf() {
		return memberidf;
	}
	public void setMemberidf(Integer memberidf) {
		this.memberidf = memberidf;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
