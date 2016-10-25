package org.i3xx.cloud.data.base.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

public class SerializationBytesTest {
	
	private static Data data;
	
	private static class Data {
		private ChildRelationMgr mgr;
		private byte[] buffer;
		private Set<String> set;
	}
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		
		data = new Data();
		
		data.mgr = new ChildRelationMgr(false, true, false);
		//data.mgr.initData();
		
		for(int i=0;i<10000;i++) {
			data.mgr.getData().add( UUID.randomUUID().toString() );
		}
	}

	@Test
	public void testA() throws IOException {
		data.buffer = data.mgr.serialize(data.mgr.getData());
	}

	@Test
	public void testB() throws IOException, ClassNotFoundException {
		data.set = data.mgr.deserialize(data.buffer);
	}
	
	@Test
	public void testC() {
		String[] arrA = data.set.toArray(new String[0]);
		String[] arrB = data.mgr.getData().toArray(new String[0]);
		
		Arrays.sort(arrA);
		Arrays.sort(arrB);
		
		assertArrayEquals( arrA, arrB );
	}

}
