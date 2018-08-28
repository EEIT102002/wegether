drop database wegether

CREATE DATABASE wegether
GO

USE wegether
GO

CREATE TABLE  member	(	--5.會員Table	
id	Int	PRIMARY KEY IDENTITY,	--會員id	
account	varchar(50) UNIQUE NOT NULL,	--帳號	
pwd	varbinary(50) NOT NULL,	--密碼
photo	varbinary(max) , --大頭照,	
name	nvarchar(50)	NOT NULL,	--姓名	
nickname	nvarchar(50) ,	--暱稱	
birthday	date not null,		--生日	
sex	int  check(sex between 0 and 1)	,	--性別	
job	nvarchar(50), 		--職業	
city	int, 		--縣市	
addr	nvarchar(50), 		--詳細地址	
tel	char(20), 		--電話	
content	nvarchar(max), 		--自我介紹	
favorite	varchar(max) 	,	--喜好活動類型	
signupdate	datetime not null default getdate(),		--註冊日期		
rank1	decimal(2,1) check(rank1 between 0 and 5),		--活動滿意度	
rank2	decimal(2,1) check(rank2 between 0 and 5),		--事前溝通安排	
rank3	decimal(2,1) check(rank3 between 0 and 5),		--時間地點選擇	
fans	Int not null default 0,		--追蹤數	
notices	Int not null default 0,		--提醒	
state	Int	not null default 0 check(state between 0 and 1)	--認證狀態
,fbid nchar(100)
,googleid nchar(100)
)	
GO

CREATE TABLE	setting	(	---14會員個人設定	
memberid Int primary key REFERENCES member(id),	--會員id	
birthday Int not null default 0 check(birthday between 0 and 2),		--生日	
sex	Int not null default 0 check(sex between 0 and 2),		--性別	
job	Int not null default 0 check(job between 0 and 2),		--職業	
city Int not null default 0 check(city between 0 and 2),		--縣市		
addr Int not null default 0 check(addr between 0 and 2),		--詳細地址	
tel	Int not null default 0 check(tel between 0 and 2),		--電話	
content	Int not null default 0 check(content between 0 and 2),		--自我介紹	
favorite	Int not null default 0 check(class between 0 and 2),		--喜好活動類型	
showarticle	Int not null default 0 check(showarticle between 0 and 2),		--Po文顯示	
showhost	Int not null default 0 check(showhost between 0 and 2),		--主辦活動顯示	
showactivity	Int not null default 0 check(showactivity between 0 and 2),		--參加活動紀錄	
rankscore	Int not null default 0 check(rankscore between 0 and 2),		--評分	
invite	Int not null default 0 check(invite between 0 and 1),		--活動邀請		
addfriend	Int not null default 0 check(addfriend between 0 and 1),	--加好友
activitymsg Int not null default 0 check(activitymsg between 0 and 1), --活動留言通知
articlemsg Int not null default 0 check(articlemsg between 0 and 1), --文章留言通知
activitychange Int not null default 0 check(activitychange between 0 and 1), --活動變更通知
friendarticle Int not null default 0 check(friendarticle between 0 and 1), --好友發文通知
friendactivity Int not null default 0 check(friendactivity between 0 and 1),	--好友活動通知	
)
GO

