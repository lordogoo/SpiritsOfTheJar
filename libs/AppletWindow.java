import java.applet.*;
import java.awt.event.*;
import java.net.URL;
import java.io.File;

import java.util.Map;
import java.util.Collections;

/**
<B>AppletWindow</B> provides a window that can display a working applet
inside of itself.<P>

<I>Known uses:</I>
By running an applet this way, you can use BlueJ's
symbolic debugger to debug your code.<P>

<I>Hints for use:</I> See AppletDemo for an example of how to use AppletWindow.<P>

@version 1.1
@author Michael Trigoboff
@author Davin McCall
 */
public class AppletWindow
  extends GWindow implements AppletStub
{
    final private Applet      applet;
    
    Map params;

    public AppletWindow(String title, int width, int height, Applet appletArg)
    {
        super(title, width, height, appletArg);
        applet = appletArg;
        params = Collections.EMPTY_MAP;
        runApplet();
    }

    public AppletWindow(String title, int width, int height, Applet appletArg, Map params)
    {
        super(title, width, height, appletArg);
        applet = appletArg;
        this.params = params;
        runApplet();
    }
    
    private void runApplet()
    {
        // start up the applet
        applet.setStub(this);
        applet.init();
        applet.start();

        // tell the applet to lay itself out
        validate();

        // set up actions to take when window closes
        addWindowListener(new WindowAdapter()
        {
            public void windowClosed (WindowEvent e)
            {
                // shut down the applet
                applet.stop();
                applet.destroy();
            }
        });
    }
  
    public void appletResize(int width, int height)
    {
        setSize(width,height);
    }
    
    public AppletContext getAppletContext()
    {
        return null;
    }
    
    public String getParameter(String name)
    {
        return (String) params.get(name);
    }
    
    public URL getCodeBase()
    {
        try {
            return new File(".").toURI().toURL();
        }
        catch (java.net.MalformedURLException mue) {
            return null;
        }
    }
    
    public URL getDocumentBase()
    {
        return null;
    }
}
