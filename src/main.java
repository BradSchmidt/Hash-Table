public class main {
	
		public static void main(String[] args)
		{
			Table practice = new Table(32);  //capacity must be a power of 2 for quadratic probing
			
			practice.put("my", "name");
			practice.put("computer", "science");
			practice.put("daffy", "duck");
			practice.put("west", "east");
			practice.put("sierra", "nevada");
			practice.put("west", "side");  //replacing object with identical key
			practice.put("frank", "reynolds");
			practice.remove("daffy");
			
			System.out.println(practice.get("my"));
			System.out.println(practice.get("computer"));
			System.out.println(practice.get("daffy"));
			System.out.println(practice.get("west"));
			System.out.println(practice.get("sierra"));
			System.out.println(practice.get("frank"));
			
			practice.size();	
		}
}
