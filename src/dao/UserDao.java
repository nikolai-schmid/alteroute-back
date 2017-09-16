package dao;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.microsoft.azure.documentdb.Database;
import com.microsoft.azure.documentdb.Document;
import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.documentdb.DocumentClientException;
import com.microsoft.azure.documentdb.DocumentCollection;
import com.microsoft.azure.documentdb.Resource;

import bo.Game;
import bo.User;

public class UserDao {
	
	private static Gson gson = new Gson();
	private DocumentClient documentClient = dao.DocumentClientFactory.getDocumentClient();
    private static final String COLLECTION_ID = "user";
    private static DocumentCollection collectionCache;
    
    private DocumentCollection getUserCollection() {
        if (collectionCache == null) {
            List<DocumentCollection> collectionList = documentClient
                    .queryCollections(
                            AlterouteDao.getAlterouteDatabase().getSelfLink(),
                            "SELECT * FROM root r WHERE r.id='" + COLLECTION_ID
                            + "'", null).getQueryIterable().toList();

            
            if (collectionList.size() > 0) {
                collectionCache = collectionList.get(0);
            } else {
                try {
                    DocumentCollection collectionDefinition = new DocumentCollection();
                    collectionDefinition.setId(COLLECTION_ID);

                    collectionCache = documentClient.createCollection(
                    		AlterouteDao.getAlterouteDatabase().getSelfLink(),
                            collectionDefinition, null).getResource();
                } catch (DocumentClientException e) {
                    e.printStackTrace();
                }
            }
        }

        return collectionCache;
    }
    
    public List<User> readUserItems() {
        List<User> users = new ArrayList<User>();

        List<Document> documentList = documentClient
                .queryDocuments(getUserCollection().getSelfLink(),
                        "SELECT * FROM root",
                        null).getQueryIterable().toList();

        for (Document user : documentList) {
            users.add(gson.fromJson(user.toString(),
                    User.class));
        }
        
        return users;
    }
}