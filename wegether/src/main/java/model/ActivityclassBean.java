package model;
// Generated 2018/9/5 �W�� 10:19:24 by Hibernate Tools 5.2.8.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Activityclass generated by hbm2java
 */
@Entity
@Table(name = "activityclass", schema = "dbo", catalog = "wegether")
public class ActivityclassBean implements java.io.Serializable {

	private ActivityclassId id;
	private ActivityBean activity;

	public ActivityclassBean() {
	}

	public ActivityclassBean(ActivityclassId id, ActivityBean activity) {
		this.id = id;
		this.activity = activity;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "classid", column = @Column(name = "classid", nullable = false)),
			@AttributeOverride(name = "activityid", column = @Column(name = "activityid", nullable = false)) })
	public ActivityclassId getId() {
		return this.id;
	}

	public void setId(ActivityclassId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "activityid", nullable = false, insertable = false, updatable = false)
	public ActivityBean getActivity() {
		return this.activity;
	}

	public void setActivity(ActivityBean activity) {
		this.activity = activity;
	}

}
