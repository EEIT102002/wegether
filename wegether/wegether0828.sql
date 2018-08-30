drop database wegether

CREATE DATABASE wegether
GO

USE wegether
GO

CREATE TABLE  member	(	--5.�|��Table	
id	Int	PRIMARY KEY IDENTITY,	--�|��id	
account	varchar(50) UNIQUE NOT NULL,	--�b��	
pwd	varbinary(50) NOT NULL,	--�K�X
photo	varbinary(max) , --�j�Y��,	
name	nvarchar(50)	NOT NULL,	--�m�W	
nickname	nvarchar(50) ,	--�ʺ�	
birthday	date not null,		--�ͤ�	
sex	int  check(sex between 0 and 1)	,	--�ʧO	
job	nvarchar(50), 		--¾�~	
city	int, 		--����	
addr	nvarchar(50), 		--�ԲӦa�}	
tel	char(20), 		--�q��	
content	nvarchar(max), 		--�ۧڤ���	
favorite	varchar(max) 	,	--�ߦn��������	
signupdate	datetime not null default getdate(),		--���U���		
rank1	decimal(2,1) check(rank1 between 0 and 5),		--���ʺ��N��	
rank2	decimal(2,1) check(rank2 between 0 and 5),		--�ƫe���q�w��	
rank3	decimal(2,1) check(rank3 between 0 and 5),		--�ɶ��a�I���	
fans	Int not null default 0,		--�l�ܼ�	
notices	Int not null default 0,		--����	
state	Int	not null default 0 check(state between 0 and 1)	--�{�Ҫ��A
,fbid nchar(100)
,googleid nchar(100)
)	
GO

CREATE TABLE	setting	(	---14�|���ӤH�]�w	
memberid Int primary key REFERENCES member(id),	--�|��id	
birthday Int not null default 0 check(birthday between 0 and 2),		--�ͤ�	
sex	Int not null default 0 check(sex between 0 and 2),		--�ʧO	
job	Int not null default 0 check(job between 0 and 2),		--¾�~	
city Int not null default 0 check(city between 0 and 2),		--����		
addr Int not null default 0 check(addr between 0 and 2),		--�ԲӦa�}	
tel	Int not null default 0 check(tel between 0 and 2),		--�q��	
content	Int not null default 0 check(content between 0 and 2),		--�ۧڤ���	
favorite	Int not null default 0 check(class between 0 and 2),		--�ߦn��������	
showarticle	Int not null default 0 check(showarticle between 0 and 2),		--Po�����	
showhost	Int not null default 0 check(showhost between 0 and 2),		--�D�쬡�����	
showactivity	Int not null default 0 check(showactivity between 0 and 2),		--�ѥ[���ʬ���	
rankscore	Int not null default 0 check(rankscore between 0 and 2),		--����	
invite	Int not null default 0 check(invite between 0 and 1),		--�����ܽ�		
addfriend	Int not null default 0 check(addfriend between 0 and 1),	--�[�n��
activitymsg Int not null default 0 check(activitymsg between 0 and 1), --���ʯd���q��
articlemsg Int not null default 0 check(articlemsg between 0 and 1), --�峹�d���q��
activitychange Int not null default 0 check(activitychange between 0 and 1), --�����ܧ�q��
friendarticle Int not null default 0 check(friendarticle between 0 and 1), --�n�͵o��q��
friendactivity Int not null default 0 check(friendactivity between 0 and 1),	--�n�ͬ��ʳq��	
)
GO

CREATE TABLE	activity	(	--1.���ʸ�TTable	
id	Int	PRIMARY KEY IDENTITY,	--����id	
hostid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--�D��Hid	
createtime	datetime not null default getdate(),		--�Ыخɶ�
title	nvarchar(50) 	NOT NULL,	--���ʦW��	
city	int	,	--����	
addr	nvarchar(100) ,	--�ԲӦa�}	
picture	varbinary(max),	--�����Y��
actbegin	datetime	NOT NULL,	--���ʶ}�l�ɶ�	
actend	datetime,		--���ʵ����ɶ�	
dateline	datetime NOT NULL ,		--���W�I����	
classtype	varchar(max) ,	--�������� 
content	nvarchar(max) 	NOT NULL,	--���ʴy�z	
numberlimit	Int ,		--���ʤH�ƤW��			
feed	Int default 0,		--���ʶO��	
state	Int	NOT NULL default 0 check(state between 0 and 2) ,	--���ʪ��A(0 ���ʶi�� 1 ���ʵ��� 2���ʨ���
rank1	decimal(2,1) check(rank1 between 0 and 5),		--���ʺ��N�ץ���	
rank2	decimal(2,1) check(rank2 between 0 and 5),		--�ƫe���q�w�ƥ���	
rank3	decimal(2,1) check(rank3 between 0 and 5),		--�ɶ��a�I��ܥ���	
judges	Int default 0,		--�����H��	
form	nvarchar(max),		--���W���	
click	Int not null default 0		--�I�\�v	
)	

