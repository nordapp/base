package org.i3xx.cloud.data.coor.service.threeway;

import static org.i3xx.cloud.data.coor.domain.IHandshake.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.i3xx.cloud.data.coor.service.threeway.LogAnalyzer;
import org.i3xx.cloud.data.coor.service.threeway.LogData;
import org.i3xx.cloud.data.coor.service.threeway.LogProcessor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AnalyzerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Tests ACK, ACK+SYN, SYN
	 */
	@Test
	public void testA() {
		List<LogData> list = new ArrayList<LogData>();
		LogAnalyzer aly = new LogAnalyzer();
		aly.setList(list);
		LogProcessor prc = new LogProcessor();
		prc.setList(list);
		
		final int ackId = 100;
		final int synId = 200;
		
		prc.setAckFlag(true);
		prc.setPartner(PARTNER_A);
		prc.setAckId(ackId);
		prc.setSynId(synId);
		
		//Start
		assertEquals(LogAnalyzer.PART_0_REACHED, aly.analyzeCommunication(0));
		
		//Send ACK
		prc.doPart1();
		assertTrue( aly.analyzeAndSetData()<0 );
		
		//receive ACK
		assertEquals(LogAnalyzer.PART_1_REACHED, aly.analyzeCommunication(ackId));
		prc.doPart2();
		assertTrue( aly.analyzeAndSetData()<0 );
		
		//receive syn
		assertEquals(LogAnalyzer.PART_2_REACHED, aly.analyzeCommunication(ackId+1));
		prc.doPart3();
		assertTrue( aly.analyzeAndSetData()<0 );
		
		//resend syn
		assertEquals(LogAnalyzer.PART_3_REACHED, aly.analyzeCommunication(synId));
		prc.doPart4();
		assertTrue( aly.analyzeAndSetData()<0 );
		
		assertEquals(LogAnalyzer.PART_4_REACHED, aly.analyzeCommunication(synId+1));
		
		assertTrue( aly.isAckFlag() );
		assertTrue( aly.isSynFlag() );
		assertFalse( aly.isFinFlag() );
		assertFalse( aly.isRstFlag() );
	}

	/**
	 * Tests FIN, FIN+SYN, SYN
	 */
	@Test
	public void testB() {
		List<LogData> list = new ArrayList<LogData>();
		LogAnalyzer aly = new LogAnalyzer();
		aly.setList(list);
		LogProcessor prc = new LogProcessor();
		prc.setList(list);
		
		final int ackId = 100;
		final int synId = 200;
		
		prc.setAckFlag(false);
		prc.setPartner(PARTNER_A);
		prc.setAckId(ackId);
		prc.setSynId(synId);
		
		//Start
		assertEquals(LogAnalyzer.PART_0_REACHED, aly.analyzeCommunication(0));
		
		//Send ACK
		prc.doPart1();
		assertTrue( aly.analyzeAndSetData()<0 );
		
		//receive ACK
		assertEquals(LogAnalyzer.PART_1_REACHED, aly.analyzeCommunication(ackId));
		prc.doPart2();
		assertTrue( aly.analyzeAndSetData()<0 );
		
		//receive syn
		assertEquals(LogAnalyzer.PART_2_REACHED, aly.analyzeCommunication(ackId+1));
		prc.doPart3();
		assertTrue( aly.analyzeAndSetData()<0 );
		
		//resend syn
		assertEquals(LogAnalyzer.PART_3_REACHED, aly.analyzeCommunication(synId));
		prc.doPart4();
		assertTrue( aly.analyzeAndSetData()<0 );
		
		assertEquals(LogAnalyzer.PART_4_REACHED, aly.analyzeCommunication(synId+1));
		
		assertTrue( aly.isFinFlag() );
		assertTrue( aly.isSynFlag() );
		assertFalse( aly.isAckFlag() );
		assertFalse( aly.isRstFlag() );
	}

	/**
	 * Tests FIN, FIN+SYN, RST
	 */
	@Test
	public void testC() {
		List<LogData> list = new ArrayList<LogData>();
		LogAnalyzer aly = new LogAnalyzer();
		aly.setList(list);
		LogProcessor prc = new LogProcessor();
		prc.setList(list);
		
		final int ackId = 100;
		final int synId = 200;
		
		prc.setAckFlag(false);
		prc.setPartner(PARTNER_A);
		prc.setAckId(ackId);
		prc.setSynId(synId);
		
		//Start
		assertEquals(LogAnalyzer.PART_0_REACHED, aly.analyzeCommunication(0));
		
		//Send ACK
		prc.doPart1();
		assertTrue( aly.analyzeAndSetData()<0 );
		
		//receive ACK
		assertEquals(LogAnalyzer.PART_1_REACHED, aly.analyzeCommunication(ackId));
		prc.doPart2();
		assertTrue( aly.analyzeAndSetData()<0 );
		
		//receive SYN
		assertEquals(LogAnalyzer.PART_2_REACHED, aly.analyzeCommunication(ackId+1));
		prc.doPart3();
		assertTrue( aly.analyzeAndSetData()<0 );
		
		//resend RST
		assertEquals(LogAnalyzer.PART_3_REACHED, aly.analyzeCommunication(synId));
		prc.doReset4();
		assertEquals( LogAnalyzer.THE_PART_IS_ANALYZED_BUT_FAILED, aly.analyzeAndSetData() );
		
		assertEquals(LogAnalyzer.PART_4_REACHED, aly.analyzeCommunication(synId+1));
		
		assertTrue( aly.isFinFlag() );
		assertTrue( aly.isSynFlag() );
		assertFalse( aly.isAckFlag() );
		assertTrue( aly.isRstFlag() );
	}

	/**
	 * Tests ACK, ACK+RST, RST
	 */
	@Test
	public void testD() {
		List<LogData> list = new ArrayList<LogData>();
		LogAnalyzer aly = new LogAnalyzer();
		aly.setList(list);
		LogProcessor prc = new LogProcessor();
		prc.setList(list);
		
		final int ackId = 100;
		final int synId = 200;
		
		prc.setAckFlag(true);
		prc.setPartner(PARTNER_A);
		prc.setAckId(ackId);
		prc.setSynId(synId);
		
		//Start
		assertEquals(LogAnalyzer.PART_0_REACHED, aly.analyzeCommunication(0));
		
		//Send ACK
		prc.doPart1();
		assertTrue( aly.analyzeAndSetData()<0 );
		
		//receive ACK
		assertEquals(LogAnalyzer.PART_1_REACHED, aly.analyzeCommunication(ackId));
		prc.doPart2();
		assertTrue( aly.analyzeAndSetData()<0 );
		
		//receive SYN
		assertEquals(LogAnalyzer.PART_2_REACHED, aly.analyzeCommunication(ackId+1));
		prc.doReset3();
		assertEquals( LogAnalyzer.THE_PART_IS_ANALYZED_BUT_FAILED, aly.analyzeAndSetData() );
		
		//resend RST
		assertEquals(LogAnalyzer.THE_PART_IS_NOT_KNOWN, aly.analyzeCommunication(synId));
		prc.doReset4();
		assertEquals( LogAnalyzer.THE_PART_IS_NOT_KNOWN, aly.analyzeAndSetData() );
		
		assertEquals(LogAnalyzer.THE_PART_IS_NOT_KNOWN, aly.analyzeCommunication(synId+1));
		
		assertTrue( aly.isAckFlag() );
		assertFalse( aly.isSynFlag() );
		assertFalse( aly.isFinFlag() );
		assertTrue( aly.isRstFlag() );
	}

	/**
	 * Tests ACK, RST+RST, RST
	 */
	@Test
	public void testE() {
		List<LogData> list = new ArrayList<LogData>();
		LogAnalyzer aly = new LogAnalyzer();
		aly.setList(list);
		LogProcessor prc = new LogProcessor();
		prc.setList(list);
		
		final int ackId = 100;
		final int synId = 200;
		
		prc.setAckFlag(true);
		prc.setPartner(PARTNER_A);
		prc.setAckId(ackId);
		prc.setSynId(synId);
		
		//Start
		assertEquals(LogAnalyzer.PART_0_REACHED, aly.analyzeCommunication(0));
		
		//Send ACK
		prc.doPart1();
		assertTrue( aly.analyzeAndSetData()<0 );
		
		//receive ACK
		assertEquals(LogAnalyzer.PART_1_REACHED, aly.analyzeCommunication(ackId));
		prc.doReset2();
		assertEquals( LogAnalyzer.THE_PART_IS_ANALYZED_BUT_FAILED, aly.analyzeAndSetData() );
		
		//receive SYN
		assertEquals(LogAnalyzer.PART_2_REACHED, aly.analyzeCommunication(ackId+1));
		prc.doReset3();
		assertEquals( LogAnalyzer.THE_PART_IS_ANALYZED_BUT_FAILED, aly.analyzeAndSetData() );
		
		//resend RST
		assertEquals(LogAnalyzer.THE_PART_IS_NOT_KNOWN, aly.analyzeCommunication(synId));
		prc.doReset4();
		assertEquals( LogAnalyzer.THE_PART_IS_NOT_KNOWN, aly.analyzeAndSetData() );
		
		assertEquals(LogAnalyzer.THE_PART_IS_NOT_KNOWN, aly.analyzeCommunication(synId+1));
		
		assertTrue( aly.isAckFlag() );
		assertFalse( aly.isSynFlag() );
		assertFalse( aly.isFinFlag() );
		assertTrue( aly.isRstFlag() );
	}
}
