

package com.beta.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.beta.exception.VendorMgmtException;
import com.beta.service.SaveDocumentService;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxHost;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.CreateFolderErrorException;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.FolderMetadata;
import com.dropbox.core.v2.files.Metadata;

@Service
@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, rollbackFor = VendorMgmtException.class)
public class DropboxSaveDocumentServiceImpl implements SaveDocumentService {

	private static final String ACCESS_TOKEN = "I66zWXoReFAAAAAAAAAAGkStTcEm2olgXLiCTYGvzhKq87QBc0bkO9Sl9GivDNMM";
	
	DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");

	DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
    
	@Override
	public void createFolder(String folderName) throws DbxException {
		
		 try {
			 
	            FolderMetadata folder = client.files().createFolder(folderName);
	            System.out.println(folder.getName());
	        } catch (CreateFolderErrorException err) {
	            if (err.errorValue.isPath() && err.errorValue.getPathValue().isConflict()) {
	                System.out.println("Something already exists at the path.");
	            } else {
	                System.out.print("Some other CreateFolderErrorException occurred...");
	                System.out.print(err.toString());
	            }
	        } catch (Exception err) {
	            System.out.print("Some other Exception occurred...");
	            System.out.print(err.toString());
	        }
	}

	@Override
	public void uploadFile(String path, String foldername) {
		
		 try {
		
	            InputStream in = new FileInputStream(path);
	            FileMetadata metadata = client.files().uploadBuilder(foldername).uploadAndFinish(in);
	        }
	        catch (FileNotFoundException fne)
	        {
	            fne.printStackTrace();
	        }
	        catch (IOException ioe)
	        {
	            ioe.printStackTrace();
	        }
	        catch (DbxException dbxe)
	        {
	            dbxe.printStackTrace();
	        }

	}

	@Override
	public void readFile(String foldername, String filename) {
		 
		 try
	        {
			 //output file for download --> storage location on local system to download file
	            FileOutputStream downloadFile = new FileOutputStream("C:\\Users\\645686\\Desktop\\DropBox\\" + filename);
	            try {
	                FileMetadata metadata = client.files().downloadBuilder(foldername).download(downloadFile);
	                } finally
	                {
	                    downloadFile.close();
	                }
	        }
	        //exception handled
	        catch (DbxException e)
	        {
	            e.printStackTrace();
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }

	}

	@Override
	public void deleteFile(String path) {
		try
        {
            Metadata metadata = client.files().delete(path);
        }
        catch (DbxException dbxe)
        {
            dbxe.printStackTrace();
        }

	}

}
