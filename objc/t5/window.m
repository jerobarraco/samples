#include <AppKit/AppKit.h>
#include "window.h" // dammit nappa
@implementation GWindow
/* -initWithContentRect:(NSRect)contentRect styleMask:(unsigned int)styleMask backing:(unsigned)backing defer:(BOOL)defer {
    NSWindow* result = [super initWithContentRect:contentRect styleMask:NSBorderlessWindowMask backing:NSBackingStoreBuffered defer:NO];
    [result setBackgroundColor: [NSColor clearColor]];
    [result setAlphaValue:1.0];
    [result setOpaque:NO];
    [result setHasShadow: YES];

    return result;
 };

-xxinitWithContentRect:(NSRect)contentRect styleMask:(unsigned int)styleMask backing:(unsigned)backing defer:(BOOL)defer {
   [super initWithContentRect:contentRect styleMask:styleMask backing:backing defer:defer];
        if ((styleMask & NSUtilityWindowMask) ||
                (styleMask & NSDocModalWindowMask)) {
                _level = NSFloatingWindowLevel; // We want these panels to be above normal windows - so they don't get lost!
        } else {
                _level = NSNormalWindowLevel;
        }
   _releaseWhenClosed=NO;
   return self;
};


- (id)ixxnitWithContentRect:(NSRect)contentRect styleMask:(unsigned int)aStyle backing:(NSBackingStoreType)bufferingType defer:(BOOL)flag {

    NSWindow* result = [super initWithContentRect:contentRect styleMask:NSBorderlessWindowMask backing:NSBackingStoreBuffered defer:NO];
    [result setBackgroundColor: [NSColor clearColor]];
    [result setAlphaValue:1.0];
    [result setOpaque:NO];
    [result setHasShadow: YES];

    return result;
};
- (id)init {  self = [super initWithWindowNibName:@"Preferences"];
              return self;
};
*/
    + (void)create:NSWindow{
        NSRect rect = NSMakeRect (100, 100, 200, 200);
        unsigned int styleMask = NSTitledWindowMask
                                 | NSMiniaturizableWindowMask;

        [super initWithContentRect: rect
                                styleMask: styleMask
                                backing: NSBackingStoreBuffered
                                defer: NO];
        [super setTitle: @"This is a test window"];
        NSButton *myButton;
      NSSize buttonSize;

      myButton = AUTORELEASE ([NSButton new]);
      [myButton setTitle: @"Print Hello!"];
      [myButton sizeToFit];
      [myButton setTarget: self];
      [myButton setAction: @selector (printHello:)];

      buttonSize = [myButton frame].size;
      rect = NSMakeRect (100, 100,
                         buttonSize.width,
                         buttonSize.height);
    }
@end