CREATE TABLE	activity	(	--1.活動資訊Table	
id	Int	PRIMARY KEY IDENTITY,	--活動id	
hostid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--主辦人id	
createtime	datetime not null default getdate(),		--創建時間
title	nvarchar(50) 	NOT NULL,	--活動名稱	
city	int	,	--縣市	
addr	nvarchar(100) ,	--詳細地址	
picture	varbinary(max),	--活動縮圖
actbegin	datetime	NOT NULL,	--活動開始時間	
actend	datetime,		--活動結束時間	
dateline	datetime NOT NULL ,		--報名截止日期	
classtype	varchar(max) ,	--活動類型 
content	nvarchar(max) 	NOT NULL,	--活動描述	
numberlimit	Int ,		--活動人數上限			
feed	Int default 0,		--活動費用	
state	Int	NOT NULL default 0 check(state between 0 and 2) ,	--活動狀態(0 活動進行 1 活動結束 2活動取消
rank1	decimal(2,1) check(rank1 between 0 and 5),		--活動滿意度平均	
rank2	decimal(2,1) check(rank2 between 0 and 5),		--事前溝通安排平均	
rank3	decimal(2,1) check(rank3 between 0 and 5),		--時間地點選擇平均	
judges	Int default 0,		--評分人數	
form	nvarchar(max),		--報名表單	
click	Int not null default 0		--點閱率	
)	

CREATE TABLE	article	(	--4.PO文資訊Table	
id	Int	PRIMARY KEY IDENTITY,	--PO文id	
memberid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--會員id	
activityid	Int	FOREIGN KEY REFERENCES activity(id), 	--活動id	
content	nvarchar(max) 	NOT NULL,	--PO內容	
createtime	datetime not null default getdate()		--PO時間		
)	
GO

CREATE TABLE	msg	(	--2.活動.PO文留言Table	
id	Int	PRIMARY KEY IDENTITY,	--留言id	
activityid	Int	FOREIGN KEY REFERENCES activity(id),	--活動id	
articleid	Int	FOREIGN KEY REFERENCES article(id),	--PO文id	
memberid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--會員id	
msgtime	datetime not null default getdate(),		--留言時間	
content	nvarchar(200) 	NOT NULL,	--留言內容	
constraint msg_chk check(
	(case when activityid is null then 0 else 1 end)+
	(case when articleid is null then 0 else 1  end)=1
	)
)	
GO

CREATE TABLE	attend(	--3.參加名單Table	
id	Int	PRIMARY KEY IDENTITY,	--參加名單id	
activityid	Int	NOT NULL  FOREIGN KEY REFERENCES activity(id),	--活動id	
memberid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--會員id	
createtime	datetime not null default getdate(),		--報名時間	
form	nvarchar(max),		--報名表單
rank1	Int check(rank1 between 0 and 5),		--活動滿意度	
rank2	Int check(rank2 between 0 and 5),		--事前溝通安排	
rank3	Int check(rank3 between 0 and 5),		--時間地點選擇	
state	Int	NOT NULL check(state between 0 and 3),	--報名狀態 0:報名中 1:報名成功 2:報名失敗 3:活動主辦人邀請
constraint act_member_uq unique (activityid,memberid)
)	
GO

CREATE TABLE	friend	(	--6.好友名單Table	
id	Int	PRIMARY KEY IDENTITY,	--好友名單id	
memberid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--會員id	
memberidf	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--好友id	
state	Int	NOT NULL check(state between 0 and 2),	--狀態	 0:申請中 1:好友 2:拒絕
constraint friend_uq unique (memberid, memberidf)
)
GO

CREATE TABLE	invite	(	--7.推薦活動	
id	Int	PRIMARY KEY IDENTITY,	--推薦活動id	
memberid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--主邀會員id	
invitedid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--被邀會員id	
activityid	Int	NOT NULL  FOREIGN KEY REFERENCES activity(id), --活動id	
constraint inv_uq unique (memberid, invitedid, activityid)
)				
GO

CREATE TABLE	notice	(	--11.提醒通知Table	推薦活動, 文章, 好友申請, 活動申請, 留言, 活動變更, 
memberid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--會員ID
content nvarchar(max) not null,	
noticetime	datetime not null default getDate(),
activityid int references activity(id),--活動id
articleid int references article(id),  --文章id
attendid int references attend(id) on delete cascade, --參加名單id (申請, 成功, 失敗
inviteid int references invite(id) on delete cascade, --推薦活動id 
friendid int references friend(id) on delete cascade, --好友名單id (申請, 成功, 失敗
msgid int references msg(id) on delete cascade, --留言id
state int not null default 0,
constraint notic_chk check(
	((case when articleid is null then 0 else 1 end)+
		(case when attendid is null then 0 else 1 end)+
		(case when inviteid is null then 0 else 1 end)+
		(case when msgid is null then 0 else 1 end)+
		(case when friendid is null then 0 else 1 end)) between 0 and 1
	)
);
create nonclustered index notice_index on notice(memberid);	
GO

CREATE TABLE	trackactivity	(	-- -12.追蹤活動清單Table	
memberid int not null FOREIGN key references member(id),
activityid	Int	NOT NULL  FOREIGN KEY REFERENCES activity(id),
constraint trackact_uq unique (memberid, activityid)							
)	
GO

CREATE TABLE	trackmember	(	--8.追蹤會員名單Table	
memberid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--會員id	
fanid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--追蹤會員id
constraint track_uq unique (memberid, fanid)	
)	
GO

CREATE TABLE blacklist	(	---16黑名單	
memberid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id) ,	--會員id	
blackid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id), --黑名單會員id
constraint 	blacklist_uq unique (memberid, blackid)
)	
GO

CREATE TABLE	service	(	--9.客服資訊Table	
id	Int	PRIMARY KEY IDENTITY,	--客服資訊id	
memberid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--會員id	
asktime	datetime not null default getdate(),		--發問時間	
title	nvarchar(50) 	NOT NULL,	--問題名稱	
class	Int not null,		--問題類型	
content	nvarchar(max) 	NOT NULL	--問題描述	
)
GO

CREATE TABLE	activityclass	(	--10.活動類型Table(用於select	
classid	Int	not null,	--活動類型id	
activityid	Int	NOT NULL  FOREIGN KEY REFERENCES activity(id)	--活動id	
)	
GO

CREATE TABLE	picture	(	---13圖片table	
id	Int	PRIMARY KEY IDENTITY,	--圖片tableid	
picture	varbinary(max) 	NOT NULL,	--圖片
activityid int references activity(id),
articleid int references article(id) on delete cascade,
memberid int references member(id),
constraint picture_chk check( 
	(case when activityid is null then 0 else 1 end)+
	(case when articleid is null then 0 else 1 end)+
	(case when memberid is null then 0 else 1 end) = 1
	)	
)
GO 