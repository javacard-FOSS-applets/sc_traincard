package htwk.smartcard.traincard.model;

public class Progress extends IModel {

	public static final byte IDENTIFICATOR = 0x06;
	
	private byte stageID;
	private ProgressElement last;
	private ProgressElement best;
	private ProgressElement worst;
	public Progress(byte stageID, ProgressElement last, ProgressElement best, ProgressElement worst) {
		super();
		// TODO Auto-generated constructor stub
		this.stageID = stageID;
		this.last = last;
		this.best = best;
		this.worst = worst;
	}
	public ProgressElement getBest() {
		return best;
	}
	public void setBest(ProgressElement best) {
		this.best = best;
	}
	public ProgressElement getLast() {
		return last;
	}
	public void setLast(ProgressElement last) {
		this.last = last;
	}
	public byte getStageID() {
		return stageID;
	}
	public void setStageID(byte stageID) {
		this.stageID = stageID;
	}
	public ProgressElement getWorst() {
		return worst;
	}
	public void setWorst(ProgressElement worst) {
		this.worst = worst;
	}

	public byte[] toBytes(){
		byte[] ret = new byte[34];
		
		//ID
		ret[0] = IDENTIFICATOR;
		//length
		ret[1] = 0x00;
		ret[2] = 0x1f;
		//data
		ret[3] = stageID;
		byte[] pebytes = last.toBytes();
		for (short j = 4; j < 14; j++) {
			ret[j] = pebytes[j-4];
		}
		pebytes = best.toBytes();
		for (short j = 14; j < 24; j++) {
			ret[j] = pebytes[j-14];
		}
		pebytes = worst.toBytes();
		for (short j = 24; j < 34; j++) {
			ret[j] = pebytes[j-24];
		}
		
		return ret;
	}
	public static Progress fromBytes(byte[] bytes) {
		if (IDENTIFICATOR != bytes[0])
			return null;
		
		short length = (short)((bytes[1]<<8) | (bytes[2]));
		
		if (length != 31)
			return null;
		
		byte[] pebytes = new byte[10];
		for (short j = 4; j < 14; j++) {
			pebytes[j-4] = bytes[j];
		}
		ProgressElement last = ProgressElement.fromBytes(pebytes);
		for (short j = 14; j < 24; j++) {
			pebytes[j-14] = bytes[j];
		}
		ProgressElement best = ProgressElement.fromBytes(pebytes);
		for (short j = 24; j < 34; j++) {
			pebytes[j-24] = bytes[j];
		}
		ProgressElement worst = ProgressElement.fromBytes(pebytes);
		
		return new Progress(bytes[3], last, best, worst);
	}
}
