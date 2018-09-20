package model.dao;

import java.util.List;

import model.MsgBean;

public interface MsgDAO {
	public abstract MsgBean selectById(Integer id);
	public abstract List<MsgBean> selectByActivity(Integer id);
	public abstract List<MsgBean> selectByArticle(Integer id);
	public abstract List<MsgBean> selectByActivity(Integer id, Integer first);
	public abstract List<MsgBean> selectByArticle(Integer id, Integer first);
	public abstract Boolean insert(MsgBean msgBean);
	public abstract Boolean delete(Integer id);
}
