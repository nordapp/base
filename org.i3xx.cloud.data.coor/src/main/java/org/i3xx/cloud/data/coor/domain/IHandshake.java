package org.i3xx.cloud.data.coor.domain;

public interface IHandshake {
	
	/** The client process data */
	public static final int PARTNER_A = 1;
	/** The server is waiting for */
	public static final int PARTNER_B = 2;
	
	/** ACK */
	public static final int ACK_FLAG = 0x1;
	/** SYN */
	public static final int SYN_FLAG = 0x2;
	/** RST */
	public static final int RST_FLAG = 0x4;
	/** FIN */
	public static final int FIN_FLAG = 0x8;
	
	/** start */
	public static final int STATE_PART_0 = 0x1;
	/** ack or fin */
	public static final int STATE_PART_1 = 0x2;
	/** ack+syn or fin+syn */
	public static final int STATE_PART_2 = 0x4;
	/** syn */
	public static final int STATE_PART_3 = 0x8;
	
	/** The process is initiated */
	public static final int TYPE_ACK = 0x10;
	/** The process is finished */
	public static final int TYPE_FIN = 0x20;

	/**
	 * @return the syn
	 */
	int getSyn();

	/**
	 * @param syn the syn to set
	 */
	void setSyn(int syn);

	/**
	 * @return the ack
	 */
	int getAck();

	/**
	 * @param ack the ack to set
	 */
	void setAck(int ack);

	/**
	 * @return the timeout
	 */
	long getTimeout();

	/**
	 * @param timeout the timeout to set
	 */
	void setTimeout(long timeout);

	/**
	 * @return the fin
	 */
	int getFin();

	/**
	 * @param fin the fin to set
	 */
	void setFin(int fin);

	/**
	 * @return the rst
	 */
	int getRst();

	/**
	 * @param rst the rst to set
	 */
	void setRst(int rst);

	/**
	 * @return the log
	 */
	String getLog();

	/**
	 * @param log the log to set
	 */
	void setLog(String log);

	/**
	 * 
	 */
	void reset();

}
