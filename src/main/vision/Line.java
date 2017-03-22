package main.vision;


public class Line implements java.io.Serializable{
import main.game.logic.ID;

public class Line {

	public int getObjectID()
	{
		return objectID;
	}

	private int objectID;
	PVector start;
	PVector end;
	ID id;

	public Line(PVector start, PVector end, ID id) {
		super();
		this.start = start;
		this.end = end;
		this.id = id;
	}
	public Line(PVector start, PVector end, ID id, int objectID) {
		super();
		this.start = start;
		this.end = end;
		this.id = id;
this.objectID = objectID;}

	public PVector getStart() {
		return start;
	}

	public void setStart(PVector start) {
		this.start = start;
	}

	public PVector getEnd() {
		return end;
	}

	public void setEnd(PVector end) {
		this.end = end;
	}

	public String toString() {
		return String.format( "[%f,%f]-[%f,%f]", start.x, start.y, end.x, end.y);
	}

	public ID getID()
	{
		return id;
	}
}
