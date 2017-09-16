package dao;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.microsoft.azure.documentdb.Database;
import com.microsoft.azure.documentdb.Document;
import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.documentdb.DocumentClientException;
import com.microsoft.azure.documentdb.DocumentCollection;

import bo.Game;
import bo.GameParticipation;

public class GameDao {
	
	private static Gson gson = new Gson();
	private DocumentClient documentClient = dao.DocumentClientFactory.getDocumentClient();
    private static final String COLLECTION_ID_GAME = "game";
    private static final String CONNECTION_ID_GAME_PARTICIPATION = "game_participation";
    private static DocumentCollection collectionCache;
    
    public List<Game> readGameItems() {
        List<Game> games = new ArrayList<Game>();

        List<Document> documentList = documentClient
                .queryDocuments(AlterouteDao.getCollection(COLLECTION_ID_GAME).getSelfLink(),
                        "SELECT * FROM root",
                        null).getQueryIterable().toList();

        for (Document game : documentList) {
            games.add(gson.fromJson(game.toString(),
        			Game.class));
        }
        
        return games;
    }
    
    public List<GameParticipation> readGameParticipationItems() {
        List<GameParticipation> gameParticipations = new ArrayList<bo.GameParticipation>();

        List<Document> documentList = documentClient
                .queryDocuments(AlterouteDao.getCollection(COLLECTION_ID_GAME).getSelfLink(),
                        "SELECT * FROM root",
                        null).getQueryIterable().toList();

        for (Document game : documentList) {
            gameParticipations.add(gson.fromJson(game.toString(),
        			GameParticipation.class));
        }
        
        return gameParticipations;    	
    }
}