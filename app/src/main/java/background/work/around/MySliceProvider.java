package background.work.around;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.app.slice.Slice;
import android.app.slice.SliceProvider;

public class MySliceProvider extends SliceProvider {

    @Override
    public boolean onCreateSliceProvider() {
        return true;
    }
    
    @Override
    public Slice onBindSlice(Uri sliceUri) {
        String path = sliceUri.getLastPathSegment();

        if ("ping_0".equals(path)) {
            return createPingSlice("Ping 0");
        } else if ("ping_1".equals(path)) {
            return createPingSlice("Ping 1");
        } else if ("ping_2".equals(path)) {
            return createPingSlice("Ping 2");
        }
        return null;
    }

    private Slice createPingSlice(String text) {
        return new Slice.Builder(getContext(), Uri.parse("content://background.work.around.provider/" + text))
                .build();
    }
}
