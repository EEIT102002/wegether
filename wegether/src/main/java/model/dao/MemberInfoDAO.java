package model.dao;

import model.MemberInfoBean;

public interface MemberInfoDAO {
	public abstract MemberInfoBean select(int id);
}
