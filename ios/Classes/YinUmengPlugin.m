#import "YinUmengPlugin.h"
#import <yin_umeng/yin_umeng-Swift.h>

@implementation YinUmengPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftYinUmengPlugin registerWithRegistrar:registrar];
}
@end
