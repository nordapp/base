package org.i3xx.cloud.data.coor.service.threeway;

import java.util.ArrayList;
import java.util.List;

import org.i3xx.cloud.data.coor.domain.IHandshake;

public class RunProtocol {
	
	final int partner;
	final int ackId;
	final int synId;
	
	final IHandshake handshake;
	final boolean dryPartner;
	final boolean ackFlag;
	
	/**
	 * A possible protocol implementation of a writer and a reader of the data.
	 * One service uses the writer and another the reader to inform about the state.
	 * <br>Writer:
	 * <br><code>
	 * <br>ObjectHandshake h = ...;
	 * <br>RunProtocol run = new RunProtocol(h, ObjectHandshake.PARTNER_A, 100, 200, true);
	 * <br>run.beginA();
	 * <br>//Add the data processing here ...
	 * <br>run.commitA();
	 * <br></code>
	 * <br>Reader:
	 * <br><code>
	 * <br>ObjectHandshake h = ...;
	 * <br>RunProtocol run = new RunProtocol(h, ObjectHandshake.PARTNER_B, 100, 200, true);
	 * <br>run.beginB();
	 * <br>//Add the data processing here ...
	 * <br>run.commitB();
	 * <br></code>
	 * 
	 * @param handshake The handshake object that holds the log
	 * @param partner The partner who runs the log
	 * (default: writer: ObjectHandshake.PARTNER_A, reader: ObjectHandshake.PARTNER_B)
	 * @param ackId The default ack id (default: 100)
	 * @param synId The default syn id (default: 200)
	 * @param dryPartner Must be true if there are two or more partner running the log. The parameter
	 * set to true allows more than one partner writing the log. (default:true)
	 * @param ackFlag Flag to use the ACK (true) or the FIN (false) process
	 */
	public RunProtocol(IHandshake handshake, int partner, int ackId, int synId, boolean dryPartner, boolean ackFlag) {
		
		this.handshake = handshake;
		this.dryPartner = dryPartner;
		this.ackFlag = ackFlag;
		this.partner = partner;
		this.ackId = ackId;
		this.synId = synId;
	}
	
	/**
	 * @return
	 */
	public boolean beginA() {
		
		List<LogData> list = new ArrayList<LogData>();
		LogParser log = new LogParser();
		log.setList(list);
		LogProcessor prc = new LogProcessor(handshake);
		prc.setList(list);
		prc.setAckFlag(ackFlag);
		prc.setPartner(partner);
		prc.setAckId(ackId);
		prc.setSynId(synId);
		LogAnalyzer aly = new LogAnalyzer();
		aly.setList(list);
		aly.setDryPartner(dryPartner);
		
		log.parse(handshake.getLog());
		
		if(LogAnalyzer.PART_0_REACHED!=aly.analyzeCommunication(0))
			return false;
		
		//sends ACK
		prc.doPart1();
		
		if(LogAnalyzer.PART_1_ANALYZED!=aly.analyzeAndSetData())
			return false;
		if(LogAnalyzer.PART_1_REACHED!=aly.analyzeCommunication( ackId ))
			return false;
		if( ackId != aly.getCurrentPartValue() )
			return false;
		
		return true;
	}
	
	/**
	 * @return
	 */
	public boolean commitA() {
		
		List<LogData> list = new ArrayList<LogData>();
		LogParser log = new LogParser();
		log.setList(list);
		LogProcessor prc = new LogProcessor(handshake);
		prc.setList(list);
		prc.setAckFlag(ackFlag);
		prc.setPartner(partner);
		prc.setAckId(ackId);
		prc.setSynId(synId);
		LogAnalyzer aly = new LogAnalyzer();
		aly.setList(list);
		aly.setDryPartner(dryPartner);
		
		log.parse(handshake.getLog());
		
		if(LogAnalyzer.PART_1_ANALYZED!=aly.analyzeAndSetData())
			return false;
		if(LogAnalyzer.PART_1_REACHED!=aly.analyzeCommunication(ackId))
			return false;
		
		prc.doPart2();
		
		log.parse(handshake.getLog());
		if(LogAnalyzer.PART_2_ANALYZED!=aly.analyzeAndSetData())
			return false;
		if(LogAnalyzer.PART_2_REACHED!=aly.analyzeCommunication( ackId+1 ))
			return false;
		if( (ackId+1) != aly.getCurrentPartValue() )
			return false;
		
		return true;
	}
	
