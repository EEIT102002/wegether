package model;

import java.util.Arrays;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "MEMBER")
public class MemberBean {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
    	generator = "MEMBER_sq")
	private Integer id;
	private String account;
	private byte[] pwd;
	private Byte[] photo;
	private String name;
	private String nickname;
	private java.util.Date birthday;
	private Integer sex;
	private String job;
	private Integer city;
	private String addr;
	private String tel;
	private String content;
	private String favorite;
	@Column(insertable = false)
	private java.util.Date signupdate;
	private Double rank1;
	private Double rank2;
	private Double rank3;
	@Column(insertable = false)
	private Integer fans;
	@Column(insertable = false)
	private Integer notices;
	@Column(insertable = false)
	private Integer state;
	private String fbid;
	private String googleid;

	// Activity
	@OneToMany(mappedBy = "memberBean",
			   cascade = { CascadeType.REMOVE })
	private Set<ActivityBean> activityBean;
	public Set<ActivityBean> getActivityBean() {
		return activityBean;
	}
	public void setActivityBean(Set<ActivityBean> activityBean) {
		this.activityBean = activityBean;
	}
	
	// Attend
	@OneToMany(mappedBy = "memberBean",
			   cascade = { CascadeType.REMOVE })
	private Set<AttendBean> attendBean;
	public Set<AttendBean> getAttendBean() {
			return attendBean;
		}
		public void setAttendBean(Set<AttendBean> attendBean) {
			this.attendBean = attendBean;
		}

	// Article
	@OneToMany(mappedBy = "memberBean",
			   cascade = {CascadeType.REMOVE})
	private Set<ArticleBean> articleBean;
	public Set<ArticleBean> getArticleBean() {
		return articleBean;
	}
	public void setArticleBean(Set<ArticleBean> articleBean) {
		this.articleBean = articleBean;
	}
	
	//Friend
	@OneToMany(mappedBy = "memberBean",
			   cascade = {CascadeType.REMOVE})
	private Set<FriendBean> friendBean;
	public Set<FriendBean> getFriendBean() {
		return friendBean;
	}
	public void setFriendBean(Set<FriendBean> friendBean) {
		this.friendBean = friendBean;
	}

	@OneToMany(
			mappedBy="memberBean",
			cascade= {CascadeType.REMOVE}
			)
	private Set<PictureBean> pictureBean;
	public Set<PictureBean> getPictureBean() {
		return pictureBean;
	}
	public void setPictureBean(Set<PictureBean> pictureBean) {
		this.pictureBean = pictureBean;
	}
	@OneToOne
	@JoinColumn(
			name="id",
			referencedColumnName="memberid",
			insertable=false,updatable=false		
			)
	private SettingBean settingBean;
	public SettingBean getSettingBean() {
		return settingBean;
	}
	public void setSettingBean(SettingBean settingBean) {
		this.settingBean = settingBean;
	}

	// Service
	@OneToMany(mappedBy = "memberBean",
			   cascade = { CascadeType.REMOVE })
	private Set<ServiceBean> serviceBean;	
	public Set<ServiceBean> getServiceBean() {
			return serviceBean;
		}
		public void setServiceBean(Set<ServiceBean> serviceBean) {
			this.serviceBean = serviceBean;
		}
		
		
		//Invite memberid
		@OneToMany(
				cascade={CascadeType.REMOVE},
				mappedBy="memberBean"		
				)
		
		private  Set<InviteBean> inviteBean ;

		public Set<InviteBean> getInviteBean() {
			return inviteBean;
		}
		public void setInviteBean(Set<InviteBean> inviteBean) {
			this.inviteBean = inviteBean;
		}
		
		//Invite invited
		@OneToMany(
				cascade={CascadeType.REMOVE},
				mappedBy="memberBeaninvited"		
				)
		private  Set<InviteBean> inviteBeaninvited ;
		
		public Set<InviteBean> getInviteBeaninvited() {
			return inviteBeaninvited;
		}
		public void setInviteBeaninvited(Set<InviteBean> inviteBeaninvited) {
			this.inviteBeaninvited = inviteBeaninvited;
		}
		
	@Override
	public String toString() {
		return "MemberBean [id=" + id + ", account=" + account + ", pwd=" + Arrays.toString(pwd) + ", photo="
				+ Arrays.toString(photo) + ", name=" + name + ", nickname=" + nickname + ", birthday=" + birthday
				+ ", sex=" + sex + ", job=" + job + ", city=" + city + ", addr=" + addr + ", tel=" + tel + ", content="
				+ content + ", favorite=" + favorite + ", signupdate=" + signupdate + ", rank1=" + rank1 + ", rank2="
				+ rank2 + ", rank3=" + rank3 + ", fans=" + fans + ", notices=" + notices + ", state=" + state
				+ ", fbid=" + fbid + ", googleid=" + googleid + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public byte[] getPwd() {
		return pwd;
	}

	public void setPwd(byte[] pwd) {
		this.pwd = pwd;
	}

	public Byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(Byte[] photo) {
		this.photo = photo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public java.util.Date getBirthday() {
		return birthday;
	}

	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFavorite() {
		return favorite;
	}

	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}

	public java.util.Date getSignupdate() {
		return signupdate;
	}

	public void setSignupdate(java.util.Date signupdate) {
		this.signupdate = signupdate;
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

	public Integer getFans() {
		return fans;
	}

	public void setFans(Integer fans) {
		this.fans = fans;
	}

	public Integer getNotices() {
		return notices;
	}

	public void setNotices(Integer notices) {
		this.notices = notices;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getFbid() {
		return fbid;
	}

	public void setFbid(String fbid) {
		this.fbid = fbid;
	}

	public String getGoogleid() {
		return googleid;
	}

	public void setGoogleid(String googleid) {
		this.googleid = googleid;
	}
}
