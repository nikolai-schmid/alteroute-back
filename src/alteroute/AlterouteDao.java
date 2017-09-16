package alteroute;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.microsoft.azure.documentdb.ConnectionPolicy;
import com.microsoft.azure.documentdb.ConsistencyLevel;
import com.microsoft.azure.documentdb.Database;
import com.microsoft.azure.documentdb.Document;
import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.documentdb.DocumentClientException;
import com.microsoft.azure.documentdb.DocumentCollection;

public class AlterouteDao {
	
	private static Gson gson = new Gson();
	private DocumentClient documentClient = DocumentClientFactory.getDocumentClient();
    
    private static final String DATABASE_ID = "alteroute";

    private static final String COLLECTION_ID = "user";

    private static Database databaseCache;

    private static DocumentCollection collectionCache;

    private Database getUserDatabase() {
        if (databaseCache == null) {
            List<Database> databaseList = documentClient
                    .queryDatabases(
                            "SELECT * FROM root r WHERE r.id='" + DATABASE_ID
                                    + "'", null).getQueryIterable().toList();
            
            if (databaseList.size() > 0) {
                databaseCache = databaseList.get(0);
            } else {
                try {
                    Database databaseDefinition = new Database();
                    databaseDefinition.setId(DATABASE_ID);

                    databaseCache = documentClient.createDatabase(
                            databaseDefinition, null).getResource();
                } catch (DocumentClientException e) {
                    e.printStackTrace();
                }
            }
        }

        return databaseCache;
    }


    private DocumentCollection getUserCollection() {
        if (collectionCache == null) {
            List<DocumentCollection> collectionList = documentClient
                    .queryCollections(
                            getUserDatabase().getSelfLink(),
                            "SELECT * FROM root r WHERE r.id='" + COLLECTION_ID
                            + "'", null).getQueryIterable().toList();

            
            if (collectionList.size() > 0) {
                collectionCache = collectionList.get(0);
            } else {
                try {
                    DocumentCollection collectionDefinition = new DocumentCollection();
                    collectionDefinition.setId(COLLECTION_ID);

                    collectionCache = documentClient.createCollection(
                            getUserDatabase().getSelfLink(),
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