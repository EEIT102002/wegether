package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Table(name="PICTURE")
public class PictureBean {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
    	generator = "PICTURE_sq")
	@SequenceGenerator(allocationSize = 1, name = "PICTURE_sq")
	public Integer id;
	
	
	public byte[] picture;
	public Integer activityid;
	public Integer articleid;
	public Integer memberid;


	//activity
	@ManyToOne
	@JoinColumn(
			name="ACTIVITYID",
			referencedColumnName="ID",
			insertable=false, updatable=false
			)
	private ActivityBean activityBean;		
	public ActivityBean getActivityBean() {
		return activityBean;
	}
	public void setActivityBean(ActivityBean activityBean) {
		this.activityBean = activityBean;
	}
	
	//article
	@ManyToOne
	@JoinColumn(
			name="ARTICLEID",
			referencedColumnName="ID",
			insertable=false, updatable=false
			)
	private ArticleBean articleBean;		
	public ArticleBean getArticleBean() {
		return articleBean;
	}
	public void setArticleBean(ArticleBean articleBean) {
		this.articleBean = articleBean;
	}
	
	//member
	@ManyToOne
	@JoinColumn(
			name="MEMBERID",
			referencedColumnName="ID",
			insertable=false, updatable=false
			)
	private MemberBean memberBean;		
	public MemberBean getMemberBean() {
			return memberBean;
		}
		public void setMemberBean(MemberBean memberBean) {
			this.memberBean = memberBean;
		}
		
	@Override
	public String toString() {
		return "PictureBean [id=" + id + ", activityid=" + activityid
				+ ", articleid=" + articleid + ", memberid=" + memberid + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
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

	
}
