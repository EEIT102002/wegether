package model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Entity
@Scope("prototype")
@Table(name = "ARTICLE")
public class ArticleBean {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
    	generator = "ARTICLE_sq")
	@SequenceGenerator(allocationSize=1, name = "ARTICLE_sq")
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

	// member
	@ManyToOne
	@JoinColumn(name = "MEMBERID",
				referencedColumnName = "ID",
				insertable = false, updatable = false)
	private MemberBean memberBean;
	public MemberBean getMemberBean() {
		return memberBean;
	}
	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}
	
	// activity
	@ManyToOne
	@JoinColumn(name = "ACTIVITYID",
				referencedColumnName = "ID",
				insertable = false, updatable = false)
	private ActivityBean activityBean;
	public ActivityBean getActivityBean() {
		return activityBean;
	}
	public void setActivityBean(ActivityBean activityBean) {
		this.activityBean = activityBean;
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
