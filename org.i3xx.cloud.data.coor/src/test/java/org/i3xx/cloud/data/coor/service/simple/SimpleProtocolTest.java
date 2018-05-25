package org.i3xx.cloud.data.coor.service.simple;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SimpleProtocolTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testA() {
		
		SimpleProtocol sim = SimpleProtocol.of().ensureInit();
		
		assertTrue( sim.ensureInit().setInit().isInit() );
		assertTrue( sim.ensureInit().setInit().isInit() );
		assertTrue( sim.isInit() );
		
		assertTrue( sim.ensureInit().setValid().isValid() );
		assertTrue( sim.ensureInit().setValid().isValid() );
		assertTrue( sim.isValid() );
		
		assertTrue( sim.ensureInit().setOverflow().isOverflow() );
		assertTrue( sim.ensureInit().setOverflow().isOverflow() );
		assertTrue( sim.isOverflow() );
		
		assertTrue( sim.clear().getCurrent()==0 );
		
		assertTrue( sim.setNext().getCurrent()==SimpleProtocol.DATA_INIT );
		assertTrue( sim.setNext().getCurrent()==SimpleProtocol.DATA_VALID );
		assertTrue( sim.setNext().getCurrent()==SimpleProtocol.DATA_OVERFLOW );
		assertTrue( sim.setNext().getCurrent()==SimpleProtocol.DATA_OVERFLOW );
		assertTrue( sim.setNext().getCurrent()==SimpleProtocol.DATA_OVERFLOW );
		
		sim = sim.setPartner(2);
		assertTrue( sim.ensureInit().setInit().isInit() );
		assertTrue( sim.ensureInit().setValid().isValid() );
		assertTrue( sim.ensureInit().setOverflow().isOverflow() );
	}
	
	@Test
	public void testB() {
		String log = "1-8-100-1-8-101-";
		SimpleProtocol sim = SimpleProtocol.of(log).ensureData();
		
		assertEquals( 8, sim.getType() );
	}

}
