package com.example.alekseyignatenko.rss_reader.NetWork;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;
import androidx.paging.PagedList.BoundaryCallback;

public class NewsBoundaryCallback extends BoundaryCallback {

    public NewsBoundaryCallback() {
        super();
    }

    @Override
    public void onZeroItemsLoaded() {
        super.onZeroItemsLoaded();
    }

    @Override
    public void onItemAtFrontLoaded(@NonNull Object itemAtFront) {
        super.onItemAtFrontLoaded(itemAtFront);
    }

    @Override
    public void onItemAtEndLoaded(@NonNull Object itemAtEnd) {
        super.onItemAtEndLoaded(itemAtEnd);
    }
}