CREATE TABLE	article	(	--4.PO���TTable	
id	Int	PRIMARY KEY IDENTITY,	--PO��id	
memberid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--�|��id	
activityid	Int	FOREIGN KEY REFERENCES activity(id), 	--����id	
content	nvarchar(max) 	NOT NULL,	--PO���e	
createtime	datetime not null default getdate()		--PO�ɶ�		
)	
GO

CREATE TABLE	msg	(	--2.����.PO��d��Table	
id	Int	PRIMARY KEY IDENTITY,	--�d��id	
activityid	Int	FOREIGN KEY REFERENCES activity(id),	--����id	
articleid	Int	FOREIGN KEY REFERENCES article(id),	--PO��id	
memberid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--�|��id	
msgtime	datetime not null default getdate(),		--�d���ɶ�	
content	nvarchar(200) 	NOT NULL,	--�d�����e	
constraint msg_chk check(
	(case when activityid is null then 0 else 1 end)+
	(case when articleid is null then 0 else 1  end)=1
	)
)	
GO

CREATE TABLE	attend(	--3.�ѥ[�W��Table	
id	Int	PRIMARY KEY IDENTITY,	--�ѥ[�W��id	
activityid	Int	NOT NULL  FOREIGN KEY REFERENCES activity(id),	--����id	
memberid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--�|��id	
createtime	datetime not null default getdate(),		--���W�ɶ�	
form	nvarchar(max),		--���W���
rank1	Int check(rank1 between 0 and 5),		--���ʺ��N��	
rank2	Int check(rank2 between 0 and 5),		--�ƫe���q�w��	
rank3	Int check(rank3 between 0 and 5),		--�ɶ��a�I���	
state	Int	NOT NULL check(state between 0 and 3),	--���W���A 0:���W�� 1:���W���\ 2:���W���� 3:���ʥD��H�ܽ�
constraint act_member_uq unique (activityid,memberid)
)	
GO

CREATE TABLE	friend	(	--6.�n�ͦW��Table	
id	Int	PRIMARY KEY IDENTITY,	--�n�ͦW��id	
memberid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--�|��id	
memberidf	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--�n��id	
state	Int	NOT NULL check(state between 0 and 2),	--���A	 0:�ӽФ� 1:�n�� 2:�ڵ�
constraint friend_uq unique (memberid, memberidf)
)
GO

CREATE TABLE	invite	(	--7.���ˬ���	
id	Int	PRIMARY KEY IDENTITY,	--���ˬ���id	
memberid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--�D�ܷ|��id	
invitedid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--�Q�ܷ|��id	
activityid	Int	NOT NULL  FOREIGN KEY REFERENCES activity(id), --����id	
constraint inv_uq unique (memberid, invitedid, activityid)
)				
GO

CREATE TABLE	notice	(	--11.�����q��Table	���ˬ���, �峹, �n�ͥӽ�, ���ʥӽ�, �d��, �����ܧ�, 
memberid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--�|��ID
content nvarchar(max) not null,	
noticetime	datetime not null default getDate(),
activityid int references activity(id),--����id
articleid int references article(id),  --�峹id
attendid int references attend(id) on delete cascade, --�ѥ[�W��id (�ӽ�, ���\, ����
inviteid int references invite(id) on delete cascade, --���ˬ���id 
friendid int references friend(id) on delete cascade, --�n�ͦW��id (�ӽ�, ���\, ����
msgid int references msg(id) on delete cascade, --�d��id
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

CREATE TABLE	trackactivity	(	-- -12.�l�ܬ��ʲM��Table	
memberid int not null FOREIGN key references member(id),
activityid	Int	NOT NULL  FOREIGN KEY REFERENCES activity(id),
constraint trackact_uq unique (memberid, activityid)							
)	
GO

CREATE TABLE	trackmember	(	--8.�l�ܷ|���W��Table	
memberid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--�|��id	
fanid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--�l�ܷ|��id
constraint track_uq unique (memberid, fanid)	
)	
GO

CREATE TABLE blacklist	(	---16�¦W��	
memberid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id) ,	--�|��id	
blackid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id), --�¦W��|��id
constraint 	blacklist_uq unique (memberid, blackid)
)	
GO

CREATE TABLE	service	(	--9.�ȪA��TTable	
id	Int	PRIMARY KEY IDENTITY,	--�ȪA��Tid	
memberid	Int	NOT NULL  FOREIGN KEY REFERENCES member(id),	--�|��id	
asktime	datetime not null default getdate(),		--�o�ݮɶ�	
title	nvarchar(50) 	NOT NULL,	--���D�W��	
class	Int not null,		--���D����	
content	nvarchar(max) 	NOT NULL	--���D�y�z	
)
GO

CREATE TABLE	activityclass	(	--10.��������Table(�Ω�select	
classid	Int	not null,	--��������id	
activityid	Int	NOT NULL  FOREIGN KEY REFERENCES activity(id)	--����id	
)	
GO

CREATE TABLE	picture	(	---13�Ϥ�table	
id	Int	PRIMARY KEY IDENTITY,	--�Ϥ�tableid	
picture	varbinary(max) 	NOT NULL,	--�Ϥ�
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