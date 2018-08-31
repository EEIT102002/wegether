package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ATTEND")
public class AttendBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer activityid;
	private Integer memberid;
	@Column(insertable = false)
	private java.util.Date createtime;
	private String form;
	private Integer rank1;
	private Integer rank2;
	private Integer rank3;
	private Integer state;
	
//	//activity
//	@ManyToOne
//	@JoinColumn(
//			name="ACTIVITYID",
//			referencedColumnName="ID",
//			insertable=false, updatable=false
//			)
//	private ActivityBean activityBean;			
//	public ActivityBean getActivityBean() {
//		return activityBean;
//	}
//	public void setActivityBean(ActivityBean activityBean) {
//		this.activityBean = activityBean;
//	}
//
//	//member
//	@ManyToOne
//	@JoinColumn(
//			name="MEMBERID",
//			referencedColumnName="ID",
//			insertable=false, updatable=false
//			)
//	private MemberBean memberBean;		
//	public MemberBean getMemberBean() {
//			return memberBean;
//		}
//		public void setMemberBean(MemberBean memberBean) {
//			this.memberBean = memberBean;
//		}


	@Override
	public String toString() {
		return "AttendBean [id=" + id + ", activityid=" + activityid + ", memberid=" + memberid + ", createtime="
				+ createtime + ", form=" + form + ", rank1=" + rank1 + ", rank2=" + rank2 + ", rank3=" + rank3
				+ ", state=" + state + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getActivityid() {
		return activityid;
	}

	public void setActivityid(Integer activityid) {
		this.activityid = activityid;
	}

	public Integer getMemberid() {
		return memberid;
	}

	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}

	public java.util.Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public Integer getRank1() {
		return rank1;
	}

	public void setRank1(Integer rank1) {
		this.rank1 = rank1;
	}

	public Integer getRank2() {
		return rank2;
	}

	public void setRank2(Integer rank2) {
		this.rank2 = rank2;
	}

	public Integer getRank3() {
		return rank3;
	}

	public void setRank3(Integer rank3) {
		this.rank3 = rank3;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}
