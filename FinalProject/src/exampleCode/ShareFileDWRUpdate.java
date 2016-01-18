/*
 * Copyright 2005 Joe Walker
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
package exampleCode;

import java.security.AccessController;
import java.util.Locale;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.extend.UninitializingBean;

import com.iii.twentywork.model.bean.UsersBean;

public class ShareFileDWRUpdate  implements Runnable, UninitializingBean
{
	/**
	 * Constructor - Creates a thread pool that runs every 10 seconds.
	 */
    public ShareFileDWRUpdate()
    {
        executor = new ScheduledThreadPoolExecutor(1);
        executor.scheduleAtFixedRate(this, 1, 10, TimeUnit.SECONDS);
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    public void run()
    {
    	System.out.println("here is run");
//        updateTableDisplay();
    }

    /* (non-Javadoc)
     * @see org.directwebremoting.extend.UninitializingBean#destroy()
     */
    public void destroy()
    {
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {}
    }

    public void updateTableDisplay(int folderId)
    {
    	System.out.println("here is updateTableDisplay");
    	System.out.println(folderId);
    	

        // Get the current page.
        String page = ServerContextFactory.get().getContextPath()
                + "/reverseajax/_04peopleTable.html";
        // Create a new AttributeScriptSessionFilter which will look for an
        // attribute on the ScriptSession
        ScriptSessionFilter attributeFilter = new AttributeScriptSessionFilter(
                SCRIPT_SESSION_ATTR);
        // Update the page, filters ScriptSessions using attributeFilter. If the
        // SCRIPT_SESSION_ATTR
        // has not been set on the ScriptSession the page in question will not
        // receive updates.
        Browser.withPageFiltered(page, attributeFilter, new Runnable()
        {
            @Override
            public void run()
            {
                // Creates a new Person bean.
//                Person person = new Person(true);
//                // Creates a multi-dimensional array, containing a row and the
//                // rows column data.
//                String[][] data = { { person.getId(), person.getName(),
//                        person.getAddress(), person.getAge() + "",
//                        person.isSuperhero() + "" } };
//                // Call DWR's util which adds rows into a table. peopleTable is
//                // the id of the tbody and data contains the row/column data.
//                Util.addRows("peopleTable", data);
            }
        });
    }


    /**
     * Called from the client to add an attribute on the ScriptSession. This
     * attribute will be used so that only pages (ScriptSessions) that have set
     * this attribute will be updated.
     */
    public void addAttributeToScriptSession() {
        ScriptSession scriptSession = WebContextFactory.get().getScriptSession();
        scriptSession.setAttribute(SCRIPT_SESSION_ATTR, true);
    }


    /**
     * Called from the client to remove an attribute from the ScriptSession.
     * When called from a client that client will no longer receive updates
     * (unless addAttributeToScriptSession) is called again.
     */
    public void removeAttributeToScriptSession() {
        ScriptSession scriptSession = WebContextFactory.get().getScriptSession();
        scriptSession.removeAttribute(SCRIPT_SESSION_ATTR);
    }


    /**
     * This is the ScriptSessionFilter that will be used to filter out all
     * ScriptSessions unless they contain the SCRIPT_SESSION_ATTR attribute.
     */
    protected class AttributeScriptSessionFilter implements ScriptSessionFilter
    {
        public AttributeScriptSessionFilter(String attributeName)
        {
            this.attributeName = attributeName;
        }

        public boolean match(ScriptSession session)
        {
            Object check = session.getAttribute(attributeName);
            return (check != null && check.equals(Boolean.TRUE));
        }

        private final String attributeName;
    }

    private static String SCRIPT_SESSION_ATTR="-1" ;

    private final ScheduledThreadPoolExecutor executor;
}
