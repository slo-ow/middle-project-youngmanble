// Stub class generated by rmic, do not edit.
// Contents subject to change without notice.

package home.chat.server;

public final class ChatServer_Stub
    extends java.rmi.server.RemoteStub
    implements home.chat.Chat, java.rmi.Remote
{
    private static final long serialVersionUID = 2;
    
    private static java.lang.reflect.Method $method_setClient_0;
    private static java.lang.reflect.Method $method_setMessage_1;
    private static java.lang.reflect.Method $method_update_2;
    
    static {
	try {
	    $method_setClient_0 = home.chat.Chat.class.getMethod("setClient", new java.lang.Class[] {home.chat.ChatMessage.class, java.lang.String.class});
	    $method_setMessage_1 = home.chat.Chat.class.getMethod("setMessage", new java.lang.Class[] {java.lang.String.class, java.lang.String.class});
	    $method_update_2 = home.chat.Chat.class.getMethod("update", new java.lang.Class[] {java.lang.String.class});
	} catch (java.lang.NoSuchMethodException e) {
	    throw new java.lang.NoSuchMethodError(
		"stub class initialization failed");
	}
    }
    
    // constructors
    public ChatServer_Stub(java.rmi.server.RemoteRef ref) {
	super(ref);
    }
    
    // methods from remote interfaces
    
    // implementation of setClient(ChatMessage, String)
    public void setClient(home.chat.ChatMessage $param_ChatMessage_1, java.lang.String $param_String_2)
	throws java.rmi.RemoteException
    {
	try {
	    ref.invoke(this, $method_setClient_0, new java.lang.Object[] {$param_ChatMessage_1, $param_String_2}, -5608789989886006047L);
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
    
    // implementation of setMessage(String, String)
    public void setMessage(java.lang.String $param_String_1, java.lang.String $param_String_2)
	throws java.rmi.RemoteException
    {
	try {
	    ref.invoke(this, $method_setMessage_1, new java.lang.Object[] {$param_String_1, $param_String_2}, 3352804270881037342L);
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
    
    // implementation of update(String)
    public void update(java.lang.String $param_String_1)
	throws java.rmi.RemoteException
    {
	try {
	    ref.invoke(this, $method_update_2, new java.lang.Object[] {$param_String_1}, 7156317230987547829L);
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
}
