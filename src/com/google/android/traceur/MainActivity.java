package com.android.traceur;
/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.app.Activity;
import android.provider.Settings;
import android.os.Bundle;
import android.os.UserManager;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
    }

    @Override
    protected void onStart() {
        super.onStart();
        boolean developerOptionsIsEnabled =
            Settings.Global.getInt(getContentResolver(),
                Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0) != 0;

        UserManager userManager = getApplicationContext()
                .getSystemService(UserManager.class);
        boolean isAdminUser = userManager.isAdminUser();
        boolean debuggingDisallowed = userManager.hasUserRestriction(
                UserManager.DISALLOW_DEBUGGING_FEATURES);

        if (!developerOptionsIsEnabled || !isAdminUser || debuggingDisallowed) {
            finish();
        }
    }
}
