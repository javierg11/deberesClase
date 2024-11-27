package hiloPrimerCoso;

public class secundario implements Runnable {

	@Override
	public void run() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i =0;i<10;i++)
		System.out.print("2: "+i+"\n");
		
	}
	public void hello() {
		
	}

}
