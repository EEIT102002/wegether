package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="setting")
public class SettingBean {
			@Id
			@TableGenerator(name="mySetting",table="member")
			@GeneratedValue(generator="mySetting", strategy=GenerationType.TABLE)
			
			public Integer memberid;
			public Integer birthday;
			public Integer sex;
			public Integer job;
			public Integer city;
			public Integer addr;
			public Integer tel;
			public Integer content;
			public Integer favorite;
			public Integer showarticle;
			public Integer showhost;
			public Integer showactivity;
			public Integer rankscore;
			public Integer invite;
			public Integer addfriend;
			public Integer activitymsg;
			public Integer articlemsg;
			public Integer activitychange;
			public Integer friendarticle;
			public Integer friendactivity;
			
			@Override
			public String toString() {
				return "SettingBean [memberid=" + memberid + ", birthday=" + birthday + ", sex=" + sex + ", job=" + job
						+ ", city=" + city + ", addr=" + addr + ", tel=" + tel + ", content=" + content + ", favorite="
						+ favorite + ", showarticle=" + showarticle + ", showhost=" + showhost + ", showactivity="
						+ showactivity + ", rankscore=" + rankscore + ", invite=" + invite + ", addfriend=" + addfriend
						+ ", activitymsg=" + activitymsg + ", articlemsg=" + articlemsg + ", activitychange="
						+ activitychange + ", friendarticle=" + friendarticle + ", friendactivity=" + friendactivity
						+ "]";
			}
			public Integer getMemberid() {
				return memberid;
			}
			public void setMemberid(Integer memberid) {
				this.memberid = memberid;
			}
			public Integer getBirthday() {
				return birthday;
			}
			public void setBirthday(Integer birthday) {
				this.birthday = birthday;
			}
			public Integer getSex() {
				return sex;
			}
			public void setSex(Integer sex) {
				this.sex = sex;
			}
			public Integer getJob() {
				return job;
			}
			public void setJob(Integer job) {
				this.job = job;
			}
			public Integer getCity() {
				return city;
			}
			public void setCity(Integer city) {
				this.city = city;
			}
			public Integer getAddr() {
				return addr;
			}
			public void setAddr(Integer addr) {
				this.addr = addr;
			}
			public Integer getTel() {
				return tel;
			}
			public void setTel(Integer tel) {
				this.tel = tel;
			}
			public Integer getContent() {
				return content;
			}
			public void setContent(Integer content) {
				this.content = content;
			}
			public Integer getFavorite() {
				return favorite;
			}
			public void setFavorite(Integer favorite) {
				this.favorite = favorite;
			}
			public Integer getShowarticle() {
				return showarticle;
			}
			public void setShowarticle(Integer showarticle) {
				this.showarticle = showarticle;
			}
			public Integer getShowhost() {
				return showhost;
			}
			public void setShowhost(Integer showhost) {
				this.showhost = showhost;
			}
			public Integer getShowactivity() {
				return showactivity;
			}
			public void setShowactivity(Integer showactivity) {
				this.showactivity = showactivity;
			}
			public Integer getRankscore() {
				return rankscore;
			}
			public void setRankscore(Integer rankscore) {
				this.rankscore = rankscore;
			}
			public Integer getInvite() {
				return invite;
			}
			public void setInvite(Integer invite) {
				this.invite = invite;
			}
			public Integer getAddfriend() {
				return addfriend;
			}
			public void setAddfriend(Integer addfriend) {
				this.addfriend = addfriend;
			}
			public Integer getActivitymsg() {
				return activitymsg;
			}
			public void setActivitymsg(Integer activitymsg) {
				this.activitymsg = activitymsg;
			}
			public Integer getArticlemsg() {
				return articlemsg;
			}
			public void setArticlemsg(Integer articlemsg) {
				this.articlemsg = articlemsg;
			}
			public Integer getActivitychange() {
				return activitychange;
			}
			public void setActivitychange(Integer activitychange) {
				this.activitychange = activitychange;
			}
			public Integer getFriendarticle() {
				return friendarticle;
			}
			public void setFriendarticle(Integer friendarticle) {
				this.friendarticle = friendarticle;
			}
			public Integer getFriendactivity() {
				return friendactivity;
			}
			public void setFriendactivity(Integer friendactivity) {
				this.friendactivity = friendactivity;
			}
	

}
