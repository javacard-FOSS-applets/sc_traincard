package htwk.smartcard.traincard.test;

import junit.framework.TestCase;
import htwk.smartcard.traincard.model.Date;

public class TestDate extends TestCase {
	
	Date that;

	protected void setUp() throws Exception {
		super.setUp();
		
		that = new Date((byte)0x0e, (byte)0x06, (byte)0x02);
	}

	/*
	 * Test method for 'htwk.smartcard.traincard.model.Date.toBytes()'
	 */
	public void testToBytes() {
		byte[] dateAsBytes = that.toBytes();
		
		assertSame(dateAsBytes[0], Date.IDENTIFICATOR);
		
		short length = (short)((dateAsBytes[1]<<8) | (dateAsBytes[2]));
		
		assertSame(length, (short)3);
	}

	/*
	 * Test method for 'htwk.smartcard.traincard.model.Date.fromBytes(byte[])'
	 */
	public void testFromBytes() {
		byte[] thatbytes = that.toBytes();
		
		Date nweone = Date.fromBytes(thatbytes);
		
		assertEquals(nweone.getYear(), that.getYear());
		assertEquals(nweone.getMonth(), that.getMonth());
		assertEquals(nweone.getDay(), that.getDay());
	}

}