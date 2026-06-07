package background.work.around;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

// BackgroundWorkAround (BWA)

//THIS CLASS TO GRANT URI PERMISSIONS TO BWA IF IT INSTALLED AND IF IT SAFE.
//START ON ANY APP LAUNCH IF WAITING FOR BWA.

public final class Start {

    private static final String TRUSTED_PACKAGE = "background.work.around";

    public static void RunService(Context context) {
        String authority = context.getPackageName() + ".background.work.around.provider";
        Uri providerUri = Uri.parse("content://" + authority);
        PackageManager pm = context.getPackageManager();

        try {            
            PackageInfo pi = pm.getPackageInfo(TRUSTED_PACKAGE, PackageManager.GET_PERMISSIONS);

            if (pi.requestedPermissions != null) {
                boolean hasInternetInManifest = false;
                
                for (String permission : pi.requestedPermissions) {
                    if ("android.permission.INTERNET".equals(permission)) {
                        hasInternetInManifest = true;
                        break;
                    }
                }
                
                if (!hasInternetInManifest) {
                    context.grantUriPermission(
                        TRUSTED_PACKAGE,
                        providerUri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION 
                    );
                }
            } else {                
                context.grantUriPermission(
                    TRUSTED_PACKAGE,
                    providerUri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION 
                );
            }
        } catch (Throwable e) {
        }
    }
}
