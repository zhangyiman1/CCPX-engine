package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Record;

public interface RecordDao {
	public ArrayList<Record> getReferenceRecords(int seller_id_from,int seller_id_to)  throws SQLException;
	
}
