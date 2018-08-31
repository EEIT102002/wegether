package model.dao;

import model.ArticleBean;

public interface ArticleDao {
	public abstract ArticleBean select(int id);

	public abstract ArticleBean insert(ArticleBean articleBean);

	public abstract boolean update(int id, String content);

	public abstract boolean delete(int id);
}