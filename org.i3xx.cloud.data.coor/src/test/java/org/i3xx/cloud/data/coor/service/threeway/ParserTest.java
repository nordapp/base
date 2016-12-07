package org.i3xx.cloud.data.coor.service.threeway;

import static org.i3xx.cloud.data.coor.domain.IHandshake.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.i3xx.cloud.data.coor.service.threeway.LogData;
import org.i3xx.cloud.data.coor.service.threeway.LogParser;
import org.i3xx.cloud.data.coor.service.threeway.LogWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParserTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testA() {
		
		LogWriter w = new LogWriter();
		w.setList(new ArrayList<LogData>());
		w.log(PARTNER_A, ACK_FLAG, 100);
		w.log(PARTNER_B, ACK_FLAG, 101);
		w.log(PARTNER_B, SYN_FLAG, 200);
		w.log(PARTNER_A, SYN_FLAG, 201);
		
		assertEquals("1-1-100-2-1-101-2-2-200-1-2-201-", w.toString());
	}

	@Test
	public void testB() {
		
		LogParser p = new LogParser();
		p.setList(new ArrayList<LogData>());
		p.parse("1-1-100-2-1-101-2-2-200-1-2-201-");
		
		LogWriter w = new LogWriter();
		w.setList(p.getList());
		
		assertEquals("1-1-100-2-1-101-2-2-200-1-2-201-", w.toString());
	}

	@Test
	public void testC() {
		
		LogParser p = new LogParser();
		p.setList(new ArrayList<LogData>());
		p.parse("1-1-100-2-1-101-2-2-200-");
		
		LogWriter w = new LogWriter();
		w.setList(p.getList());
		
		assertEquals("1-1-100-2-1-101-2-2-200-", w.toString());
	}

}
