package model.dao;

import java.util.List;

import org.hibernate.sql.Select;

import model.PictureBean;

public interface PictureDAO {
	public abstract List<PictureBean> selectByMember(Integer memberid);
	public abstract List<PictureBean> selectByActivity(Integer actid);
	public abstract List<PictureBean> selectByArticle(Integer articleid);
	public abstract PictureBean Select(int id);
	public abstract PictureBean insert(PictureBean picturebean);

	public abstract PictureBean update(PictureBean picturebean);

	public abstract boolean delete(int id);
	
}
