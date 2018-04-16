package org.i3xx.cloud.data.coor.service.simple;

import java.util.ArrayList;
import java.util.List;

import org.i3xx.cloud.data.coor.service.threeway.LogData;
import org.i3xx.cloud.data.coor.service.threeway.LogParser;
import org.i3xx.cloud.data.coor.service.threeway.LogWriter;

public class SimpleProtocol {

	
	/** The single partner per default is 1 */
	public static final int PARTNER = 1;
	
	/** The protocol type is 5 */
	public static final int TYPE = 5;
	
	/** The initial data is 100 */
	public static final int DATA_INIT = 100;
	
	/** The valid data is 101 */
	public static final int DATA_VALID = 101;
	
	/** The valid data is 105 */
	public static final int DATA_OVERFLOW = 105;
	
	/** The valid data is 109 */
	public static final int DATA_ERROR = 109;
	
	/** The list of protocol data */
	private List<LogData> list;
	
	/**
	 * The partner of this protocol.
	 * 
	 * Only the matching partner is used to get the
	 * current entry.
	 */
	private int partner;
	
	/** 
	 * The type of this protocol
	 * Note: the type is irrelevant for the SimpleProtocol,
	 * but can be used outside the protocol for several reasons. 
	 */
	private int type;
	
	/**
	 * @return
	 */
	public static SimpleProtocol of() {
		return new SimpleProtocol();
	}
	
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
		partner = PARTNER;
		type = TYPE;
	}
	
	/**
	 * @param log
	 */
	public SimpleProtocol(String log) {
		list = new ArrayList<LogData>();
		partner = PARTNER;
		type = TYPE;
		
		this.parse(log);
	}
	
	/**
	 * @param partner
	 * @return
	 */
	public SimpleProtocol setPartner(int partner){
		this.partner = partner;
		return this;
	}

	/**
	 * @return
	 */
	public int getPartner() {
		return partner;
	}

	/**
	 * @param type the type to set
	 */
	public SimpleProtocol setType(int type) {
		this.type = type;
		return this;
	}
	
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
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
		if(isEmpty())
			setInit();
		return this;
	}
	
	/**
	 * @return
	 */
	public boolean isEmpty() {
		if(list.isEmpty())
			return true;
		
		//search for the type
		for(int i=(list.size()-1);i>-1;i--)
			if(list.get(i).getPartner()==partner)
				return false;
		
		return true;
	}
	
	/**
	 * @return
	 */
	public int getCurrent() {
		if(isEmpty())
			return 0;
		
		//search for the partner
		for(int i=(list.size()-1);i>-1;i--)
			if(list.get(i).getPartner()==partner)
				return list.get(i).getData();
		
		//nothing found
		return 0;
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
		case DATA_ERROR:
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
	public boolean isError() {
		 return getCurrent()==DATA_ERROR;
	}
	
	/**
	 * @return
	 */
	public SimpleProtocol setInit() {
		list.add(new LogData(partner, type, DATA_INIT));
		return this;
	}
	
	/**
	 * @return
	 */
	public SimpleProtocol setValid() {
		list.add(new LogData(partner, type, DATA_VALID));
		return this;
	}
	
	/**
	 * @return
	 */
	public SimpleProtocol setOverflow() {
		list.add(new LogData(partner, type, DATA_OVERFLOW));
		return this;
	}
	
	/**
	 * @return
	 */
	public SimpleProtocol setError() {
		list.add(new LogData(partner, type, DATA_ERROR));
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
