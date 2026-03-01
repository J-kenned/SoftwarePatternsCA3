

I started on coretto 1.8 and i was getting an error after i set a new customer: 

```shell
/Users/john/Library/Java/JavaVirtualMachines/corretto-1.8.0_472/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=53134 -Dfile.encoding=UTF-8 -classpath /Users/john/Library/Java/JavaVirtualMachines/corretto-1.8.0_472/Contents/Home/jre/lib/charsets.jar:/Users/john/Library/Java/JavaVirtualMachines/corretto-1.8.0_472/Contents/Home/jre/lib/ext/cldrdata.jar:/Users/john/Library/Java/JavaVirtualMachines/corretto-1.8.0_472/Contents/Home/jre/lib/ext/dnsns.jar:/Users/john/Library/Java/JavaVirtualMachines/corretto-1.8.0_472/Contents/Home/jre/lib/ext/jaccess.jar:/Users/john/Library/Java/JavaVirtualMachines/corretto-1.8.0_472/Contents/Home/jre/lib/ext/localedata.jar:/Users/john/Library/Java/JavaVirtualMachines/corretto-1.8.0_472/Contents/Home/jre/lib/ext/nashorn.jar:/Users/john/Library/Java/JavaVirtualMachines/corretto-1.8.0_472/Contents/Home/jre/lib/ext/sunec.jar:/Users/john/Library/Java/JavaVirtualMachines/corretto-1.8.0_472/Contents/Home/jre/lib/ext/sunjce_provider.jar:/Users/john/Library/Java/JavaVirtualMachines/corretto-1.8.0_472/Contents/Home/jre/lib/ext/sunpkcs11.jar:/Users/john/Library/Java/JavaVirtualMachines/corretto-1.8.0_472/Contents/Home/jre/lib/ext/zipfs.jar:/Users/john/Library/Java/JavaVirtualMachines/corretto-1.8.0_472/Contents/Home/jre/lib/jce.jar:/Users/john/Library/Java/JavaVirtualMachines/corretto-1.8.0_472/Contents/Home/jre/lib/jfr.jar:/Users/john/Library/Java/JavaVirtualMachines/corretto-1.8.0_472/Contents/Home/jre/lib/jsse.jar:/Users/john/Library/Java/JavaVirtualMachines/corretto-1.8.0_472/Contents/Home/jre/lib/management-agent.jar:/Users/john/Library/Java/JavaVirtualMachines/corretto-1.8.0_472/Contents/Home/jre/lib/resources.jar:/Users/john/Library/Java/JavaVirtualMachines/corretto-1.8.0_472/Contents/Home/jre/lib/rt.jar:/Users/john/Documents/School/4th-Year/Software Patterns/CAs/CA3/out/production/CA3 Menu
java.awt.IllegalComponentStateException: component must be showing on the screen to determine its location
	at java.awt.Component.getLocationOnScreen_NoTreeLock(Component.java:2062)
	at java.awt.Component.getLocationOnScreen(Component.java:2036)
	at sun.lwawt.macosx.CAccessibility$23.call(CAccessibility.java:418)
	at sun.lwawt.macosx.CAccessibility$23.call(CAccessibility.java:416)
	at sun.lwawt.macosx.LWCToolkit$CallableWrapper.run(LWCToolkit.java:597)
	at java.awt.event.InvocationEvent.dispatch(InvocationEvent.java:301)
	at java.awt.EventQueue.dispatchEventImpl(EventQueue.java:758)
	at java.awt.EventQueue.access$500(EventQueue.java:97)
	at java.awt.EventQueue$3.run(EventQueue.java:709)
	at java.awt.EventQueue$3.run(EventQueue.java:703)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(ProtectionDomain.java:74)
	at java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(ProtectionDomain.java:84)
	at java.awt.EventQueue$4.run(EventQueue.java:733)
	at java.awt.EventQueue$4.run(EventQueue.java:731)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(ProtectionDomain.java:74)
	at java.awt.EventQueue.dispatchEvent(EventQueue.java:730)
	at java.awt.EventDispatchThread.pumpOneEventForFilters(EventDispatchThread.java:205)
	at java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:116)
	at java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:109)
	at java.awt.WaitDispatchSupport$2.run(WaitDispatchSupport.java:190)
	at java.awt.WaitDispatchSupport$4.run(WaitDispatchSupport.java:235)
	at java.awt.WaitDispatchSupport$4.run(WaitDispatchSupport.java:233)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.awt.WaitDispatchSupport.enter(WaitDispatchSupport.java:233)
	at java.awt.Dialog.show(Dialog.java:1084)
	at javax.swing.JOptionPane.showOptionDialog(JOptionPane.java:869)
	at javax.swing.JOptionPane.showMessageDialog(JOptionPane.java:666)
	at javax.swing.JOptionPane.showMessageDialog(JOptionPane.java:637)
	at Menu$2$2$1.actionPerformed(Menu.java:165)
	at javax.swing.AbstractButton.fireActionPerformed(AbstractButton.java:2022)
	at javax.swing.AbstractButton$Handler.actionPerformed(AbstractButton.java:2348)
	at javax.swing.DefaultButtonModel.fireActionPerformed(DefaultButtonModel.java:402)
	at javax.swing.DefaultButtonModel.setPressed(DefaultButtonModel.java:259)
	at javax.swing.plaf.basic.BasicButtonListener.mouseReleased(BasicButtonListener.java:262)
	at java.awt.Component.processMouseEvent(Component.java:6539)
	at javax.swing.JComponent.processMouseEvent(JComponent.java:3324)
	at java.awt.Component.processEvent(Component.java:6304)
	at java.awt.Container.processEvent(Container.java:2239)
	at java.awt.Component.dispatchEventImpl(Component.java:4889)
	at java.awt.Container.dispatchEventImpl(Container.java:2297)
	at java.awt.Component.dispatchEvent(Component.java:4711)
	at java.awt.LightweightDispatcher.retargetMouseEvent(Container.java:4904)
	at java.awt.LightweightDispatcher.processMouseEvent(Container.java:4535)
	at java.awt.LightweightDispatcher.dispatchEvent(Container.java:4476)
	at java.awt.Container.dispatchEventImpl(Container.java:2283)
	at java.awt.Window.dispatchEventImpl(Window.java:2746)
	at java.awt.Component.dispatchEvent(Component.java:4711)
	at java.awt.EventQueue.dispatchEventImpl(EventQueue.java:760)
	at java.awt.EventQueue.access$500(EventQueue.java:97)
	at java.awt.EventQueue$3.run(EventQueue.java:709)
	at java.awt.EventQueue$3.run(EventQueue.java:703)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(ProtectionDomain.java:74)
	at java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(ProtectionDomain.java:84)
	at java.awt.EventQueue$4.run(EventQueue.java:733)
	at java.awt.EventQueue$4.run(EventQueue.java:731)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(ProtectionDomain.java:74)
	at java.awt.EventQueue.dispatchEvent(EventQueue.java:730)
	at java.awt.EventDispatchThread.pumpOneEventForFilters(EventDispatchThread.java:205)
	at java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:116)
	at java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java:105)
	at java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:101)
	at java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:93)
	at java.awt.EventDispatchThread.run(EventDispatchThread.java:82)

```

This means its a MacOs accessibility code and not an error thrown by swing directly so I looked into compatability. 
```shell
at sun.lwawt.macosx.CAccessibility$23.call(CAccessibility.java:418)                                                                                                                                                     
  at sun.lwawt.macosx.LWCToolkit$CallableWrapper.run(LWCToolkit.java:597) 
```

Could also be this using `f` as parent for the message dialog as `f.dispose()` was called beforehand. 

found a couple of references to window managers causing this on old java versions (im using coretto 1.8): https://stackoverflow.com/questions/12809396/illegalcomponentstateexception-in-java-swing .

# Commit 1

## Switch Java Version to 21

# Commit 2

## Get app running  first

1. For atmCard assign the `valid`

2. Get rid of the extra action listener
   We'll see i guess if this was a mistake but i needed to press the button twice to get to the next screen

3. Change f1 to f in menu.java because some cases f1 is null

4. changed customer to have a list of accounts instead of null

5. Same as above for transations.

6. some weird spacing too

# Commit 3

Move models into model dir and rename Menu to Main