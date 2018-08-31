package model.dao;

import model.SettingBean;

public interface SettingDAO {
	public abstract SettingBean select(int memberid);
	public abstract SettingBean update(SettingBean settingBean);
}
