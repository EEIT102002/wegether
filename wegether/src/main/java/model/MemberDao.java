package model;

public interface MemberDao {
	public abstract MemberBean select(int id);

	public abstract MemberBean insert(MemberBean memberBean);

	public abstract boolean update(MemberBean memberBean);
}
