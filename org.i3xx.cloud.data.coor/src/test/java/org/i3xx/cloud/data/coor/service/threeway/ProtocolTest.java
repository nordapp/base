package org.i3xx.cloud.data.coor.service.threeway;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.i3xx.cloud.data.coor.domain.IHandshake;
import org.i3xx.cloud.data.coor.domain.ObjectHandshake;
import org.i3xx.cloud.data.coor.service.threeway.LogAnalyzer;
import org.i3xx.cloud.data.coor.service.threeway.LogData;
import org.i3xx.cloud.data.coor.service.threeway.LogParser;
import org.i3xx.cloud.data.coor.service.threeway.LogProcessor;
import org.i3xx.cloud.data.coor.service.threeway.RunProtocol;
import org.i3xx.cloud.data.object.util.UuidTool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProtocolTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Full example of the protocol implementation
	 * to show complexity and function
	 */
	@Test
	public void testA() {
		
		//The single object
		ObjectHandshake h = new ObjectHandshake();
		h.setUuid( UuidTool.getOne() );
		
		final int ackId = 100;
		final int synId = 200;
		
		//The protocol side A
		List<LogData> listA = new ArrayList<LogData>();
		LogParser logA = new LogParser();
		logA.setList(listA);
		LogProcessor prcA = new LogProcessor(h);
		LogAnalyzer alyA = new LogAnalyzer();
		alyA.setList(listA);
		alyA.setDryPartner(true);
		prcA.setList(listA);
		prcA.setPartner(ObjectHandshake.PARTNER_A);
		prcA.setAckId(ackId);
		prcA.setSynId(synId);
		
		//The protocol side B
		List<LogData> listB = new ArrayList<LogData>();
		LogParser logB = new LogParser();
		logB.setList(listB);
		LogProcessor prcB = new LogProcessor(h);
		LogAnalyzer alyB = new LogAnalyzer();
		alyB.setList(listB);
		alyB.setDryPartner(true);
		prcB.setList(listB);
		prcB.setPartner(ObjectHandshake.PARTNER_B);
		prcB.setAckId(ackId);
		prcB.setSynId(synId);
		
		//Start
		assertEquals(LogAnalyzer.PART_0_REACHED, alyA.analyzeCommunication(0));
		assertEquals(LogAnalyzer.PART_0_REACHED, alyB.analyzeCommunication(0));
		
		//A sends ACK
		prcA.doPart1();
		assertEquals(LogAnalyzer.PART_1_ANALYZED, alyA.analyzeAndSetData());
		
		//B answers ACK + SYN
		logB.parse(h.getLog());
		assertEquals(LogAnalyzer.PART_1_ANALYZED, alyB.analyzeAndSetData());
		assertEquals(LogAnalyzer.PART_1_REACHED, alyB.analyzeCommunication(ackId));
		
		prcB.doPart2();
		
		//B answers ACK + SYN
		logB.parse(h.getLog());
		assertEquals(LogAnalyzer.PART_2_ANALYZED, alyB.analyzeAndSetData() );
		assertEquals(LogAnalyzer.PART_2_REACHED, alyB.analyzeCommunication(ackId+1));
		
		prcB.doPart3();
		
		logB.parse(h.getLog());
		assertEquals(LogAnalyzer.PART_3_ANALYZED, alyB.analyzeAndSetData() );
		
		//A answers SYN
		logA.parse(h.getLog());
		assertEquals(LogAnalyzer.PART_3_ANALYZED, alyA.analyzeAndSetData() );
		assertEquals(LogAnalyzer.PART_3_REACHED, alyA.analyzeCommunication(synId));
		
		prcA.doPart4();
		
		logA.parse(h.getLog());
		assertEquals(LogAnalyzer.PART_4_ANALYZED, alyA.analyzeAndSetData() );
		assertEquals(LogAnalyzer.PART_4_REACHED, alyA.analyzeCommunication(synId+1));
		
		assertTrue( alyA.isAckFlag() );
		assertTrue( alyA.isSynFlag() );
		assertFalse( alyA.isFinFlag() );
		assertFalse( alyA.isRstFlag() );
		
		assertTrue( alyB.isAckFlag() );
		assertTrue( alyB.isSynFlag() );
		assertFalse( alyB.isFinFlag() );
		assertFalse( alyB.isRstFlag() );
	}

	/**
	 * Example of the protocol using a single RunProtocol object.
	 */
	@Test
	public void testB() {
		
		ObjectHandshake h = new ObjectHandshake();
		h.setUuid( UuidTool.getOne() );
		
		RunProtocol run = new RunProtocol(h, IHandshake.PARTNER_A, 100, 200, false, true);
		
		assertTrue(run.beginA());
		assertTrue(run.commitA());
		
		assertTrue(run.beginB());
		assertTrue(run.commitB());
	}

	/**
	 * Example of the protocol using two RunProtocol objects.
	 */
	@Test
	public void testC() {
		
		ObjectHandshake h = new ObjectHandshake();
		h.setUuid( UuidTool.getOne() );
		
		RunProtocol run = null;
		
		run = new RunProtocol(h, IHandshake.PARTNER_A, 100, 200, true, true);
		assertTrue(run.beginA());
		
		run = new RunProtocol(h, IHandshake.PARTNER_B, 100, 200, true, true);
		assertTrue(run.commitA());
		
		run = new RunProtocol(h, IHandshake.PARTNER_B, 100, 200, true, true);
		assertTrue(run.beginB());
		
		run = new RunProtocol(h, IHandshake.PARTNER_A, 100, 200, true, true);
		assertTrue(run.commitB());
		
		assertEquals(h.getLog(), "1-1-100-2-1-101-2-2-200-1-2-201-");
	}

	/**
	 * Example of the protocol using two RunProtocol objects.
	 */
	@Test
	public void testD() {
		
		ObjectHandshake h = new ObjectHandshake();
		h.setUuid( UuidTool.getOne() );
		
		RunProtocol run = null;
		
		run = new RunProtocol(h, IHandshake.PARTNER_A);
		assertTrue(run.beginA());
		
		run = new RunProtocol(h, IHandshake.PARTNER_A);
		assertTrue(run.commitA());
		
		run = new RunProtocol(h, IHandshake.PARTNER_B);
		assertTrue(run.beginB());
		
		run = new RunProtocol(h, IHandshake.PARTNER_B);
		assertTrue(run.commitB());
		
		assertEquals(h.getLog(), "1-1-100-1-1-101-2-2-200-2-2-201-");
	}

	/**
	 * Example using reset
	 */
	@Test
	public void testE() {
		
		ObjectHandshake h = new ObjectHandshake();
		h.setUuid( UuidTool.getOne() );
		
		RunProtocol run = null;
		
		run = new RunProtocol(h, IHandshake.PARTNER_A, 100, 200, true, true);
		assertTrue(run.beginA());
		
		run = new RunProtocol(h, IHandshake.PARTNER_B, 100, 200, true, true);
		assertTrue(run.commitA());
		
		run = new RunProtocol(h, IHandshake.PARTNER_B, 100, 200, true, true);
		assertTrue(run.beginB());
		
		run = new RunProtocol(h, IHandshake.PARTNER_A, 100, 200, true, true);
		run.reset();
		
		run = new RunProtocol(h, IHandshake.PARTNER_A, 100, 200, true, true);
		assertFalse(run.commitB());
		
		run = new RunProtocol(h, IHandshake.PARTNER_A, 100, 200, true, true);
		assertTrue( run.getCurrentAnalyzer().isRstFlag() );
		
		assertEquals(h.getLog(), "1-1-100-2-1-101-2-2-200-1-4-201-");
	}

	/**
	 * Example with the FIN flag
	 */
	@Test
	public void testF() {
		
		ObjectHandshake h = new ObjectHandshake();
		h.setUuid( UuidTool.getOne() );
		
		RunProtocol run = null;
		
		run = new RunProtocol(h, IHandshake.PARTNER_A, 100, 200, true, false);
		assertTrue(run.beginA());
		
		run = new RunProtocol(h, IHandshake.PARTNER_B, 100, 200, true, false);
		assertTrue(run.commitA());
		
		run = new RunProtocol(h, IHandshake.PARTNER_B, 100, 200, true, false);
		assertTrue(run.beginB());
		
		run = new RunProtocol(h, IHandshake.PARTNER_A, 100, 200, true, false);
		assertTrue(run.commitB());
		
		assertEquals(h.getLog(), "1-8-100-2-8-101-2-2-200-1-2-201-");
	}

	/**
	 * Example of the protocol using two RunProtocol objects and the RST flag.
	 */
	@Test
	public void testG() {
		
		ObjectHandshake h = new ObjectHandshake();
		h.setUuid( UuidTool.getOne() );
		
		RunProtocol run = null;
		
		run = new RunProtocol(h, IHandshake.PARTNER_A);
		assertTrue(run.beginA());
		
		run = new RunProtocol(h, IHandshake.PARTNER_A);
		assertTrue(run.commitA());
		
		run.reset();
		
		run = new RunProtocol(h, IHandshake.PARTNER_B);
		assertFalse(run.beginB());
		
		run = new RunProtocol(h, IHandshake.PARTNER_B);
		assertFalse(run.commitB());
		
		//analyze the reset
		LogAnalyzer aly = run.getCurrentAnalyzer();
		//The value, the reset occurs
		assertEquals( 200, aly.getCurrentPartValue() );
		//The part is not available
		assertEquals( LogAnalyzer.THE_PART_IS_NOT_KNOWN, aly.getPart() );
		
		assertFalse( aly.isAfterPart0() );
		assertFalse( aly.isAfterPart1() );
		//The parts 0 and 1 run properly
		assertTrue( aly.isAfterPart2() );
		//The part 3 is not reached, because of the reset
		assertFalse( aly.isAfterPart3() );
		
		//There is no part
		assertFalse( aly.isPart1() );
		assertFalse( aly.isPart2() );
		assertFalse( aly.isPart3() );
		assertFalse( aly.isPart4() );
		
		//There are 3 entries in the list.
		assertEquals( 3, aly.getList().size() );
		//The last entry has the RST_FLAG set
		assertEquals( IHandshake.RST_FLAG, aly.getCurrentPartType() );
		
		assertEquals(h.getLog(), "1-1-100-1-1-101-1-4-200-");
	}

}
