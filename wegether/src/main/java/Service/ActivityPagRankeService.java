package Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.AttendBean;
import model.dao.AttendDAO;

@Service
public class ActivityPagRankeService {

	@Autowired
	private AttendDAO attendDAO;
	
	
  public AttendBean updateRank(int activityid,int memberid, int rank1, int rank2, int rank3) {
	   AttendBean temp = attendDAO.selectByActivityAndMember(activityid, memberid);
	  if(temp!=null) {
		  temp.setRank1(rank1);
		  temp.setRank2(rank2);
		  temp.setRank3(rank3);
		 attendDAO.update(temp);
	  }
	  return temp;
  }
  
  public AttendBean selectRank(int activityid,int memberid) {
	  return attendDAO.selectByActivityAndMember(activityid, memberid);
 }
	
}
