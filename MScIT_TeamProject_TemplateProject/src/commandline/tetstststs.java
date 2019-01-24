package commandline;

public class tetstststs {

	public static void main(String[] args) {
		Model model = new Model();
		model.readContent();
//		for (int i = 0; i < model.cardHeader.length; i++) {
//			System.out.println(model.cardHeader[i]);
//		}
//		String mystring = "hello";
//		int[] inty = {1,2,3,6,7};
//		Card c = new Card(mystring, inty,model);
		System.out.println(model.cardCon.get(0));

	}

}
