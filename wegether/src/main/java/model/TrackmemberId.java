package model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

//@Embeddable
public class TrackmemberId implements Serializable {
	private static final long serialVersionUID = 1L;
	 @Column(name = "memberid")
	private Integer memberid;
	 @Column(name = "fanid")
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
	
	public int hashCode() {

        return Objects.hash(getMemberid(), getFanid());

    }

}
