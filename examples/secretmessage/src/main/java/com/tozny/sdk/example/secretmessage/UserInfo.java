/*
 * UserInfo.java
 *
 * Copyright (C) 2016, Tozny, LLC.
 * All Rights Reserved.
 *
 * Released under the Apache license. See the file "LICENSE"
 * for more information.
 */

package com.tozny.sdk.example.secretmessage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Information about a logged in user.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
class UserInfo {
    public String user_id;
    public String session_id;
}
