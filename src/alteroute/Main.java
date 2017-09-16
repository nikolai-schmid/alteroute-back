package alteroute;

import bo.Game;
import bo.GameParticipation;
import bo.Image;
import bo.User;
import dao.GameDao;
import dao.ImageDao;
import dao.UserDao;

class Main {
	public static void main(String[] args) {
		UserDao userDao = new UserDao();
		for (User user : userDao.readUserItems()) {
			System.out.println(user);
		}
		
		GameDao gameDao = new GameDao();
		for (Game game : gameDao.readGameItems()) {
			System.out.println(game);
		}
		
		for (GameParticipation gp : gameDao.readGameParticipationItems()) {
			System.out.println(gp);
		}
		
		ImageDao imageDao = new ImageDao();
		for (Image image : imageDao.readImagetems()) {
			System.out.println(image);
		}
	}
}