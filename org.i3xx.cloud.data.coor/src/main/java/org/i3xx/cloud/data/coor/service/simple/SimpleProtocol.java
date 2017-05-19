package org.i3xx.cloud.data.coor.service.simple;

import java.util.ArrayList;
import java.util.List;

import org.i3xx.cloud.data.coor.service.threeway.LogData;
import org.i3xx.cloud.data.coor.service.threeway.LogParser;
import org.i3xx.cloud.data.coor.service.threeway.LogWriter;

public class SimpleProtocol {

	
	/** The single partner is 1 */
	public static final int PARTNER = 1;
	
	/** The protocol type is 5 */
	public static final int TYPE = 5;
	
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
	public static SimpleProtocol of(String log) {
		return new SimpleProtocol(log);
	}
	
	/**
	 * 
	 */
	public SimpleProtocol() {
		list = new ArrayList<LogData>();
	}
	
	/**
	 * @param log
	 */
	public SimpleProtocol(String log) {
		list = new ArrayList<LogData>();
		parse(log);
	}
	
	/**
	 * @return
	 */
	public SimpleProtocol clear() {
		list.clear();
		return this;
	}
	
	/**
	 * @param log
	 * @return
	 */
	public SimpleProtocol parse(String log) {
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
	public SimpleProtocol ensureInit() {
		if(list.isEmpty())
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
	public SimpleProtocol setNext() {
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
	public SimpleProtocol setInit() {
		list.add(new LogData(PARTNER, TYPE, DATA_INIT));
		return this;
	}
	
	/**
	 * @return
	 */
	public SimpleProtocol setValid() {
		list.add(new LogData(PARTNER, TYPE, DATA_VALID));
		return this;
	}
	
	/**
	 * @return
	 */
	public SimpleProtocol setOverflow() {
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
