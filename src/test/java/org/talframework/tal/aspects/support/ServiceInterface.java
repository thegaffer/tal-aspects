/*
 * Copyright 2010 Thomas Spencer
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.talframework.tal.aspects.support;

import org.talframework.tal.aspects.annotations.Profile;

/**
 * This test interface has some profile methods that
 * we can ensure we hit.
 *
 * @author Tom Spencer
 */
public interface ServiceInterface {

    @Profile
    public boolean profileTest(String arg);
    
    public boolean nonProfileTest();
}
