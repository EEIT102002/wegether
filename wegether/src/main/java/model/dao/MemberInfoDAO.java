package model.dao;

import java.util.List;

import model.MemberBean;
import model.MemberInfoBean;

public interface MemberInfoDAO {
	public abstract MemberInfoBean select(int id);
	public abstract List<MemberInfoBean> selectByNickname(String nickname);
	public abstract List<MemberInfoBean> selectByNicknameForFriend(String nickname, Integer id);
	public abstract List<MemberInfoBean> selectByNicknameForTrack(String nickname,Integer id);
	public abstract List<MemberInfoBean> selectByNicknameForBlacklist(String nickname, Integer id);
}
