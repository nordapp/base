package org.i3xx.cloud.data.coor.service.threeway;

import java.util.List;

public class LogWriter {
	
	private List<LogData> list;
	
	public LogWriter() {
		list = null;
	}
	
	/**
	 * @param partner
	 * @param type
	 * @param data
	 */
	public void log(int partner, int type, int data) {
		list.add(new LogData(partner, type, data));
	}

	/**
	 * @return the list
	 */
	public List<LogData> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<LogData> list) {
		this.list = list;
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		
		for(int i=0;i<list.size();i++) {
			LogData data = list.get(i);
			buf.append(data.getPartner());
			buf.append(LogData.separator);
			buf.append(data.getType());
			buf.append(LogData.separator);
			buf.append(data.getData());
			buf.append(LogData.separator);
		}//for
		
		return buf.toString();
	}
}
