

package com.beta.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.beta.exception.VendorMgmtException;
import com.beta.service.ConfigUtil;
import com.beta.service.SaveDocumentService;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.CreateFolderErrorException;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.FolderMetadata;
import com.dropbox.core.v2.files.GetMetadataErrorException;
import com.dropbox.core.v2.files.Metadata;

@Service
@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, rollbackFor = VendorMgmtException.class)
public class DropboxSaveDocumentServiceImpl implements SaveDocumentService {

	private static final String ACCESS_TOKEN = ConfigUtil.getKey("DROPBOX_ACCESS_TOKEN");
	
	DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");

	DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
    
	@Override
	public void createFolder(String folderName) throws DbxException {
		
		 try {		 
	            FolderMetadata folder = client.files().createFolder(folderName);
	        } catch (CreateFolderErrorException err) {
	            if (err.errorValue.isPath() && err.errorValue.getPathValue().isConflict()) {
	            	throw new VendorMgmtException("Error in creating folder as something exists in the path", err);
	            } else {
	            	throw new VendorMgmtException("Error in creating folder", err);
	            }
	        } catch (Exception err) {
	        	throw new VendorMgmtException("Error in creating folder", err);
	        }
	}

	@Override
	public String uploadFile(String path, String foldername) {
		
		 try {
	            InputStream in = new FileInputStream(path);
	            FileMetadata metadata = client.files().uploadBuilder(foldername).uploadAndFinish(in);
	            String url = client.sharing().createSharedLinkWithSettings(foldername).getUrl();
	            return url;
	        }
	        catch (FileNotFoundException fne)
	        {
	        	throw new VendorMgmtException("Error in uploading file to dropbox", fne);
	        }
	        catch (IOException ioe)
	        {
	        	throw new VendorMgmtException("Error in uploading file to dropbox", ioe);
	        }
	        catch (DbxException dbxe)
	        {
	        	throw new VendorMgmtException("Error in uploading file to dropbox", dbxe);
	        }

	}

	@Override
	public void readAndDownloadFile(String foldername, String filename) {
		 
		 try
	        {
			 //output file for download --> storage location on local system to download file
	            FileOutputStream downloadFile = new FileOutputStream(filename);
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
	        	throw new VendorMgmtException("Error in reading file from dropbox", e);
	        }
	        catch (IOException e)
	        {
	        	throw new VendorMgmtException("Error in reading file from dropbox", e);
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
            throw new VendorMgmtException("Error in deleting file from dropbox", dbxe);
        }

	}

	@Override
	public boolean checkFileExists(String foldername) {
		try {
            client.files().getMetadata(foldername);
            return true;
        } catch (GetMetadataErrorException e){
            if (e.errorValue.isPath() && e.errorValue.getPathValue().isNotFound()) {
                return false;
            } else {
            	throw new VendorMgmtException("Error checking if file exists", e);
            }
        } catch (DbxException e) {
        	throw new VendorMgmtException("Error checking if file exists", e);
        } 
	}

}
