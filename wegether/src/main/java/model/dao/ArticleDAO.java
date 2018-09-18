package model.dao;

import java.util.List;

import org.hibernate.query.Query;

import model.ActivityBean;
import model.ArticleBean;

public interface ArticleDAO {
	public abstract ArticleBean select(int id);

	public abstract ArticleBean insert(ArticleBean articleBean);

	public abstract boolean update(int id, String content);

	public abstract boolean delete(int id);

	public abstract List<Integer> selectAllForActid();
	
	public abstract List<ArticleBean> selectByActivityId(int activityId);

	public abstract int getArticleId(int memberid, int activityid);

}