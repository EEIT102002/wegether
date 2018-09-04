package model.dao;

import java.util.List;

import model.MemberBean;

public interface MemberDAO {
	public abstract MemberBean select(int id);
	
	public abstract List<MemberBean> select();

	public abstract MemberBean insert(MemberBean memberBean);

	public abstract boolean update(MemberBean memberBean);
}
