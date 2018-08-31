package model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ARTICLE")
public class ArticleBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer memberid;
	private Integer activityid;
	private String content;
	@Column(insertable = false)
	private java.util.Date createtime;
	
	@OneToMany(
			mappedBy="articleBean",
			cascade= {CascadeType.REMOVE}
			)
	private Set<PictureBean> PictureBean;
	public Set<PictureBean> getPictureBean() {
		return PictureBean;
	}

	public void setPictureBean(Set<PictureBean> pictureBean) {
		PictureBean = pictureBean;
	}

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
