package alteroute;

import com.microsoft.azure.documentdb.ConnectionPolicy;
import com.microsoft.azure.documentdb.ConsistencyLevel;
import com.microsoft.azure.documentdb.DocumentClient;

class DocumentClientFactory {
	private static final String HOST = "https://alterroutcosmosdb.documents.azure.com:443/";
    private static final String MASTER_KEY = "xnkLk9PtDLMLfkMYLR7gNjKctx79WRC6fT3iJUHsfBnQGLd6lUXXznhR3zbTxYTxo0Pfp9xtZJSKm9W0Kf4UHA==";

    private static DocumentClient documentClient = new DocumentClient(HOST, MASTER_KEY,
            ConnectionPolicy.GetDefault(), ConsistencyLevel.Session);

    public static DocumentClient getDocumentClient() {
        return documentClient;
       
    }
}