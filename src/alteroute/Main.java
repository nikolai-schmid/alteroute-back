package alteroute;

class Main {
	public static void main(String[] args) {
		AlterouteDao ad = new AlterouteDao();
		for (User user : ad.readUserItems()) {
			System.out.println(user);
		}
	}
}