package org.i3xx.cloud.data.coor.service.simple;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SerialProtocolTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		SerialProtocol ser = SerialProtocol.of("").ensureInit();
		assertTrue( ser.size()==1 );
		assertTrue( ser.getCurrent()==100 );
		assertTrue( ser.isValue(0, 100) );
		assertTrue( ser.compareValue(0, 100)==0 );
		ser.setNext();
		assertTrue( ser.size()==2 );
		assertTrue( ser.getCurrent()==101 );
		assertTrue( ser.isValue(1, 101) );
		assertTrue( ser.compareValue(1, 100)==-1 );
		assertTrue( ser.compareValue(1, 101)==0 );
		assertTrue( ser.compareValue(1, 102)==1 );
		ser.setNext();
		assertTrue( ser.size()==3 );
		assertTrue( ser.getCurrent()==102 );
		assertTrue( ser.isValue(2, 102) );
		assertTrue( ser.compareValue(2, 101)==-1 );
		assertTrue( ser.compareValue(2, 102)==0 );
		assertTrue( ser.compareValue(2, 103)==1 );
	}
}