	/**
	 * @return
	 */
	public boolean beginB() {
		
		List<LogData> list = new ArrayList<LogData>();
		LogParser log = new LogParser();
		log.setList(list);
		LogProcessor prc = new LogProcessor(handshake);
		prc.setList(list);
		prc.setAckFlag(ackFlag);
		prc.setPartner(partner);
		prc.setAckId(ackId);
		prc.setSynId(synId);
		LogAnalyzer aly = new LogAnalyzer();
		aly.setList(list);
		aly.setDryPartner(dryPartner);
		
		log.parse(handshake.getLog());
		
		if(LogAnalyzer.PART_2_ANALYZED!=aly.analyzeAndSetData())
			return false;
		if(LogAnalyzer.PART_2_REACHED!=aly.analyzeCommunication(ackId+1))
			return false;
		
		prc.doPart3();
		
		log.parse(handshake.getLog());
		if(LogAnalyzer.PART_3_ANALYZED!=aly.analyzeAndSetData())
			return false;
		if(LogAnalyzer.PART_3_REACHED!=aly.analyzeCommunication( synId ))
			return false;
		if( synId != aly.getCurrentPartValue() )
			return false;
		
		return true;
	}
	
	/**
	 * @return
	 */
	public boolean commitB() {
		
		List<LogData> list = new ArrayList<LogData>();
		LogParser log = new LogParser();
		log.setList(list);
		LogProcessor prc = new LogProcessor(handshake);
		prc.setList(list);
		prc.setAckFlag(ackFlag);
		prc.setPartner(partner);
		prc.setAckId(ackId);
		prc.setSynId(synId);
		LogAnalyzer aly = new LogAnalyzer();
		aly.setList(list);
		aly.setDryPartner(dryPartner);
		
		log.parse(handshake.getLog());
		
		if(LogAnalyzer.PART_3_ANALYZED!=aly.analyzeAndSetData())
			return false;
		if(LogAnalyzer.PART_3_REACHED!=aly.analyzeCommunication(synId))
			return false;
		
		prc.doPart4();
		
		log.parse(handshake.getLog());
		if(LogAnalyzer.PART_4_ANALYZED!=aly.analyzeAndSetData())
			return false;
		if(LogAnalyzer.PART_4_REACHED!=aly.analyzeCommunication( synId+1 ))
			return false;
		if( (synId+1) != aly.getCurrentPartValue() )
			return false;
		
		return true;
	}
	
	/**
	 * @return
	 */
	public boolean reset() {
		
		List<LogData> list = new ArrayList<LogData>();
		LogParser log = new LogParser();
		log.setList(list);
		LogProcessor prc = new LogProcessor(handshake);
		prc.setList(list);
		prc.setAckFlag(ackFlag);
		prc.setPartner(partner);
		prc.setAckId(ackId);
		prc.setSynId(synId);
		LogAnalyzer aly = new LogAnalyzer();
		aly.setList(list);
		aly.setDryPartner(dryPartner);
		
		log.parse(handshake.getLog());
		
		switch( aly.analyzeAndSetData() ) {
		case LogAnalyzer.PART_0_ANALYZED:
			prc.doReset1();
			break;
		case LogAnalyzer.PART_1_ANALYZED:
			prc.doReset2();
			break;
		case LogAnalyzer.PART_2_ANALYZED:
			prc.doReset3();
			break;
		case LogAnalyzer.PART_3_ANALYZED:
			prc.doReset4();
			break;
		case LogAnalyzer.PART_4_ANALYZED:
			//the workflow is committed, no reset is possible
			return false;
		default:
		}
		
		return true;
	}
	
	/**
	 * @return 
	 * <br>LogAnalyzer.PART_0_ANALYZED after start
	 * <br>LogAnalyzer.PART_1_ANALYZED after beginA
	 * <br>LogAnalyzer.PART_2_ANALYZED after commitA
	 * <br>LogAnalyzer.PART_3_ANALYZED after beginB
	 * <br>LogAnalyzer.PART_4_ANALYZED after commitB
	 */
	public int getCurrent() {
		
		List<LogData> list = new ArrayList<LogData>();
		LogParser log = new LogParser();
		log.setList(list);
		LogAnalyzer aly = new LogAnalyzer();
		aly.setList(list);
		aly.setDryPartner(dryPartner);
		
		log.parse(handshake.getLog());
		
		return( aly.analyzeAndSetData() );
	}
	
	/**
	 * @return The analyzer
	 */
	public LogAnalyzer getCurrentAnalyzer() {
		
		List<LogData> list = new ArrayList<LogData>();
		LogParser log = new LogParser();
		log.setList(list);
		LogAnalyzer aly = new LogAnalyzer();
		aly.setList(list);
		aly.setDryPartner(dryPartner);
		
		log.parse(handshake.getLog());
		aly.analyzeAndSetData();
		
		return aly;
	}

	/**
	 * @return the handshake
	 */
	public IHandshake getHandshake() {
		return handshake;
	}

	/**
	 * @return the partner
	 */
	public int getPartner() {
		return partner;
	}

	/**
	 * @return the ackId
	 */
	public int getAckId() {
		return ackId;
	}

	/**
	 * @return the synId
	 */
	public int getSynId() {
		return synId;
	}

	/**
	 * @return the dryPartner
	 */
	public boolean isDryPartner() {
		return dryPartner;
	}

	/**
	 * @return the ackFlag
	 */
	public boolean isAckFlag() {
		return ackFlag;
	}

}
