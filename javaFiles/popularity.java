import java.util.BitSet; 

public class popularity {
	
	BitSet zeroBitVector = new BitSet(8); 
	BitSet oneBitVector = new BitSet(8);
	
	
	popularity()
	{
	zeroBitVector.clear(0,7);
	oneBitVector.set(0,7);
	}
	
	BitSet updatePopularity(BitSet input,boolean updateValue)
	{
		//Right Shift Input
		
		if(updateValue)
		{
		//Increase popularity
		}
		else
		{
		//Decrease popularity
		}
		
		return(input);
	}
}
