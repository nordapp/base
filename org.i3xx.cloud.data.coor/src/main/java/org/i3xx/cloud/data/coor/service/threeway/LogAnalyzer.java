package org.i3xx.cloud.data.coor.service.threeway;

import static org.i3xx.cloud.data.coor.domain.IHandshake.*;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Stefan
 *
 */
public class LogAnalyzer {
	
	private static final Logger logger = LoggerFactory.getLogger(LogAnalyzer.class);
	
	//Errors
	public static final int PART_IS_PART_1_BUT_DOES_NOT_MATCH = 1;
	public static final int PART_IS_PART_2_BUT_DOES_NOT_MATCH = 2;
	public static final int PART_IS_PART_3_BUT_DOES_NOT_MATCH = 3;
	public static final int PART_IS_PART_4_BUT_DOES_NOT_MATCH = 4;
	public static final int THE_PARTNER_DOES_NOT_MATCH = 5;
	public static final int THE_PART_IS_NOT_KNOWN = 6;
	public static final int PART_IS_PART_5_BUT_THERE_IS_NO_SUCH_PART_5 = 7;
	public static final int THE_PART_IS_ANALYZED_BUT_FAILED = 8;
	
	//Oks
	public static final int PART_0_REACHED = -1;
	public static final int PART_1_REACHED = -2;
	public static final int PART_2_REACHED = -3;
	public static final int PART_3_REACHED = -4;
	public static final int PART_4_REACHED = -5;
	
	//Data
	public static final int PART_0_ANALYZED = -6;
	public static final int PART_1_ANALYZED = -7;
	public static final int PART_2_ANALYZED = -8;
	public static final int PART_3_ANALYZED = -9;
	public static final int PART_4_ANALYZED = -10;
	
	private List<LogData> list;
	private int partner;
	private int countAck;
	private int countSyn;
	private boolean ackFlag;
	private boolean finFlag;
	private boolean synFlag;
	private boolean rstFlag;
	
	/** If true the partner is no stop criterion, necessary if two processors work with the same dataset. */
	private boolean dryPartner;

	public LogAnalyzer() {
		list = null;
		partner = 0;
		dryPartner = false;
		countAck = 0;
		countSyn = 0;
		ackFlag = false;
		synFlag = false;
		finFlag = false;
		rstFlag = false;
	}
	
