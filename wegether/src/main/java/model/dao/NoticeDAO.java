package model.dao;

import java.util.List;

import model.NoticeBean;

public interface NoticeDAO {
	public abstract List<NoticeBean> selectByMemberId(Integer id, Integer first);
}
