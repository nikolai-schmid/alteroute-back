package dao;

import java.util.List;

import com.microsoft.azure.documentdb.Database;
import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.documentdb.DocumentClientException;
import com.microsoft.azure.documentdb.DocumentCollection;

public class AlterouteDao {
	private static final String DATABASE_ID = "alteroute";
    private static Database databaseCache;
    private static DocumentCollection collectionCache;
    private static DocumentClient documentClient = dao.DocumentClientFactory.getDocumentClient();
    	
    static Database getAlterouteDatabase() {
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
    
    static DocumentCollection getCollection(String collectionId) {
        if (collectionCache == null) {
            List<DocumentCollection> collectionList = documentClient
                    .queryCollections(
                            AlterouteDao.getAlterouteDatabase().getSelfLink(),
                            "SELECT * FROM root r WHERE r.id='" + collectionId
                            + "'", null).getQueryIterable().toList();

            
            if (collectionList.size() > 0) {
                collectionCache = collectionList.get(0);
            } else {
                try {
                    DocumentCollection collectionDefinition = new DocumentCollection();
                    collectionDefinition.setId(collectionId);

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
}