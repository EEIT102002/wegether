package model;

public interface MsgDao {
	public abstract MsgBean select(int id);

	public abstract boolean update(int id, String content);

	public abstract boolean delete(int id);
}