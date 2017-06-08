package org.i3xx.cloud.data.coor.service.threeway;

import java.util.ArrayList;
import java.util.List;

public class LogParser {
	
	private List<LogData> list;
	
	/**
	 * @param log
	 * @return
	 * @throws IllegalArgumentException
	 */
	public final static LogParser of(String log) {
		LogParser parser = new LogParser();
		parser.setList( new ArrayList<LogData>() );
		parser.parse(log);
		return parser;
	}
	
	/**
	 * 
	 */
	public LogParser() {
		list = null;
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
	
	/**
	 * @param log
	 * @throws IllegalArgumentException
	 */
	public void parse(String log) throws IllegalArgumentException {
		try{
			list.clear();
			String[] p = log.split(LogData.separator);
			for(int i=0;i<p.length-2;i+=3) {
				list.add( new LogData(Integer.parseInt(p[i]), Integer.parseInt(p[i+1]), Integer.parseInt(p[i+2])) );
			}
		}catch(Exception e){
			IllegalArgumentException t = new IllegalArgumentException("Illegal Argument'"+log+"'.");
			t.initCause(e);
			throw t;
		}
	}

}
