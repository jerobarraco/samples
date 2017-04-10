#ifndef _AppController_H_
#define _AppController_H_

#include <Foundation/NSObject.h>

@class NSWindow;
@class NSTextField;
@class NSNotification;

#include "gview.h"
@interface AppController : NSObject
{
   NSWindow *window;
   //GWindow *myWindow;
    GView *gview;
   NSTextField *label;
}

- (void)applicationWillFinishLaunching:(NSNotification *) not;
- (void)applicationDidFinishLaunching:(NSNotification *) not;

@end

#endif /* _AppController_H_ */
