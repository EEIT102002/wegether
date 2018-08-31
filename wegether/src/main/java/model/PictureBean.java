package model;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;
@Entity
@Table(name="picture")
public class PictureBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer id;
	public byte[] picture;
	public Integer activityid;
	public Integer articleid;
	public Integer memberid;
	
	
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
