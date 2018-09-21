package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name="noticeview")
@Immutable
public class NoticeviewBean {
	@Id
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private Integer memberid;
	private Integer activityid;
	private Integer articleid;
	private Integer attendid;
	private Integer inviteid;
	private Integer friendid;
	private Integer msgcount;
	private Integer msgcountu;
	private Integer ntype;
	@Column(insertable=false)
	private Integer state;
	private String content;
	@Column(insertable=false)
	private Date noticetime;
	private String cnickname;
	private Integer cmemberid;
	private String ctitle;
	private Integer cactivityid;
	
	@Override
	public String toString() {
		return String.format("id = %-10s, memberid = %-10s, noticetime = %-20s ", id, memberid,noticetime);
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
	public Integer getAttendid() {
		return attendid;
	}
	public void setAttendid(Integer attendid) {
		this.attendid = attendid;
	}
	public Integer getInviteid() {
		return inviteid;
	}
	public void setInviteid(Integer inviteid) {
		this.inviteid = inviteid;
	}
	public Integer getFriendid() {
		return friendid;
	}
	public void setFriendid(Integer friendid) {
		this.friendid = friendid;
	}
	public Integer getMsgcount() {
		return msgcount;
	}
	public void setMsgcount(Integer msgcount) {
		this.msgcount = msgcount;
	}
	public Integer getMsgcountu() {
		return msgcountu;
	}
	public void setMsgcountu(Integer msgcountu) {
		this.msgcountu = msgcountu;
	}
	public Integer getNtype() {
		return ntype;
	}
	public void setNtype(Integer ntype) {
		this.ntype = ntype;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getNoticetime() {
		return noticetime;
	}
	public void setNoticetime(Date noticetime) {
		this.noticetime = noticetime;
	}
	public String getCnickname() {
		return cnickname;
	}
	public void setCnickname(String cnickname) {
		this.cnickname = cnickname;
	}
	public Integer getCmemberid() {
		return cmemberid;
	}
	public void setCmemberid(Integer cmemberid) {
		this.cmemberid = cmemberid;
	}
	public String getCtitle() {
		return ctitle;
	}
	public void setCtitle(String ctitle) {
		this.ctitle = ctitle;
	}
	public Integer getCactivityid() {
		return cactivityid;
	}
	public void setCactivityid(Integer cactivityid) {
		this.cactivityid = cactivityid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}