	public void reset() {
		list = new ArrayList<LogData>();
		partner = 0;
		dryPartner = false;
		countAck = 0;
		countSyn = 0;
		ackFlag = false;
		synFlag = false;
		finFlag = false;
		rstFlag = false;
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
	 * Needs the analyzing process finished.
	 * 
	 * @return
	 */
	public int getPart() {
		if(isPart0()){
			return PART_0_REACHED;
		}
		else {
			if(isPart1()) {
				return PART_1_REACHED;
			}//fi
			else if(isPart2()) {
				return PART_2_REACHED;
			}//fi
			else if(isPart3()) {
				return PART_3_REACHED;
			}//fi
			else if(isPart4()) {
				return PART_4_REACHED;
			}//fi
			else{
				return THE_PART_IS_NOT_KNOWN;
			}//fi
		}//fi
	}
	
	/**
	 * This value can be used with analyzeCommunication to verify the last log entry
	 * of the protocol.
	 * 
	 * @return The value of the latest log entry.
	 */
	public int getCurrentPartValue() {
		return list.isEmpty() ? 0 : list.get(list.size()-1).getData();
	}
	
	/**
	 * This value can be used to test the last log entry
	 * of the protocol.
	 * 
	 * @return The type of the latest log entry.
	 */
	public int getCurrentPartType() {
		return list.isEmpty() ? 0 : list.get(list.size()-1).getType();
	}
	
	/**
	 * Analyzes the communication using the log. Doesn't change the log or the data.
	 * 
	 * @param newValue The value set or received
	 * @return A negative return value indicates that the protocol runs well, a positive value means
	 * an error has occurred.<br>Positive values are: <b>PART_0_REACHED</b>, <b>PART_1_REACHED</b>, <b>PART_2_REACHED</b>,
	 * <b>PART_3_REACHED</b>, <b>PART_4_REACHED</b><br>Negative values are:<b>PART_IS_PART_1_BUT_DOES_NOT_MATCH</b>,
	 * <b>PART_IS_PART_2_BUT_DOES_NOT_MATCH</b>, <b>PART_IS_PART_3_BUT_DOES_NOT_MATCH</b>, <b>PART_IS_PART_4_BUT_DOES_NOT_MATCH</b>,
	 * <b>THE_PART_IS_NOT_KNOWN</b>
	 */
	public int analyzeCommunication(int newValue) {
		
		logger.trace("Comm size:{}, countAck:{}, countSyn:{}, partner:{}, ackFlag:{}, synFlag:{}, finFlag:{}, rstFlag:{}",
				list.size(), countAck, countSyn, partner, ackFlag, synFlag, finFlag, rstFlag);
		
		if(isPart0()){
			return PART_0_REACHED;
		}
		else {
			if(isPart1()) {
				if( analyzePart1(newValue) ){
					return PART_1_REACHED;
				}else{
					return PART_IS_PART_1_BUT_DOES_NOT_MATCH;
				}//fi
			}//fi
			else if(isPart2()) {
				if( analyzePart2(newValue) ){
					return PART_2_REACHED;
				}else{
					return PART_IS_PART_2_BUT_DOES_NOT_MATCH;
				}//fi
			}//fi
			else if(isPart3()) {
				if( analyzePart3(newValue) ){
					return PART_3_REACHED;
				}else{
					return PART_IS_PART_3_BUT_DOES_NOT_MATCH;
				}//fi
			}//fi
			else if(isPart4()) {
				if( analyzePart4(newValue) ){
					return PART_4_REACHED;
				}else{
					return PART_IS_PART_4_BUT_DOES_NOT_MATCH;
				}//fi
			}//fi
			else{
				return THE_PART_IS_NOT_KNOWN;
			}//fi
		}//fi
	}
	
	/**
	 * Analyzes the log and set the data.
	 * 
	 * @return
	 */
	public int analyzeAndSetData() {
		
		logger.trace("Data size:{}, countAckDefault:{}, countSynDefault:{}, partner:{}, ackFlag:{}, synFlag:{}, finFlag:{}, rstFlag:{}",
				list.size(), countAck, countSyn, partner, ackFlag, synFlag, finFlag, rstFlag);
		
		//New empty analyzer
		if(isPart0()){
			return PART_0_ANALYZED;
		}
		
		//Needed to get the partner
		else if(isAfterPart0()) {
			analyzePartner(); //always true in this case
		}
		
		//The protocol is in progress but a new analyzer is built
		else if(isUnset()) {
			//
			// do every step from 0 ... n-1 inside a loop
			//
			List<LogData> ls = list;
			//Use a new list for recursive processing
			list = new ArrayList<LogData>();
			for(int i=0;i<ls.size()-1;i++) {
				list.add(ls.get(i));
				analyzeAndSetData();
			}//for
			//Keep the the original list
			list = ls;
			//
			// and proceed with the step n
			//
		}//fi
		
		if(isAfterPart0()) {
			if( /*analyzePartner() &&*/ analyzeFlags() && analyzeAck() ){
				return PART_1_ANALYZED;
			}
		}//fi
		else if(isAfterPart1()) {
			if( analyzePartner() && analyzeFlags() ){
				return PART_2_ANALYZED;
			}//fi
		}//fi
		else if(isAfterPart2()) {
			if( analyzePartner() && analyzeFlags() && analyzeSyn() ){
				return PART_3_ANALYZED;
			}//fi
		}//fi
		else if(isAfterPart3()) {
			if( analyzePartner() && analyzeFlags() ){
				return PART_4_ANALYZED;
			}//fi
		}//fi
		else{
			return THE_PART_IS_NOT_KNOWN;
		}//fi
		
		return THE_PART_IS_ANALYZED_BUT_FAILED;
	}
	
	/**
	 * A new part has been reached. The current part is part0.
	 * PARTNER_A: send ACK (number)
	 * PARTNER_B: received ACK (number)
	 * 
	 * @param newValue The received or set value
	 * @return
	 */
	public boolean analyzePart1(int newValue) {
		if( !isPart1())
			return false;
		
		if( countAck!=newValue )
			return false;
		
		return true;
	}
	
	/**
	 * A new part has been reached. The current part is part1.
	 * PARTNER_B: return ACK (number+1
	 * PARTNER_A: received ACK (test count-ack==number+1)
	 * 
	 * @param newValue The received or set value
	 * @return
	 */
	public boolean analyzePart2(int newValue) {
		if( !isPart2())
			return false;
		
		if( (countAck+1)!=newValue )
			return false;
		
		return true;
	}
	
	/**
	 * A new part has been reached. The current part is part2.
	 * PARTNER_B: send SYN (number)
	 * PARTNER_A: received SYN (number)
	 * 
	 * @param newValue The received or set value
	 * @return
	 */
	public boolean analyzePart3(int newValue) {
		if( !isPart3())
			return false;
		
		if( countSyn!=newValue )
			return false;
		
		return true;
	}
	
	/**
	 * A new part has been reached. The current part is part3.
	 * PARTNER_A: return SYN (number+1)
	 * PARTNER_B: received SYN (test count-syn==number+1)
	 * 
	 * @param newValue The received or set value
	 * @return
	 */
	public boolean analyzePart4(int newValue) {
		if( !isPart4())
			return false;
		
		if( (countSyn+1)!=newValue )
			return false;
		
		return true;
	}
	
	/**
	 * Nothing is set
	 * 
	 * @return
	 */
	public boolean isPart0() {
		return ( list.size()==0 && countAck==0 && countSyn==0 );
	}
	/** The part is done, analyze log to get data */
	public boolean isAfterPart0() {
		return ( list.size()==1 && countAck==0 && countSyn==0 );
	}
	
	/**
	 * PARTNER_A: send ACK (number)
	 * PARTNER_B: received ACK (number)
	 * @return
	 */
	public boolean isPart1() {
		return ( list.size()==1 && countAck!=0 && countSyn==0 );
	}
	/** The part is done, analyze log to get data */
	public boolean isAfterPart1() {
		return ( list.size()==2 && countAck!=0 && countSyn==0 );
	}
	
	/**
	 * PARTNER_B: return ACK (number+1)
	 * PARTNER_A: received ACK (test count-ack==number+1)
	 * @return
	 */
	public boolean isPart2() {
		return ( list.size()==2 && countAck!=0 && countSyn==0 );
	}
	/** The part is done, analyze log to get data */
	public boolean isAfterPart2() {
		return ( list.size()==3 && countAck!=0 && countSyn==0 );
	}
	
	/**
	 * PARTNER_B: send SYN (number)
	 * PARTNER_A: received SYN (number)
	 * @return
	 */
	public boolean isPart3() {
		return ( list.size()==3 && countAck!=0 && countSyn!=0 );
	}
	/** The part is done, analyze log to get data */
	public boolean isAfterPart3() {
		return ( list.size()==4 && countAck!=0 && countSyn!=0 );
	}
	
	/**
	 * PARTNER_A: return SYN (number+1
	 * PARTNER_B: received SYN (test count-syn==number+1)
	 * @return
	 */
	public boolean isPart4() {
		return ( list.size()==4 && countAck!=0 && countSyn!=0 );
	}
	
	/**
	 * Gets the partner and analyzes the list
	 * @return true if the partner is properly set at each entry of the list, false if an error occurs.
	 */
	public boolean analyzePartner() {
		if(dryPartner && partner!=0)
			return true;
		
		for(int i=0;i<list.size();i++) {
			LogData d = list.get(i);
			if(partner==0) {
				partner = d.getPartner();
			}else if(partner==d.getPartner()) {
				//ok
			}else{
				return dryPartner ? true : false;
			}
		}//for
		return true;
	}

	/**
	 * @return the dryPartner
	 */
	public boolean isDryPartner() {
		return dryPartner;
	}

	/**
	 * @param dryPartner the dryPartner to set
	 */
	public void setDryPartner(boolean dryPartner) {
		this.dryPartner = dryPartner;
	}
	
	/**
	 * @return
	 */
	public boolean analyzeFlags() {
		int i = list.size()-1;
		if(i<0)
			return false;
		
		LogData d = list.get(i);
		switch(d.getType()) {
		case ACK_FLAG:
			ackFlag = true;
			return true;
		case SYN_FLAG:
			synFlag = true;
			return true;
		case FIN_FLAG:
			finFlag = true;
			return true;
		case RST_FLAG:
			rstFlag = true;
			return false;
		default:
			return false;
		}
	}
	
	/**
	 * @return
	 */
	public boolean analyzeAck() {
		int i = list.size()-1;
		if(i<0)
			return false;
		
		LogData d = list.get(i);
		countAck = d.getData();
		return true;
	}
	
	/**
	 * @return
	 */
	public boolean analyzeSyn() {
		int i = list.size()-1;
		if(i<0)
			return false;
		
		LogData d = list.get(i);
		countSyn = d.getData();
		return true;
	}

	/**
	 * @return the ackFlag
	 */
	public boolean isAckFlag() {
		return ackFlag;
	}

	/**
	 * @param ackFlag the ackFlag to set
	 */
	public void setAckFlag(boolean ackFlag) {
		this.ackFlag = ackFlag;
	}

	/**
	 * @return the finFlag
	 */
	public boolean isFinFlag() {
		return finFlag;
	}

	/**
	 * @param finFlag the finFlag to set
	 */
	public void setFinFlag(boolean finFlag) {
		this.finFlag = finFlag;
	}

	/**
	 * @return the synFlag
	 */
	public boolean isSynFlag() {
		return synFlag;
	}

	/**
	 * @param synFlag the synFlag to set
	 */
	public void setSynFlag(boolean synFlag) {
		this.synFlag = synFlag;
	}

	/**
	 * @return the rstFlag
	 */
	public boolean isRstFlag() {
		return rstFlag;
	}

	/**
	 * @param rstFlag the rstFlag to set
	 */
	public void setRstFlag(boolean rstFlag) {
		this.rstFlag = rstFlag;
	}
	
	/**
	 * The list contains entries but nothing is set yet.
	 * 
	 * @return
	 */
	public boolean isUnset() {
		return ( !list.isEmpty() && countAck==0 && countSyn==0 &&
				!ackFlag && !synFlag && !rstFlag && !finFlag );
	}
}
