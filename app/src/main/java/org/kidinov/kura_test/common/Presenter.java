package org.kidinov.kura_test.common;

import android.content.Intent;
import android.os.Bundle;

public interface Presenter {
    void create(Bundle savedInstanceState);

    void resume();

    void pause();

    void destroy();

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void saveInstanceState(Bundle outState);
}
