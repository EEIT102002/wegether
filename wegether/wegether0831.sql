use test

drop database wegether

CREATE DATABASE wegether
GO

USE wegether
GO

create sequence member_sq
start with 1
increment by 1


CREATE TABLE  member	(	--5.會員Table	
id	Int	PRIMARY KEY default next value for member_sq,	--會員id	
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
favorite	Int not null default 0 check(favorite between 0 and 2),		--喜好活動類型	
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

create sequence activity_sq
start with 1
increment by 1


CREATE TABLE	activity	(	--1.活動資訊Table	
id	Int	PRIMARY KEY default next value for activity_sq,	--活動id	
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

create sequence article_sq
start with 1
increment by 1


CREATE TABLE	article	(	--4.PO文資訊Table	
id	Int	PRIMARY KEY default next value for article_sq,	--PO文id	
memberid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--會員id	
activityid	Int	FOREIGN KEY REFERENCES activity(id), 	--活動id	
content	nvarchar(max) 	NOT NULL,	--PO內容	
createtime	datetime not null default getdate()		--PO時間		
)	
GO

create sequence msg_sq
start with 1
increment by 1


CREATE TABLE	msg	(	--2.活動.PO文留言Table	
id	Int	PRIMARY KEY default next value for msg_sq,	--留言id	
activityid	Int	FOREIGN KEY REFERENCES activity(id),	--活動id	
articleid	Int	FOREIGN KEY REFERENCES article(id) on delete cascade,	--PO文id	
memberid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--會員id	
msgtime	datetime not null default getdate(),		--留言時間	
content	nvarchar(200) 	NOT NULL,	--留言內容	
state int default 0,
constraint msg_chk check(
	(case when activityid is null then 0 else 1 end)+
	(case when articleid is null then 0 else 1  end)=1
	)
)	
GO

create sequence attend_sq
start with 1
increment by 1


CREATE TABLE	attend(	--3.參加名單Table	
id	Int	PRIMARY KEY default next value for attend_sq,	--參加名單id	
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

create sequence friend_sq
start with 1
increment by 1


CREATE TABLE friend	(	--6.好友名單Table	
id	Int	PRIMARY KEY default next value for friend_sq,	--好友名單id	
memberid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--會員id	
memberidf	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--好友id	
state	Int	NOT NULL check(state between 0 and 2),	--狀態	 0:申請中 1:好友 2:拒絕
constraint friend_uq unique (memberid, memberidf)
)
GO

create sequence invite_sq
start with 1
increment by 1



CREATE TABLE	invite	(	--7.推薦活動	
id	Int	PRIMARY KEY default next value for invite_sq,	--推薦活動id	
memberid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--主邀會員id	
invitedid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--被邀會員id	
activityid	Int	NOT NULL  FOREIGN KEY REFERENCES activity(id), --活動id	
constraint inv_uq unique (memberid, invitedid, activityid),
constraint inv_id_check check (memberid <> invitedid)
)				
GO

create sequence notice_sq
start with 1
increment by 1


create TABLE	notice	(	--11.提醒通知Table	推薦活動, 文章, 好友申請, 活動申請, 留言, 活動變更, 
id int primary key default next value for notice_sq,
memberid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--會員ID
content nvarchar(max) not null,	
noticetime	datetime not null default getDate(),
activityid int references activity(id),--活動id
articleid int references article(id) on delete cascade,  --文章id
attendid int references attend(id) on delete cascade, --參加名單id (申請, 成功, 失敗
inviteid int references invite(id) on delete cascade, --推薦活動id 
friendid int references friend(id) on delete cascade, --好友名單id (申請, 成功, 失敗
msgcount int, --留言次數
msgcountu int, --新留言次數
ntype int,
state int not null default 0,
constraint notic_chk check(
	((case when articleid is null then 0 else 1 end)+
		(case when attendid is null then 0 else 1 end)+
		(case when inviteid is null then 0 else 1 end)+
		(case when activityid is null then 0 else 1 end)+
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

create sequence service_sq
start with 1
increment by 1



CREATE TABLE	service	(	--9.客服資訊Table	
id	Int	PRIMARY KEY default next value for service_sq,	--客服資訊id	
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

create sequence picture_sq
start with 1
increment by 1


CREATE TABLE	picture	(	---13圖片table	
id	Int	PRIMARY KEY default next value for picture_sq,	--圖片tableid	
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

create trigger setting_member_insert on member
for insert
as
begin
	insert into setting (memberid)
	select id
	from inserted
end
go 

create trigger check_friend_insert on friend --檢查有無重複的好友申請
instead of insert
as
begin
	if not exists (select * from friend f join inserted i on f.memberid = i.memberidf and f.memberidf = i.memberid)
	begin
		insert into friend (memberid, memberidf , state, id) 
		select memberid, memberidf, i.state,i.id
		from inserted i
		where i.state <> 0 or (select addfriend from setting where memberid = memberidf) = 0
	end
end
go

create function notice_name
(@memberid int)
returns nvarchar(max)
as
begin
	return 
	(select nickname
		from member 
		where id = @memberid
		)
end
go

create trigger notice_friend_insert on friend --提醒新的好友申請
after insert
as
begin 
	declare @f_id int, @f_memberid int, @f_friendid int , @f_state int, @content nvarchar(max)
	select @f_id = id, @f_memberid = memberid, @f_friendid = memberidf , @f_state = state
	from inserted
	if(@f_state = 0)
	begin
		set @content = dbo.notice_name(@f_memberid) --好友申請1
		insert into notice (memberid, content, friendid, ntype) values (@f_friendid, @content, @f_id, 1)
	end
end
go

create trigger notice_friend_update on friend -- 提醒申請方同意好友申請
after update
as
begin
if((select state from deleted) = 0 ) --刪除申請提醒
begin
	if ((select state from inserted) = 1)--同意申請 friend table 新增雙方好友，notice table新增同意提醒
	begin 
		update friend
		set state = i.state
		from inserted i
		where friend.memberidf = i.memberid and friend.memberid = i.memberidf
		IF @@rowcount=0
		begin
			insert into friend (memberid, memberidf , state) 
			select memberidf , memberid, 1
			from inserted
		end

		declare @content nvarchar(max)
		set @content = dbo.notice_name((select memberidf from inserted)) --邀請接受2

		insert into notice (memberid, content, friendid, ntype)
		select memberid , @content , id , 2
		from inserted

		delete notice 
		from inserted i
		where notice.memberid = i.memberidf and notice.friendid = i.id
	end
	else if ((select state from inserted) = 2)
	begin
		delete notice
		from inserted i
		where notice.friendid = i.id
	end
end
end
go

create function notice_name_title
(@memberid int, @activityid int)
returns nvarchar(max)
as
begin
	return dbo.notice_name(@memberid)
		+','+(select title from activity where id = @activityid)
end
go

create trigger check_invite_insert on invite -- check 被推薦人設置
instead of insert
as
begin
	insert into invite (memberid, invitedid, activityid, id)
	select i.memberid, i.invitedid, i.activityid, i.id
	from inserted i
	join setting s
	on i.invitedid = s.memberid
	where s.invite = 0
end
go


create trigger notice_invite_insert on invite --invite table 新增時 notice table新增提醒
for insert
as
begin
	insert into notice (memberid, inviteid,ntype, content)
	select invitedid , id, 3,
	dbo.notice_name_title(i.memberid, i.activityid) --推薦你參加3
	from inserted i
end
go

create function notice_name_titlestr
(@memberid int, @title nvarchar(max))
returns nvarchar(max)
as
begin
	return dbo.notice_name(@memberid)
		+','+@title
end
go

create trigger notice_attend_insert on attend  --attend table 新增時 notice table 新增提醒
for insert
as
begin
declare @hostid int, @title nvarchar(40)
if (select state from inserted ) = 0
begin
	select  @hostid= hostid , @title = title
	from activity
	where id = (select activityid from inserted)

	insert into notice(memberid, attendid, noticetime, content, ntype)
	select @hostid , id, createtime, 
		dbo.notice_name_titlestr(i.memberid, @title),4  --申請參加 4
	from inserted i
end
else if (select state from inserted) = 3
begin 
	select  @hostid= hostid , @title = title
	from activity
	where id = (select activityid from inserted)	

	insert into notice(memberid, attendid, noticetime, content, ntype)
	select @hostid , id, createtime,
		dbo.notice_name_titlestr(@hostid, @title),5 --邀請你參加 5
	from inserted
end
end
go

create trigger notice_attend_update on attend --attend table 修改時 notice table 新增申請結果，刪除原申請提醒
for update
as
begin

select u.id, memberid, dstate, istate, act.hostid , act.title
into #uattend
from (
	select d.id, d.memberid,d.state as dstate, i.state as istate, d.activityid
	from deleted d
	join inserted i
	on (d.state = 0 or d.state = 3) and (i.state = 1 or i.state = 2)
	)u
join activity act
on u.activityid = act.id

merge notice
using #uattend a
on notice.attendid = a.id
when matched then 
	delete;

insert into  notice  (memberid , attendid , ntype, content)
select  memberid, id ,
	(case istate 
		when 1 then 6 --報名成功6
		when 2 then 7 --報名失敗7
		end),title
from #uattend
where dstate = 0

insert into  notice  (memberid , attendid, ntype, content)
select  hostid, id , 
	(case istate 
		when 1 then 8 --接受邀請 8
		when 2 then 9  --拒絕邀請 9
		end 
		), 
	dbo.notice_name(memberid)
from #uattend
where dstate = 3
end
go

create trigger notice_article_insert on article --article table 新增時 在notice table新增提醒(好友與追蹤
for insert
as
begin
	declare @actorname nvarchar(max)
	set @actorname = dbo.notice_name((select memberid from inserted)) --有新的文章10

	insert into notice (memberid, articleid , content, noticetime,ntype)
	(select memberidf, i.id, @actorname, i.createtime,10
	from friend
	join inserted i
	on state = 1 and friend.memberid = i.memberid 
	and (select friendarticle from setting where memberid = memberidf) = 0)
	union
	(select fanid, i.id, @actorname, i.createtime,10
	from trackmember t
	join inserted i 
	on t.memberid = i.memberid)
end
go

create trigger notice_activity_insert on activity --activity table 新增 notice table 新增提醒到好友與追蹤
for insert
as
begin
	declare @hostname nvarchar(50)
	set @hostname = dbo.notice_name_titlestr(
		(select hostid from inserted), (select title from inserted))  -- 發起新活動 11

	insert into notice(memberid,content,noticetime , activityid, ntype)
	(select memberidf, @hostname, i.createtime ,i.id, 11
	from friend f
	join inserted i
	on f.state = 1 and f.memberid = i.hostid
	and (select friendactivity from setting where memberid = f.memberidf) = 0)
	union
	(select fanid, @hostname, i.createtime, i.id, 11
	from trackmember t
	join inserted i
	on t.memberid = i.hostid)

end
go

create trigger notice_activity_update on activity --activity table更新 活動取消 notice table 刪除相關提醒 新增提醒到活動參加者
for update
as
begin
if (select count(id) from inserted) =1
begin
	declare @date datetime  = getDate(), @title nvarchar(50), @id int
	select @title = title , @id=id
	from inserted

	if (select state from deleted) = 0 
	and (select state from inserted) = 2
	begin
		delete notice 
		where activityid = @id 
		or (attendid is not null and attendid in (select id from attend where activityid = @id))

		insert into notice (memberid, content , noticetime, ntype)
		select memberid, @title, @date,12  --活動取消12
		from attend
		where activityid = @id and state = 1

		delete attend
		from inserted i 
		where attend.activityid = i.id
	end
	else 
	begin
		merge notice
		using (
			select memberid, activityid 
			from attend 
			where activityid = @id and state =1 
			and (select activitychange from setting where memberid = attend.memberid) = 0
			) a
		on notice.memberid = a.memberid and notice.activityid = a.activityid and notice.ntype = 13
		when matched then
			update
			set content = @title,  --活動變更13
				noticetime = @date,
				state = 0
		when not matched then
			insert (memberid, activityid, content, ntype)
			values(a.memberid, a.activityid, @title, 13);
	end
end
end
go

create trigger member_notice_insert on notice --notice table 新增 member talbe notices +1
for insert
as
begin
	update member
	set notices+=1
	where id in (select memberid from inserted)
end
go

create trigger member_notice_delete on notice -- notice table 刪除  如果state =0 , member talbe notices -1
for delete
as
begin
	update member
	set notices -= c.notice
	from (
		select memberid, count(memberid) as notice
		from deleted
		where state = 0
		group by memberid
		) c
	where member.id = c.memberid
end
go

create trigger member_notice_update on notice -- notice table 更新  state 0->1 , 1->0 : member table notices -1,+1
for update
as
begin
	update member
	set notices =(
		case when c.state = 1  then notices + c.notice
		when c.state = 0 and notices > 0 then notices -	c.notice end)		
	from (
		select d.memberid, count(d.memberid) as notice, d.state
		from deleted d 
		join inserted i 
		on d.id = i.id and d.state <> i.state
		group by d.memberid , d.state
		) c
	where member.id = c.memberid
end
go

create trigger msg_notice_update on notice --notice table state 修改為1 相關 msg table state修改為1
for update
as
begin
	update msg
	set state = 1
	from deleted d
	join inserted i 
	on d.id = i.id and d.msgcountu >0 and i.msgcountu =0 and d.state = 0 and i.state =1
	where msg.activityid = d.activityid or msg.articleid = d.articleid and msg.state = 0
end
go

create function notice_title
(@activityid int)
returns nvarchar(max)
as
begin
	return (select title from activity where id = @activityid)
end
go

create trigger notice_msg_insert on msg --msg table新增　notice table 新增提醒
for insert
as
begin
	declare @activityid int , @articleid int, @date datetime, @content nvarchar(100)
	select @activityid = activityid, @articleid = articleid ,@date = msgtime
	from inserted
	if @activityid is not null and (select activitymsg from setting where memberid = (select hostid from activity where id = @activityid)) =0
	begin
		merge notice n
		using (select * from activity where id = @activityid) a
		on n.memberid = a.hostid and n.activityid =  a.id
		when matched then 
			update
			set n.noticetime = @date, 
				n.msgcount += 1,
				n.msgcountu +=1,
				n.state = 0
		when not matched then
			insert (memberid, activityid, content , noticetime, msgcount, msgcountu, ntype)
			values (a.hostid, a.id, a.title, @date, 1, 1, 14); --活動有新的留言14
	end
	else if @articleid is not null and (select articlemsg from setting where memberid = (select memberid from article where id = @articleid)) = 0
	begin
		merge notice n
		using (select * from article where id = @articleid) a
		on n.memberid = a.memberid and n.articleid = a.id
		when matched then 
			update
			set n.noticetime = @date,
				n.msgcount += 1,
				n.msgcountu +=1,
				n.state = 0
		when not matched then 
			insert (memberid, articleid, noticetime, msgcount, msgcountu,content, ntype)
			values (a.memberid, a.id, @date,1,1, 
				dbo.notice_title(a.activityid), 15); --文章有新的留言15
	end
end
go

create trigger notice_msg_delete on msg -- msg table 刪除 notice.msgcount-msg.state為0的數量 更新後msg.state=0>0 notice.state =0 or 1
for delete
as
begin
	merge notice n 
	using (
		select articleid, activityid,sum(case state when 0 then 1 else 0 end) as msgcountu , count(state) as msgcount,
		(case when articleid is null then (select hostid from activity where id = activityid )
		when activityid  is null then (select memberid from article where id = articleid) end) as memberid
		from deleted
		group by articleid, activityid
		)d
	on n.memberid = d.memberid and ( n.articleid = d.articleid or n.activityid = d.activityid)
	when matched and n.msgcount-d.msgcount <= 0 then
		delete
	when matched then 
		update 
		set n.msgcount -= d.msgcount,
			n.state = (case when n.msgcountu-d.msgcountu > 0 then 0 else 1 end),
			n.msgcountu -= d.msgcountu,
			n.noticetime = (
				select max(msgtime)
				from msg 
				where activityid = n.activityid or articleid = n.articleid
				group by activityid , articleid);
end
go

create trigger notice_member_update on member
for update
as
begin
	EXEC('DISABLE TRIGGER member_notice_update ON notice')
	update notice
	set state = 1
		, msgcountu = (case when msgcount is not null then 0 else null end)
	from deleted d
	join inserted i
	on d.id = i.id and d.notices > 0 and i.notices = 0
	where notice.memberid = d.id 
	EXEC('ENABLE TRIGGER member_notice_update ON notice')
end
go
