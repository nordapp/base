package org.i3xx.cloud.data.coor.service.simple;

import java.util.ArrayList;
import java.util.List;

import org.i3xx.cloud.data.coor.service.threeway.LogData;
import org.i3xx.cloud.data.coor.service.threeway.LogParser;
import org.i3xx.cloud.data.coor.service.threeway.LogWriter;

public class SerialProtocol {
	
	/** The single partner is 1 */
	public static final int PARTNER = 1;
	
	/** The protocol type is 6 */
	public static final int TYPE = 6;
	
	/** The initial data is 100 */
	public static final int DATA_INIT = 100;
	
	/** The data value is 101 */
	public static final int DATA_ONE = 101;
	
	/** The data value is 102 */
	public static final int DATA_TWO = 102;
	
	/** The data value is 103 */
	public static final int DATA_THREE = 103;
	
	/** The data value is 104 */
	public static final int DATA_FOUR = 104;
	
	/** The data value is 105 */
	public static final int DATA_FIVE = 105;
	
	/** The data value is 106 */
	public static final int DATA_SIX = 106;
	
	private List<LogData> list;
	
	/**
	 * @param log
	 * @return
	 */
	public static SerialProtocol of() {
		return new SerialProtocol();
	}
	
	/**
	 * @param log
	 * @return
	 */
	public static SerialProtocol of(String log) {
		return new SerialProtocol(log);
	}

	/**
	 * 
	 */
	public SerialProtocol() {
		list = new ArrayList<LogData>();
	}
	
	/**
	 * @param log
	 */
	public SerialProtocol(String log) {
		list = new ArrayList<LogData>();
		parse(log);
	}
	
	/**
	 * @return
	 */
	public SerialProtocol clear() {
		list.clear();
		return this;
	}
	
	/**
	 * @param log
	 * @return
	 */
	public SerialProtocol parse(String log) {
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
	public SerialProtocol ensureInit() {
		if(list.isEmpty())
			setInit();
		return this;
	}
	
	/**
	 * @return
	 */
	public SerialProtocol setInit() {
		list.add(new LogData(PARTNER, TYPE, DATA_INIT));
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
	public int size() {
		return list.size();
	}
	
	/**
	 * @return
	 */
	public SerialProtocol setNext() {
		int next = getCurrent()+1;
		list.add(new LogData(PARTNER, TYPE, next));
		return this;
	}
	
	/**
	 * @param index The index of the value to set
	 * @param value The value
	 * @return
	 */
	public SerialProtocol setValue(int index, int value) {
		LogData ldat = list.get(index);
		ldat.setData(value);
		return this;
	}
	
	/**
	 * @param index The index of the value to set
	 * @param value The value
	 * @return
	 */
	public boolean isValue(int index, int value) {
		LogData ldat = list.get(index);
		return ldat.getData()==value;
	}
	
	/**
	 * @param index The index of the value to set
	 * @param value The value
	 * @return 1 if the value is bigger than the stored one
	 * and -1 if the value is smaller than the stored value
	 * and 0 if both values are equal.
	 */
	public int compareValue(int index, int value) {
		LogData ldat = list.get(index);
		int data = ldat.getData();
		return data>value ? -1 : data<value ? 1 : 0;
	}
	
	/**
	 * @return
	 */
	public boolean isCurrent(int current) {
		 return getCurrent()==current;
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
