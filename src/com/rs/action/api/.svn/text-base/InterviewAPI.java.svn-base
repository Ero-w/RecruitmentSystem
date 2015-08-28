package com.rs.action.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.print.CancelablePrintJob;

import org.apache.struts2.ServletActionContext;
import org.hibernate.sql.Insert;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.rs.dao.impl.ApplyDAO;
import com.rs.dao.impl.DepartmentDAO;
import com.rs.dao.impl.InterviewDAO;
import com.rs.dao.impl.PositionDAO;
import com.rs.dao.impl.StaffDAO;
import com.rs.model.Apply;
import com.rs.model.Interview;
import com.rs.model.Position;
import com.rs.model.Staff;
import com.rs.service.IAccountService;
import com.rs.service.IInterviewService;
import com.rs.service.impl.InterviewService;
import java.sql.ResultSet;

public class InterviewAPI extends ActionSupport{

	private IInterviewService interviewService;	
	private List<Interview> interviewList = new ArrayList<Interview>();
	private String sname;
	private StaffDAO staffDAO;
	private ApplyDAO applyDAO;
	private InterviewDAO interviewDAO;
	private PositionDAO positionDAO;
	private DepartmentDAO departmentDAO;

	public DepartmentDAO getDepartmentDAO() {
		return departmentDAO;
	}
	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}
	public PositionDAO getPositionDAO() {
		return positionDAO;
	}
	public void setPositionDAO(PositionDAO positionDAO) {
		this.positionDAO = positionDAO;
	}
	public List<Interview> getInterviewList() {
		return interviewList;
	}
	public void setInterviewList(List<Interview> interviewList) {
		this.interviewList = interviewList;
	}
	public InterviewDAO getInterviewDAO() {
		return interviewDAO;
	}
	public void setInterviewDAO(InterviewDAO interviewDAO) {
		this.interviewDAO = interviewDAO;
	}
	public StaffDAO getStaffDAO() {
		return staffDAO;
	}
	public ApplyDAO getApplyDAO() {
		return applyDAO;
	}
	public void setApplyDAO(ApplyDAO applyDAO) {
		this.applyDAO = applyDAO;
	}
	public void setStaffDAO(StaffDAO staffDAO) {
		this.staffDAO = staffDAO;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public IInterviewService getInterviewService() {
		return interviewService;
	}
	public void setInterviewService(IInterviewService interviewService) {
		this.interviewService = interviewService;
	}

	private JSONObject jsonObject;	
	//json接收前台传过来的字符串，在setJson()时解析成jsonObject;
	private String json;
	//map为返回给前台的json
	private Map<String, Object> map=new HashMap<String, Object>();
	
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
		try {//转换json对象
			jsonObject=JSONObject.parseObject(json);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject=null;
		}
	}
	
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}	
	
	//面试管理和评分管理中显示所有面试人信息
	public String showAllInterview(){		
		//Set set = staffDAO.findById(1).getInterviews();//默认面试官的Id为1	
		//System.out.println(ServletActionContext.getRequest().getSession().getAttribute("sid"));
//		int sid = (Integer) ServletActionContext.getRequest().getSession().getAttribute("sid");
		Set set = staffDAO.findById(1).getInterviews();	
		Iterator iterator = set.iterator();
		int i = 0;
		while(iterator.hasNext()){
			Interview interview = (Interview)iterator.next();			
			//过滤掉已经评分的
			String rank = interview.getRank();			
			if(!rank.equals("")){		
				continue;
			}
			//使用map传递数据给前台
			map.put(i+"", interview.getIid());				
			map.put(i+"sname", interview.getApply().getStaff().getSname());		
			map.put(i+"pname",interview.getApply().getPosition().getPname());		
			map.put(i+"time", interview.getTime());			
			i++;	
		}
		//显式设置map的长度
		map.put("length", i);		
		return SUCCESS;
	}	
	
	
	
	//面试官给出评分
	public String saveScore(){		
		Interview interview = interviewDAO.findById((Integer)jsonObject.get("iid"));	
		interview.setRank((String)jsonObject.get("rank"));
		interview.setEvaluate((String)jsonObject.get("evaluate"));	
		interview.setPass(jsonObject.getInteger("pass"));
		interviewDAO.attachDirty(interview);
		return SUCCESS;
	}
	//面试官通知面试时间
	public String saveTime(){		
		Interview interview = interviewDAO.findById(jsonObject.getInteger("iid"));		
		interview.setTime(jsonObject.getDate("time"));		
		interviewDAO.attachDirty(interview);
		return SUCCESS;
	}
	
	//删除面试人
	public String deleteInterview(){		
		Interview interview = interviewDAO.findById((Integer)jsonObject.get("iid"));		
		interviewDAO.delete(interview);
		return SUCCESS;
	}
	
	//添加面试人记录
	public String addInterview(){		
		//System.out.println(jsonObject.getInteger("apid"));
		Apply apply = applyDAO.findById(jsonObject.getInteger("apid"));
		//System.out.println(apply);
		//判断是否面试表已有该记录
		if(apply.getInterviews().isEmpty()){//如果没有该记录，则可添加
			Interview interview = new Interview();
			//interview.setStaff(staffDAO.findById(1));//暂时默认面试官为1号面试官,interviewerId
			//int sid = (Integer) ServletActionContext.getRequest().getSession().getAttribute("sid");
			interview.setStaff(staffDAO.findById(1));
			interview.setApply(apply);//通过关联映射，设置apid的值。			
			interview.setTime(jsonObject.getDate("date"));
			interview.setRemark(jsonObject.getString("remark"));
			//给ranK设置一个""空字符代替NULL值,方便操作
			interview.setRank("");
			interview.setPass(-1);
			interview.setEvaluate("");
			interviewDAO.save(interview);	
			interviewDAO.attachDirty(interview);
		}else{
			map.put("exist", 1);
		}
		
		return  SUCCESS;
		
	}
	//面试管理找到所有的面试信息
	public String findAllMessage(){		
		List<Apply> list = applyDAO.findAll();
		int i = 0;
		for(Apply apply:list){	
			//先判断Interview表中是否已经有该面试记录
			if(apply.getInterviews().isEmpty()){//如果没有该面试记录
				//System.out.println(781957);
				map.put(i+"apid", apply.getApid());
				map.put(i+"sname",apply.getStaff().getSname());
				map.put(i+"pname", apply.getPosition().getPname());
				i++;
			}				
		}
		map.put("length",i);
		return SUCCESS;
	}
	
	//面试中心找到求职中心中所有的岗位
	public String showAllPosition(){
		List<Position> list = positionDAO.findAll();		
		//Staff staff = staffDAO.findById(1);//暂时默认该登录的人为1	
		//int sid = (Integer) ServletActionContext.getRequest().getSession().getAttribute("sid");
		Staff staff = staffDAO.findById(1);
		int i = 0;
		for(Position position:list){
			//判断是否已插入到Apply表
			if(canInsert(staff, position.getPid())){//如果还没插入到Apply表中
				map.put(i+"isApply", false);				
			}else{				
				map.put(i+"isApply", true);
			}
			map.put(i+"", position.getPid());
			map.put(i+"pname", position.getPname());
			map.put(i+"dname",position.getDepartment().getDname());
			map.put(i+"amount", position.getCount());
			map.put(i+"requirement", position.getRequirement());
			map.put(i+"deadline",position.getDeadline());
			i++;
		
		}
		map.put("length", i);
		return SUCCESS;
	}
	//面试中心中申请岗位
	public String applyPosition(){
		Apply apply = new Apply();		
		Position position = positionDAO.findById(jsonObject.getInteger("pid"));
		//Staff staff = staffDAO.findById(1);//暂时默认该登录的人为1	
		//int sid = (Integer) ServletActionContext.getRequest().getSession().getAttribute("sid");
		Staff staff = staffDAO.findById(1);
		//判断是否可以插入到Apply表
		if(canInsert(staff, jsonObject.getInteger("pid"))){//如果没有重复记录，则可以插入			
			apply.setPosition(position);		
			apply.setStaff(staff);	
			applyDAO.save(apply);
			applyDAO.attachDirty(apply);//更新数据库
		}		
		return SUCCESS;
	}
	//判断是否可以插入Apply表（不可以插入当然也不可以在面试中心中申请）
	public boolean canInsert(Staff staff,Integer pid2){		
		//Apply表中是否有重复记录		
		Set set = staff.getApplies();
		boolean insert = true;//判断是否可以插入
		if(set.isEmpty()){//说明该面试人未面试任何岗位			
			insert = true;
		}else{
			Iterator iterator = set.iterator();
			while(iterator.hasNext()){
				Apply apply = (Apply)iterator.next();
				Position position = apply.getPosition();			
				//if(position.getPid() == position2.getPid())
				if(position.getPid().equals(pid2))
					insert = false;
			}
		}		
		return insert;		
	}	
	//面试中心：显示所有的面试记录
	public String showAllRecord(){
		//Staff staff = staffDAO.findById(1);//默认面试人编号是1
		//int sid = (Integer) ServletActionContext.getRequest().getSession().getAttribute("sid");
		Staff staff = staffDAO.findById(1);
		//先找到Apply表
    	Set set = new HashSet(0);	
    	set = staff.getApplies();	
    	Iterator j = set.iterator();	
    	int i = 0; 
    	while(j.hasNext()){	    	
    		Apply apply = (Apply)j.next();	    		
    		//利用 Apply表找到Interview表
    		Set set2 = new HashSet(0);	    		
    		set2 = apply.getInterviews();
    		//如果面试官还没有安排面试时间
    		if(set2.size() == 0){
    			//使用map传递数据给前台			
    			map.put(i+"", apply.getApid());
    			map.put(i+"pname",apply.getPosition().getPname());
    			map.put(i+"time","等待分配");
    			map.put(i+"pass","无");
				map.put(i+"state", "无");
    			i++;
    		}
    		Iterator k = set2.iterator();    		
    		while(k.hasNext()){
    			Interview interview = (Interview) k.next();	
    			//过滤掉未进行评分管理的
    			//String rank = interview.getRank();    			
    			//if(rank.equals("")){    				
    			//	continue;
    			//}
    			interviewList.add(interview);	    			
    		}
    	}       	
	    for(Interview interview:interviewList){	    
			//使用map传递数据给前台			
			//map.put(i+"", interview.getIid());		
	    	map.put(i+"", interview.getApply().getApid());
			map.put(i+"pname",interview.getApply().getPosition().getPname());
			map.put(i+"time", interview.getTime());
			//获得当前系统时间,与面试时间比较，判断面试状态是否已完成
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd ");
			String systemTime = format.format(date);	
			String time = interview.getTime().toString().substring(0,10);
			//System.out.println(interview.getTime().toString().substring(0,10));
			//System.out.println(time.compareTo(interview.getTime().toString().substring(0,10)));			
			if(interview.getPass() > 0){
				map.put(i+"pass","已录取");
				map.put(i+"state", "已完成");
			}else if(interview.getPass() == 0){
				map.put(i+"pass","未录取");
				map.put(i+"state","完成");
			}else{
				map.put(i+"pass","未录取");
				if(systemTime.compareTo(time) > 1){//当前系统时间,与面试时间比较
					map.put(i+"state", "已过期");
				}else{
					map.put(i+"state", "进行中......");
				}
			}
		
			//map.put(i+"pass",interview.getPass());
			i++;
	    }
	    //显式设置map的长度
	    map.put("length", i);	  
		return SUCCESS;
	}
	
	/*//评分管理中查找特定的面试人信息
	public String findInterview(){			
		List<Staff> list = staffDAO.findBySname(sname);		
		int i = 0;				
	    for(Staff staff:list){	    	
	    	//先找到Apply表
	    	Set set = new HashSet(0);	
	    	set = staff.getApplies();	
	    	Iterator j = set.iterator();	    	
	    	while(j.hasNext()){	    	
	    		Apply apply = (Apply)j.next();	    		
	    		//利用 Apply表找到Interview表
	    		Set set2 = new HashSet(0);	    		
	    		set2 = apply.getInterviews();
	    		Iterator k = set2.iterator();
	    		while(k.hasNext()){
	    			Interview interview = (Interview) k.next();
	    			//过滤掉已经评分的
	    			String rank = interview.getRank();
	    			if(!rank.equals("")){
	    				continue;
	    			}
	    			if(interview.getStaff().getSid().equals(1))//默认面试官编号为1
	    				interviewList.add(interview);	    			
	    		}
	    	}
	    }	 
		i = 0;		  
	    for(Interview interview:interviewList){	    	
			//使用map传递数据给前台			
			map.put(i+"", interview.getIid());		
			map.put(i+"sname", interview.getApply().getStaff().getSname());
			map.put(i+"pname",interview.getApply().getPosition().getPname());
			map.put(i+"time", interview.getTime());			
			i++;
	    }
	    //显式设置map的长度
	    map.put("length", i);	  
		return SUCCESS;
	}	*/
	
}
