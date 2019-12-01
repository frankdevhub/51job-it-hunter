package frankdevhub.job.webauto.google.drive.ftp.adapter.view.ftp;

import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.AuthorizationRequest;

/**
 * <p>Title:Authorities.java</p>  
 * <p>Description: </p>  
 * <p>Copyright: Copyright (c) 2019</p>  
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>  
 * @author frankdevhub   
 * @date:2019-04-23 16:54
 */

public final class Authorities {
	public static class PWDPermission implements Authority {

		@Override
		public AuthorizationRequest authorize(AuthorizationRequest arg0) {
			return null;
		}

		@Override
		public boolean canAuthorize(AuthorizationRequest arg0) {
			return false;
		}
	}

	public static class CWDPermission implements Authority {

		@Override
		public AuthorizationRequest authorize(AuthorizationRequest arg0) {
			return null;
		}

		@Override
		public boolean canAuthorize(AuthorizationRequest arg0) {
			return false;
		}
	}

	public static class ListPermission implements Authority {

		@Override
		public AuthorizationRequest authorize(AuthorizationRequest arg0) {
			return null;
		}

		@Override
		public boolean canAuthorize(AuthorizationRequest arg0) {
			return false;
		}
	}

	public static class DeletePermission implements Authority {

		@Override
		public AuthorizationRequest authorize(AuthorizationRequest arg0) {
			return null;
		}

		@Override
		public boolean canAuthorize(AuthorizationRequest arg0) {
			return false;
		}
	}

	public static class RetrievePermission implements Authority {

		@Override
		public AuthorizationRequest authorize(AuthorizationRequest arg0) {
			return null;
		}

		@Override
		public boolean canAuthorize(AuthorizationRequest arg0) {
			return false;
		}
	}

	public static class RemoveDirPermission implements Authority {

		@Override
		public AuthorizationRequest authorize(AuthorizationRequest arg0) {
			return null;
		}

		@Override
		public boolean canAuthorize(AuthorizationRequest arg0) {
			return false;
		}
	}

	public static class MakeDirPermission implements Authority {

		@Override
		public AuthorizationRequest authorize(AuthorizationRequest arg0) {
			return null;
		}

		@Override
		public boolean canAuthorize(AuthorizationRequest arg0) {
			return false;
		}
	}

	public static class AppendPermission implements Authority {

		@Override
		public AuthorizationRequest authorize(AuthorizationRequest arg0) {
			return null;
		}

		@Override
		public boolean canAuthorize(AuthorizationRequest arg0) {
			return false;
		}
	}

	public static class StorePermission implements Authority {

		@Override
		public AuthorizationRequest authorize(AuthorizationRequest arg0) {
			return null;
		}

		@Override
		public boolean canAuthorize(AuthorizationRequest arg0) {
			return false;
		}
	}

	public static class RenameToPermission implements Authority {

		@Override
		public AuthorizationRequest authorize(AuthorizationRequest arg0) {
			return null;
		}

		@Override
		public boolean canAuthorize(AuthorizationRequest arg0) {
			return false;
		}
	}
}
