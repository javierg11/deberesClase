package hiloPrimerCoso;

public class main {

	public static void main(String[] args) {

		Thread th = new Thread(new secundario());
		th.start();
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hello2();
		for(int i =0;i<10;i++)
			System.out.print("1: "+i+"\n");
	}

	private static void hello2() {
		
		
	}

}
