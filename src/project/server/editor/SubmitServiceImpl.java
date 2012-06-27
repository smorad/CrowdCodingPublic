package project.server.editor;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNProperties;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;

import project.client.editor.SubmitService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class SubmitServiceImpl extends RemoteServiceServlet implements SubmitService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String sendCode(String string) {
		// TODO Auto-generated method stub
		try{
			File f=new File("C:\\Users\\Patrick\\NewFolder\\Code.java");
			PrintWriter p=new PrintWriter(f);
			p.print(string);
			p.close();

			try{
				SVNClientManager clientManager= SVNClientManager.newInstance(new DefaultSVNOptions(), "pahdnguyen@gmail.com", "Ks7WV2Ye5Rh6");
				SVNURL url=SVNURL.parseURIDecoded("https://crowdsourced-programming.googlecode.com/svn/Code/");
				SVNURL[] d={url};
				try{
					clientManager.getCommitClient().doDelete(d, "Deleting Code");
				}
				catch(SVNException e){
				}
				finally{
					clientManager.getCommitClient().doImport(f, url, "Importing code", new SVNProperties(), false, false, SVNDepth.INFINITY);
					f.delete();
				}
			}	
			catch(SVNException e){
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}	
		return string;
	}

}
