package querylanguage;

import org.springframework.context.annotation.Bean;

public class Select {

	public static final String offset = " offset :offset_first row fetch next :offset_max rows only ";

	public static final String noticeByMember = "select {n.*} from Notice n where memberid = :id order by noticetime desc "
			+ offset;
	public static final String noticeByActivity = noticeSql("activityid");
	public static final String noticeByArticle = noticeSql("articleid");
	public static final String noticeByAttend = noticeSql("attendid");
	public static final String noticeByFriend = noticeSql("friendid");
	public static final String noticeByInvite = noticeSql("inviteid");
	public static final String msgByActivity = msgSql("activityid");
	public static final String msgByArticle = msgSql("activityid");
	public static final String msgByActivityOffset = addOffset(msgByActivity);
	public static final String msgByArticleOffset = addOffset(msgByArticle);
	public static final String friendByMemberState = addOffset(friendSql("memberid"));
	public static final String friendByMemberFState = addOffset(friendSql("memberidf"));
	public static final String memberByNickname = "from MemberBean where nickname like :nickname";
	
	public static final String memberInfoByNickname = "from MemberInfoBean where nickname like :nickname";
	
	public static final String memberByNicknameForFriend = 
			"select {m.*} "+
			"from (select * from member where nickname like :nickname) m "+
			"left join Friend f "+ 
			"on (f.memberid = :id and m.id = f.memberidf) or (f.memberidf = :id and m.id = f.memberid) " + 
	
			"where f.memberid is null and m.id <> :id";
	public static final String memberByNicknameForTrack = 
			"select {m.*} " + 
			"from (select n.* " + 
			"	from (select * from member where nickname like :nickname ) n " + 
			"	left join trackmember t " + 
			"	on t.fanid = :id and n.id = t.memberid " + 
			"	where t.memberid is null and n.id <> :id) m " + 
			"left join blacklist b " + 
			"on b.blackid = m.id and b.memberid = :id " + 
			"where b.memberid is null ";
	public static final String memberByNicknameForBlacklist =
			"select {m.*} " + 
			"from (select * from member where nickname like :nickname) m " + 
			"left join blacklist b " + 
			"on m.id = b.blackid and b.memberid = :id " + 
			"where b.memberid is null and m.id <> :id ";
	
	private static String addOffset(String sql) {
		return sql + offset;
	}

	private static String msgSql(String tabelid) {
		return "select {m.*} from Msg m where " + tabelid + " = :id order by msgtime desc ";
	}

	private static String friendSql(String tableid) {
		return "select {m.*} from Friend m WHERE " + tableid + " = :id AND STATE = :state order by state";
	}

	private static String noticeSql(String tableid) {
		return "from NoticeBean where " + tableid + " = :id and ntype = :ntype and state = 0";
	}

}
