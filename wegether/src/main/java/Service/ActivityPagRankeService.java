package Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.AttendBean;
import model.dao.AttendDAO;

@Service
public class ActivityPagRankeService {

	@Autowired
	private AttendDAO attendDAO;
	
	
  public AttendBean updateRank(int activityid,int memberid) {
	   AttendBean temp = attendDAO.selectByActivityAndMember(activityid, memberid);
	  if(temp!=null) {
		  temp.setRank1(3);
		  temp.setRank2(3);
		  temp.setRank3(3);
		  attendDAO.update(temp);
	  }
	  return temp;
  }
  
  public void selectRank(int activityid,int memberid) {
	   AttendBean temp = attendDAO.selectByActivityAndMember(activityid, memberid);
	  if(temp!=null) {
		  temp.getRank1();
		  temp.getRank2();
		  temp.getRank3();
		  AttendBean bean = attendDAO.update(temp);
	  }
 }
	
}
