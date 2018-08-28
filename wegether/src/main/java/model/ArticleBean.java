package model;

public class ArticleBean {
	private Integer id;
	private Integer memberid;
	private Integer activityid;
	private String content;
	private java.util.Date createtime;

	@Override
	public String toString() {
		return "AttendBean [id=" + id + ", memberid=" + memberid + ", activityid=" + activityid + ", content=" + content
				+ ", createtime=" + createtime + "]";
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public java.util.Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}
}
