package com.missionsky.exceltool.model;

/**
 * @ClassName: AttendanceModel
 * @Description: TODO
 * @author Alex
 * @Date: 2017-3-1
 */
public class AttendanceModel {
	
		
		//中文名
		private String nameZH;
		
		//英文名
		private String nameEN;
		
		//病假
		private String sickLeaveHour;
		
		//事假
		private String casualLeaveHour;
		
		//年假
		private String annualLeaveHour;
		
		//补休
		private String compensatedLeaveHour;
		
		//其他
		private String otherHour;
		
		//备注
		private String remark;
		
		//剩余年假
		private String remainAnnualLeaveHour;
		
		//剩余加班
		private String remainOvertime;
		
		//说明
		private String explain;
		
		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("姓名：").append(nameZH).append("\n\r").append("病假：");
			
			if(null == sickLeaveHour){
				sb.append("0").append("\n\r").append("事假：");
			}else{
				sb.append(sickLeaveHour).append("\n\r").append("事假：");
			}
			
			if(null == casualLeaveHour){
				sb.append("0").append("\n\r").append("年假：");
			}else{
				sb.append(casualLeaveHour).append("\n\r").append("年假：");
			}
			
			if(null == annualLeaveHour){
				sb.append("0").append("\n\r").append("补休：");
			}else{
				sb.append(annualLeaveHour).append("\n\r").append("补休：");
			}
			
			if(null == compensatedLeaveHour){
				sb.append("0").append("\n\r").append("其他：");
			}else{
				sb.append(compensatedLeaveHour).append("\n\r").append("其他：");
			}
			
			if(null == otherHour){
				sb.append("0").append("\n\r").append("备注：");
			}else{
				sb.append(otherHour).append("\n\r").append("备注：");
			}
			
			if(null == remark){
				sb.append("0").append("\n\r").append("剩余年假：");
			}else{
				sb.append(remark).append("\n\r").append("剩余年假：");
			}
			
			if(null == remainAnnualLeaveHour){
				sb.append("0").append("\n\r").append("剩余加班：");
			}else{
				sb.append(remainAnnualLeaveHour).append("\n\r").append("剩余加班：");
			}
			
			if(null == remainOvertime){
				sb.append("0").append("\n\r");
			}else{
				sb.append(remainOvertime).append("\n\r");
			}
			
			
			//如果有请病假，则发送病假说明信息
			if(null != sickLeaveHour){
				
				sb.append("说明：").append(explain).append("\n\r");
			}
			return sb.toString();
		}


		
		public String getNameZH() {
			return nameZH;
		}

		public void setNameZH(String nameZH) {
			this.nameZH = nameZH;
		}

		public String getNameEN() {
			return nameEN;
		}

		public void setNameEN(String nameEN) {
			this.nameEN = nameEN;
		}

		public String getSickLeaveHour() {
			return sickLeaveHour;
		}

		public void setSickLeaveHour(String sickLeaveHour) {
			this.sickLeaveHour = sickLeaveHour;
		}

		public String getCasualLeaveHour() {
			return casualLeaveHour;
		}

		public void setCasualLeaveHour(String casualLeaveHour) {
			this.casualLeaveHour = casualLeaveHour;
		}

		public String getAnnualLeaveHour() {
			return annualLeaveHour;
		}

		public void setAnnualLeaveHour(String annualLeaveHour) {
			this.annualLeaveHour = annualLeaveHour;
		}

		public String getCompensatedLeaveHour() {
			return compensatedLeaveHour;
		}

		public void setCompensatedLeaveHour(String compensatedLeaveHour) {
			this.compensatedLeaveHour = compensatedLeaveHour;
		}

		public String getOtherHour() {
			return otherHour;
		}

		public void setOtherHour(String otherHour) {
			this.otherHour = otherHour;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getRemainAnnualLeaveHour() {
			return remainAnnualLeaveHour;
		}

		public void setRemainAnnualLeaveHour(String remainAnnualLeaveHour) {
			this.remainAnnualLeaveHour = remainAnnualLeaveHour;
		}

		public String getRemainOvertime() {
			return remainOvertime;
		}

		public void setRemainOvertime(String remainOvertime) {
			this.remainOvertime = remainOvertime;
		}

		public String getExplain() {
			return explain;
		}

		public void setExplain(String explain) {
			this.explain = explain;
		}
		
}
