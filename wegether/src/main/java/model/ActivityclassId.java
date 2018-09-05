package model;
// Generated 2018/9/5 �W�� 10:19:24 by Hibernate Tools 5.2.8.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ActivityclassId generated by hbm2java
 */
@Embeddable
public class ActivityclassId implements java.io.Serializable {

	private int classid;
	private int activityid;

	public ActivityclassId() {
	}

	public ActivityclassId(int classid, int activityid) {
		this.classid = classid;
		this.activityid = activityid;
	}

	@Column(name = "classid", nullable = false)
	public int getClassid() {
		return this.classid;
	}

	public void setClassid(int classid) {
		this.classid = classid;
	}

	@Column(name = "activityid", nullable = false)
	public int getActivityid() {
		return this.activityid;
	}

	public void setActivityid(int activityid) {
		this.activityid = activityid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ActivityclassId))
			return false;
		ActivityclassId castOther = (ActivityclassId) other;

		return (this.getClassid() == castOther.getClassid()) && (this.getActivityid() == castOther.getActivityid());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getClassid();
		result = 37 * result + this.getActivityid();
		return result;
	}

}
