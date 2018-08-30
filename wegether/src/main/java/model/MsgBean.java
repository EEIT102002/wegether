package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MSG")
public class MsgBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer activityid;
	private Integer articleid;
	private Integer memberid;
	@Column(insertable=false)
	private java.util.Date msgtime;
	private String content;
	@Column(insertable=false)
	private Integer state;
	
	@Override
	public String toString() {
		return "MsgBean [id=" + id + ", activityid=" + activityid + ", articleid=" + articleid + ", memberid="
				+ memberid + ", msgtime=" + msgtime + ", content=" + content + "]";
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
	public Integer getArticleid() {
		return articleid;
	}
	public void setArticleid(Integer articleid) {
		this.articleid = articleid;
	}
	public Integer getMemberid() {
		return memberid;
	}
	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}
	public java.util.Date getMsgtime() {
		return msgtime;
	}
	public void setMsgtime(java.util.Date msgtime) {
		this.msgtime = msgtime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
