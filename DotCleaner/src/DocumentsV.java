/**
 * 
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Joaquin Gayoso Cabada
 *
 */
public class DocumentsV {
	
	private ArrayList<Long> Att;
	private Long Id;

	@SuppressWarnings("unused")
	private DocumentsV() {
		Att=new ArrayList<Long>();
		this.Id=0l;
	}
	
	public DocumentsV(Long Id) {
		Att=new ArrayList<Long>();
		this.Id=Id;
		
	}
	
	public ArrayList<Long> getAtt() {
		return Att;
	}

	public void setAtt(ArrayList<Long> att) {
		Att = att;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof DocumentsV)
			{
			HashSet<Long> oL=new HashSet<>();
			HashSet<Long> oThis=new HashSet<>();
			for (Long long1 : Att) 
				oThis.add(long1);
			
			for (Long long1 : ((DocumentsV)o).Att) 
				oL.add(long1);
			
			
			if (oL.size()!=oThis.size())
				return false;
			
			
			for (Long long1 : oThis) {
				if (!oL.contains(long1))
					return false;
			}
			
			return true;
			
			}
		else
			return false;
	}
	
	
	
	@Override
	public String toString() {
		return "{ID:"+Id+","+"Att:"+Arrays.toString(Att.toArray())+"}";
	}

}
