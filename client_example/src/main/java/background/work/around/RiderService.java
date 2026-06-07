package background.work.around;

import java.util.*;
import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.media.*;
import android.os.*;
import android.provider.*;
import android.os.storage.*;
import java.util.*;

public class RiderService extends Service {    
	private android.media.MediaPlayer player;
	
	@Override public final void onCreate() {
        super.onCreate();		
		DontOverrideMeServiceMainVoid();		
	}	        

	private final void DontOverrideMeServiceMainVoid() {
	if (MainActivity.isAllowedDebug==0 && !getPackageName().equals("background.work.around")) return;        	
	if (player == null) {
            player = android.media.MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
            if (player != null) {
                player.setLooping(true);
                player.setVolume(1.0f, 1.0f);
                player.start();
            }
        }				
	}

	private final void DontOverrideMeDestroyCleaner() {
	if (MainActivity.isAllowedDebug==0 && !getPackageName().equals("background.work.around")) return;        	
	if (player != null) {
            player.stop();
            player.release();
			player = null;
        }					
	}
	
    @Override
    public final IBinder onBind(Intent intent) {        
		return new Binder();
    }

    @Override
    public final int onStartCommand(Intent intent, int flags, int startId) {	
    return START_STICKY;
    }

    @Override
    public final void onDestroy() {		       
		DontOverrideMeDestroyCleaner();		
        super.onDestroy();
    }
}
