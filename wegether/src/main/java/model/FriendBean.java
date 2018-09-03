package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.bytebuddy.matcher.FailSafeMatcher;

@Entity
@Table(name="FRIEND")
public class FriendBean {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, 
    	generator = "FRIEND_sq")
	@SequenceGenerator(allocationSize = 1, name = "FRIEND_sq")
	private Integer id;
	private Integer memberid;
	private Integer memberidf;
	private Integer state;
	
	@ManyToOne
	@JoinColumn(name = "MEMBERID, MEMBERIDF",
				referencedColumnName = "ID",
				insertable = false, updatable = false)
	private MemberBean memberBean;
	public MemberBean getMemberBean() {
		return memberBean;
	}
	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}
	
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
