package model;

import java.util.Arrays;
import java.util.Date;
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
import javax.persistence.Table;

@Entity
@Table(name="ACTIVITY")
public class ActivityBean {
	
	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer hostid;
	@Column(insertable = false)
	private Date createtime;
	private String title;
	private Integer city;
	private String addr;
	private byte[] picture;
	private Date actbegin;
	private Date actend;
	private Date dateline;
	private String classtype;
	private String content;
	private Integer numberlimit;
	private Integer feed;
	private Integer state;
	private Double rank1;
	private Double rank2;
	private Double rank3;
	private Integer judges;
	private String form;
	private Integer click;
	
	//member
	@ManyToOne
	@JoinColumn(
			name="HOSTID",
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
		/*
	//picture
	@OneToMany(
			mappedBy="activityBean",
			cascade= {CascadeType.REMOVE}
			)
	private Set<PictureBean> pictureBean;	
	public Set<PictureBean> getPictureBean() {
		return pictureBean;
	}
	public void setPictureBean(Set<PictureBean> pictureBean) {
		this.pictureBean = pictureBean;
	}
	
	//article
	@OneToMany(
			mappedBy="articleBean",
			cascade= {CascadeType.REMOVE}
			)
	private Set<ArticleBean> articleBean;	
	public Set<ArticleBean> getArticleBean() {
		return articleBean;
	}
	public void setArticleBean(Set<ArticleBean> articleBean) {
		this.articleBean = articleBean;
	}
	
	//msg
	@OneToMany(
			mappedBy="msgBean",
			cascade= {CascadeType.REMOVE}
			)
	private Set<MsgBean> msgBean;	
	public Set<MsgBean> getMsgBean() {
		return msgBean;
	}
	public void setMsgBean(Set<MsgBean> msgBean) {
		this.msgBean = msgBean;
	}
	
	//attend
	@OneToMany(
			mappedBy="attendBean",
			cascade= {CascadeType.REMOVE}
			)
	private Set<AttendBean> attendBean;
	public Set<AttendBean> getAttendBean() {
		return attendBean;
	}
	public void setAttendBean(Set<AttendBean> attendBean) {
		this.attendBean = attendBean;
	}
	
	//invite
	@OneToMany(
			mappedBy="inviteBean",
			cascade= {CascadeType.REMOVE}
			)
	private Set<InviteBean> inviteBean;
	public Set<InviteBean> getInviteBean() {
		return inviteBean;
	}
	public void setInviteBean(Set<InviteBean> inviteBean) {
		this.inviteBean = inviteBean;
	}
	
	//notice
	@OneToMany(
			mappedBy="noticeBean",
			cascade= {CascadeType.REMOVE}
			)
	private Set<NoticeBean> noticeBean;
	public Set<NoticeBean> getNoticeBean() {
		return noticeBean;
	}
	public void setNoticeBean(Set<NoticeBean> noticeBean) {
		this.noticeBean = noticeBean;
	}
	
	//trackactivity
	@OneToMany(
			mappedBy="trackactivityBean",
			cascade= {CascadeType.REMOVE}
			)
	private Set<TrackactivityBean> trackactivityBean;
	public Set<TrackactivityBean> getTrackactivityBean() {
		return trackactivityBean;
	}
	public void setTrackactivityBean(Set<TrackactivityBean> trackactivityBean) {
		this.trackactivityBean = trackactivityBean;
	}

	//activityclass
	@OneToMany(
			mappedBy="activityclassBean",
			cascade= {CascadeType.REMOVE}
			)
	private Set<ActivityclassBean> activityclassBean;
	public Set<ActivityclassBean> getActivityclassBean() {
		return activityclassBean;
	}
	public void setActivityclassBean(Set<ActivityclassBean> activityclassBean) {
		this.activityclassBean = activityclassBean;
	}	
	*/
	
	@Override
	public String toString() {
		return "ActivityBean [id=" + id + ", hostid=" + hostid + ", createtime=" + createtime + ", title=" + title
				+ ", city=" + city + ", addr=" + addr + ", picture=" + Arrays.toString(picture) + ", actbegin="
				+ actbegin + ", actend=" + actend + ", dateline=" + dateline + ", classtype=" + classtype + ", content="
				+ content + ", numberlimit=" + numberlimit + ", feed=" + feed + ", state=" + state + ", rank1=" + rank1
				+ ", rank2=" + rank2 + ", rank3=" + rank3 + ", judges=" + judges + ", form=" + form + ", click=" + click
				+ "]";
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getHostid() {
		return hostid;
	}
	public void setHostid(Integer hostid) {
		this.hostid = hostid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	public Date getActbegin() {
		return actbegin;
	}
	public void setActbegin(Date actbegin) {
		this.actbegin = actbegin;
	}
	public Date getActend() {
		return actend;
	}
	public void setActend(Date actend) {
		this.actend = actend;
	}
	public Date getDateline() {
		return dateline;
	}
	public void setDateline(Date dateline) {
		this.dateline = dateline;
	}
	public String getClasstype() {
		return classtype;
	}
	public void setClasstype(String classtype) {
		this.classtype = classtype;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getNumberlimit() {
		return numberlimit;
	}
	public void setNumberlimit(Integer numberlimit) {
		this.numberlimit = numberlimit;
	}
	public Integer getFeed() {
		return feed;
	}
	public void setFeed(Integer feed) {
		this.feed = feed;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Double getRank1() {
		return rank1;
	}
	public void setRank1(Double rank1) {
		this.rank1 = rank1;
	}
	public Double getRank2() {
		return rank2;
	}
	public void setRank2(Double rank2) {
		this.rank2 = rank2;
	}
	public Double getRank3() {
		return rank3;
	}
	public void setRank3(Double rank3) {
		this.rank3 = rank3;
	}
	public Integer getJudges() {
		return judges;
	}
	public void setJudges(Integer judges) {
		this.judges = judges;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public Integer getClick() {
		return click;
	}
	public void setClick(Integer click) {
		this.click = click;
	}
	
	
	
	
	
	
}
