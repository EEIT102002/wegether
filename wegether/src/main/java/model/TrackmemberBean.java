package model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

//@Entity
//@Table(name = "trackmemberid")
public class TrackmemberBean implements Serializable{

	private static final long serialVersionUID = 432954984112471789L;
	@EmbeddedId
	private TrackmemberId trackmemberId;
	public TrackmemberId getTrackmemberId() {
		return trackmemberId;
	}
	public void setTrackmemberId(TrackmemberId trackmemberId) {
		this.trackmemberId = trackmemberId;
	}
	
	
	



}
