package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="SERVICE")
public class ServiceBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
    	generator = "SERVICE_sq")
	@SequenceGenerator(allocationSize = 1, name = "PICTURE_sq")
	private Integer id ;
	private Integer memberid ;
	@Column(insertable = false)
	private java.util.Date asktime ;
	private  String title ;
	private Integer classtype ;
	private String content ;
	
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
		return "ServiceBean [id=" + id + ", memberid=" + memberid + ", asktime=" + asktime + ", title=" + title
				+ ", classtype=" + classtype + ", content=" + content + "]";
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
	public java.util.Date getAsktime() {
		return asktime;
	}
	public void setAsktime(java.util.Date asktime) {
		this.asktime = asktime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getClasstype() {
		return classtype;
	}
	public void setClasstype(Integer classtype) {
		this.classtype = classtype;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
   
	
//	CREATE TABLE	service	(	--9.客服資訊Table	
//			id	Int	PRIMARY KEY IDENTITY,	--客服資訊id	
//			memberid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--會員id	
//			asktime	datetime not null default getdate(),		--發問時間	
//			title	nvarchar(50) 	NOT NULL,	--問題名稱	
//			class	Int not null,		--問題類型	
//			content	nvarchar(max) 	NOT NULL	--問題描述	
//			)

}
