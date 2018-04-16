package org.i3xx.cloud.data.coor.service.simple;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SimpleExtProtocolTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testA() {
		
		SimpleExtProtocol ext = SimpleExtProtocol.of();
		ext.setInit().setNext().setNext();
		assertEquals( ext.toString(), "1-7-100-1-7-101-1-7-105-" );
		
		ext.ensureResetAndInit();
		assertEquals( ext.toString(), "1-7-100-1-7-101-1-7-105-1-7-100-" );
		ext.ensureResetAndInit();
		assertEquals( ext.toString(), "1-7-100-1-7-101-1-7-105-1-7-100-" );
		ext.ensureResetAndInit();
		assertEquals( ext.toString(), "1-7-100-1-7-101-1-7-105-1-7-100-" );
		
		ext.ensureResetAndInit().setNext().setNext();
		assertEquals( ext.toString(), "1-7-100-1-7-101-1-7-105-1-7-100-1-7-101-1-7-105-" );
		ext.ensureResetAndInit().setNext().setNext();
		assertEquals( ext.toString(), "1-7-100-1-7-101-1-7-105-1-7-100-1-7-101-1-7-105-" );
		ext.ensureResetAndInit().setNext().setNext();
		assertEquals( ext.toString(), "1-7-100-1-7-101-1-7-105-1-7-100-1-7-101-1-7-105-" );
	}

	@Test
	public void testB() {
		
		// Has incomplete protocol
		SimpleExtProtocol ext = SimpleExtProtocol.of("1-7-101-1-7-105-");
		
		// Has incomplete protocol at the beginning
		ext.ensureResetAndInit().setNext().setNext();
		assertEquals( ext.toString(), "1-7-101-1-7-105-1-7-100-1-7-101-1-7-105-" );
		
		ext.ensureResetAndInit().setNext().setNext();
		assertEquals( ext.toString(), "1-7-100-1-7-101-1-7-105-1-7-100-1-7-101-1-7-105-" );
		ext.ensureResetAndInit().setNext().setNext();
		assertEquals( ext.toString(), "1-7-100-1-7-101-1-7-105-1-7-100-1-7-101-1-7-105-" );
		ext.ensureResetAndInit().setNext().setNext();
		assertEquals( ext.toString(), "1-7-100-1-7-101-1-7-105-1-7-100-1-7-101-1-7-105-" );
	}

}
