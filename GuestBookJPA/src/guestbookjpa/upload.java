package guestbookjpa;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.blobstore.BlobstoreService;

public class upload extends HttpServlet {

	private BlobstoreService 
		blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	  public void doPost(HttpServletRequest req, HttpServletResponse res)
      throws IOException {

      Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
      BlobKey blobKey = blobs.get("myFile");

      if (blobKey == null) {
          res.sendRedirect("/");
      } else {
          res.sendRedirect("/serve?blob-key=" + blobKey.getKeyString());
      }
  }
	  
}
