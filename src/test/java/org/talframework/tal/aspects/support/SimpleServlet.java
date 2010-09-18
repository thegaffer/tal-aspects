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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.talframework.tal.aspects.annotations.HttpTrace;

/**
 * This
 *
 * @author Tom Spencer
 */
public class SimpleServlet {

    @HttpTrace
    public void doGet(HttpServletRequest request, HttpServletResponse resp) {
        String test = request.getParameter("test");
        request.setAttribute("requestTest", test);
        request.getSession().setAttribute("sessionTest", test);
    }
}
