package org.i3xx.cloud.data.coor.service.simple;

import java.util.ArrayList;
import java.util.List;

import org.i3xx.cloud.data.coor.service.threeway.LogData;
import org.i3xx.cloud.data.coor.service.threeway.LogParser;
import org.i3xx.cloud.data.coor.service.threeway.LogWriter;

public class SimpleExtProtocol {

	
	/** The single partner is 1 */
	public static final int PARTNER = 1;
	
	/** The protocol type is 7 */
	public static final int TYPE = 7;
	
	/** The initial data is 100 */
	public static final int DATA_INIT = 100;
	
	/** The valid data is 100 */
	public static final int DATA_VALID = 101;
	
	/** The valid data is 105 */
	public static final int DATA_OVERFLOW = 105;
	
	private List<LogData> list;
	
	/**
	 * @param log
	 * @return
	 */
	public static SimpleExtProtocol of(String log) {
		return new SimpleExtProtocol(log);
	}
	
	/**
	 * 
	 */
	public SimpleExtProtocol() {
		list = new ArrayList<LogData>();
	}
	
	/**
	 * @param log
	 */
	public SimpleExtProtocol(String log) {
		list = new ArrayList<LogData>();
		parse(log);
	}
	
	/**
	 * @return
	 */
	public SimpleExtProtocol clear() {
		list.clear();
		return this;
	}
	
	/**
	 * @param log
	 * @return
	 */
	public SimpleExtProtocol parse(String log) {
		if(log!=null) {
			LogParser parser = new LogParser();
			parser.setList(list);
			parser.parse(log);
		}//fi
		return this;
	}
	
	/**
	 * @return
	 */
	public SimpleExtProtocol ensureInit() {
		if(list.isEmpty())
			setInit();
		
		return this;
	}
	
	/**
	 * @return
	 */
	public SimpleExtProtocol ensureResetAndInit() {
		if(list.isEmpty()) {
			setInit();
			return this;
		}
		
		//Up to three nodes former protocol and INIT
		if(list.size()<=4 && isInit())
			return this;
		
		//save the last transaction by searching for INIT
		//use a maximum of 3 nodes
		List<LogData> dd = new ArrayList<LogData>();
		for(int i=(list.size()-1);i>=0;i--) {
			LogData d = list.get(i);
			dd.add(0, d);
			
			//stop if INIT is reached
			if(d.getData()==DATA_INIT)
				break;
			
			//stop if there are three nodes or more
			if(dd.size()>=3) {
				break;
			}
		}//for
		list = dd;
		
		//set INIT
		if(!isInit())
			setInit();
		
		return this;
	}
	
	/**
	 * @return
	 */
	public int getCurrent() {
		if(list.isEmpty())
			return 0;
		
		LogData ldat = list.get(list.size()-1);
		return ldat.getData();
	}
	
	/**
	 * @return
	 */
	public SimpleExtProtocol setNext() {
		switch(getCurrent()) {
		case 0:
			setInit();
			break;
		case DATA_INIT:
			setValid();
			break;
		case DATA_VALID:
			setOverflow();
			break;
		case DATA_OVERFLOW:
			default:
				//does nothing
		}
		return this;
	}
	
	/**
	 * @return
	 */
	public boolean isInit() {
		 return getCurrent()==DATA_INIT;
	}
	
	/**
	 * @return
	 */
	public boolean isValid() {
		 return getCurrent()==DATA_VALID;
	}
	
	/**
	 * @return
	 */
	public boolean isOverflow() {
		 return getCurrent()==DATA_OVERFLOW;
	}
	
	/**
	 * @return
	 */
	public SimpleExtProtocol setInit() {
		list.add(new LogData(PARTNER, TYPE, DATA_INIT));
		return this;
	}
	
	/**
	 * @return
	 */
	public SimpleExtProtocol setValid() {
		list.add(new LogData(PARTNER, TYPE, DATA_VALID));
		return this;
	}
	
	/**
	 * @return
	 */
	public SimpleExtProtocol setOverflow() {
		list.add(new LogData(PARTNER, TYPE, DATA_OVERFLOW));
		return this;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		LogWriter writer = new LogWriter();
		writer.setList(list);
		return writer.toString();
	}

}
