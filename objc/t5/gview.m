#include "gview.h"
#include <AppKit/AppKit.h>


@implementation GView

- (id) initWithFrame: (NSRect) rect
{
    [super initWithFrame: rect];

    unsigned int styleMask = NSTitledWindowMask
                               | NSMiniaturizableWindowMask;
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



     [self addSubView: myButton];
}

@end
