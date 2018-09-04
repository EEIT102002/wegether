package model.dao;

import model.MemberBean;

public interface MemberDAO {
	public abstract MemberBean select(int id);

	public abstract MemberBean insert(MemberBean memberBean);

	public abstract boolean update(MemberBean memberBean);
}
