package dao;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.microsoft.azure.documentdb.Document;
import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.documentdb.DocumentCollection;

import bo.Image;

public class ImageDao {
	
	private static Gson gson = new Gson();
	private DocumentClient documentClient = dao.DocumentClientFactory.getDocumentClient();
    private static final String COLLECTION_ID_IMAGE = "image";
    private static DocumentCollection collectionCache;
    
    public List<Image> readImagetems() {
        List<Image> images = new ArrayList<Image>();

        List<Document> documentList = documentClient
                .queryDocuments(AlterouteDao.getCollection(COLLECTION_ID_IMAGE).getSelfLink(),
                        "SELECT * FROM root",
                        null).getQueryIterable().toList();

        for (Document game : documentList) {
            images.add(gson.fromJson(game.toString(),
        			Image.class));
        }
        
        return images;
    }
}