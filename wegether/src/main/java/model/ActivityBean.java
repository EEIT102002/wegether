package model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ACTIVITY")

public class ActivityBean {
	
	@Id
	private Integer id;
	private Integer hostid;
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
	private BigDecimal rank1;
	private BigDecimal rank2;
	private BigDecimal rank3;
	private Integer judges;
	private String form;
	private Integer click;
	
	
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
	public BigDecimal getRank1() {
		return rank1;
	}
	public void setRank1(BigDecimal rank1) {
		this.rank1 = rank1;
	}
	public BigDecimal getRank2() {
		return rank2;
	}
	public void setRank2(BigDecimal rank2) {
		this.rank2 = rank2;
	}
	public BigDecimal getRank3() {
		return rank3;
	}
	public void setRank3(BigDecimal rank3) {
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
