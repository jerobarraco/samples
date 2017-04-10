#include "AppController.h"
#include <AppKit/AppKit.h>


@implementation AppController
- (void) applicationWillFinishLaunching: (NSNotification *) not
{
   /* Create Menu */
   NSMenu *menu;
   NSMenu *info;

   menu = [NSMenu new];
   [menu addItemWithTitle: @"Info"
                   action: NULL
            keyEquivalent: @""];
   [menu addItemWithTitle: @"Hide"
                   action: @selector(hide:)
            keyEquivalent: @"h"];
   [menu addItemWithTitle: @"Quit"
                   action: @selector(terminate:)
            keyEquivalent: @"q"];

   info = [NSMenu new];
   [info addItemWithTitle: @"Info Panel..."
                   action: @selector(orderFrontStandardInfoPanel:)
            keyEquivalent: @""];
   [info addItemWithTitle: @"Preferences"
                   action: NULL
            keyEquivalent: @""];
   [info addItemWithTitle: @"Help"
                   action: @selector (orderFrontHelpPanel:)
            keyEquivalent: @"?"];

   [menu setSubmenu: info
            forItem: [menu itemWithTitle:@"Info"]];
   RELEASE(info);

   [NSApp setMainMenu:menu];
   RELEASE(menu);

   /* Create Window */
   window = [[NSWindow alloc] initWithContentRect: NSMakeRect(300, 300, 200, 100)
                                        styleMask: (NSTitledWindowMask |
                                                    NSMiniaturizableWindowMask |
                                                    NSResizableWindowMask)
                                          backing: NSBackingStoreBuffered
                                            defer: YES];
   [window setTitle: @"Hello World"];

   /* Create Label */
   label = [[NSTextField alloc] initWithFrame: NSMakeRect(30, 30, 80, 30)];
   [label setSelectable: NO];
   [label setBezeled: NO];
   [label setDrawsBackground: NO];
   [label setStringValue: @"Hello World"];

   [[window contentView] addSubview: label];
   RELEASE(label);

   gview = [[GView alloc] initWithFrame: NSMakeRect(300, 300, 200, 100)];
   [[window contentView] addSubview: gview];

}

- (void) applicationDidFinishLaunching: (NSNotification *) not
{
   [window makeKeyAndOrderFront: self];
}

- (void) dealloc
{
  RELEASE(window);
  [super dealloc];
}

@end
