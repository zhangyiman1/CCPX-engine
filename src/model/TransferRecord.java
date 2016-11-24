/**
 * 
 */
package model;

/**
 * @author KEN
 *页面展示的交易记录
 */

public class TransferRecord {

	private String username;
	private String type;
	private int points;
	private String time;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "username=" + username + "&type=" + type + "&points=" + points
				+ "&time=" + time;
	}
	
	public TransferRecord(String username, String type, int points, String time) {
		super();
		this.username = username;
		this.type = type;
		this.points = points;
		this.time = time;
	}
	public TransferRecord() {
		super();
		
	}
	
	
	
}
