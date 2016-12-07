package org.i3xx.cloud.data.coor.service.threeway;

public class LogData {
	
	public static final String separator = "-";
	
	private int type;
	private int data;
	private int partner;
	
	public LogData() {
		type = 0;
		data = 0;
		partner = 0;
	}

	public LogData(int partner, int type, int data) {
		this.type = type;
		this.data = data;
		this.partner = partner;
	}

	/**
	 * @return the partner
	 */
	public int getPartner() {
		return partner;
	}

	/**
	 * @param partner the partner to set
	 */
	public void setPartner(int partner) {
		this.partner = partner;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the data
	 */
	public int getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(int data) {
		this.data = data;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(LogData.class.getName());
		buf.append(" (partner:");
		buf.append(partner);
		buf.append(", type:");
		buf.append(type);
		buf.append(", data:");
		buf.append(data);
		buf.append(")");
		return buf.toString();
	}

}
